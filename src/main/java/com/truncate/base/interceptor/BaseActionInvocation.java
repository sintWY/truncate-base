package com.truncate.base.interceptor;

import com.truncate.base.config.FunctionConfig;
import com.truncate.base.config.FunctionItem;
import com.truncate.base.constant.ControlConstant;
import com.truncate.base.domain.ResultVo;
import com.truncate.base.exception.CommonException;
import com.truncate.base.exception.ErrorCode;
import com.truncate.base.function.Function;
import com.truncate.base.util.JsonUtil;
import com.truncate.base.util.ReflectUtil;
import com.truncate.base.util.RequestUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * 描述: //TODO 类描述
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月29日
 * 创建时间: 18:20
 */
public class BaseActionInvocation implements ActionInvocation
{

	private static final Logger logger = LoggerFactory.getLogger(BaseActionInvocation.class);

	private HttpServletRequest request;

	private HttpServletResponse response;

	private FunctionItem functionItem;

	private int funcNo;

	private Map paraMap;

	private boolean executed;

	private ResultVo resultVo;

	private LinkedHashSet<Interceptor> intercrptorList;

	private Iterator<Interceptor> iterator;

	private long start;

	private long end;

	public BaseActionInvocation(HttpServletRequest request, HttpServletResponse response)
	{
		this.request = request;
		this.response = response;
		this.executed = false;
		this.resultVo = new ResultVo();
		this.start = System.currentTimeMillis();
		init();
	}

	private void init()
	{
		if(request == null)
		{
			throw new CommonException(ErrorCode.EMPTY_ARGUMENT_ERROR, new String[] { "request" });
		}
		if(response == null)
		{
			throw new CommonException(ErrorCode.EMPTY_ARGUMENT_ERROR, new String[] { "response" });
		}
		funcNo = RequestUtil.getInt(request, "funcNo");
		if(funcNo == 0)
		{
			throw new CommonException(ErrorCode.EMPTY_ARGUMENT_ERROR, new String[] { "funcNo" });
		}
		functionItem = FunctionConfig.getFunctionItem(funcNo);
		if(functionItem == null)
		{
			throw new CommonException(ErrorCode.NOT_EXISTS_DATA_ERROR, new String[] { "功能号:" + funcNo });
		}

		//初始化参数
		this.paraMap = request.getParameterMap();

		//初始化拦截器
		this.intercrptorList = functionItem.getInterceptors();

		//初始化迭代器
		iterator = intercrptorList.iterator();
	}

	@Override
	public ResultVo invoke()
	{
		if(executed)
		{
			throw new CommonException(ErrorCode.REPEATABILITY_REQUEST_ERROR);
		}
		if(iterator.hasNext())
		{
			Interceptor intercrptor = iterator.next();
			resultVo = intercrptor.intercept(this);
		}
		else
		{
			resultVo = invokeLast();
		}
		executed = true;
		return resultVo;
	}

	@Override
	public HttpServletRequest getRequest()
	{
		return request;
	}

	@Override
	public HttpServletResponse getResponse()
	{
		return response;
	}

	@Override
	public FunctionItem getFunctionItem()
	{
		return functionItem;
	}

	@Override
	public int getFuncNo()
	{
		return funcNo;
	}

	@Override
	public Map getParaMap()
	{
		return paraMap;
	}

	private ResultVo invokeLast()
	{
		ResultVo resultVo;
		int type = functionItem.getType();
		switch(type)
		{
			case ControlConstant.FunctionType.LOCAL_CLASS_FUNCTION:
				resultVo = invokeLocalClassFunction();
				break;
			case ControlConstant.FunctionType.THIRD_INTERFACE_FUNCTION:
			default:
				throw new CommonException(ErrorCode.NOT_SUPPORT_FUNCTION_TYPE_ERROR, new String[] { "type=" + type });
		}
		return resultVo;
	}

	private ResultVo invokeLocalClassFunction()
	{
		String className = functionItem.getClassName();
		if(StringUtils.isEmpty(className))
		{
			throw new CommonException(ErrorCode.EMPTY_ARGUMENT_ERROR, new String[] { "className" });
		}

		Object object = ReflectUtil.reflectObject(className);
		if(!(object instanceof Function))
		{
			throw new CommonException(ErrorCode.CLASS_IMPL_ERROR, new String[] { className, Function.class.getName() });
		}

		Function function = (Function) object;
		ResultVo resultVo = function.execute(functionItem, request, response);
		if(logger.isInfoEnabled())
		{
			end = System.currentTimeMillis();
			logger.info("功能号[{}]调用时间为：" + (end - start) + "ms,返回的结果为：{}", funcNo, JsonUtil.toString(resultVo.getResultMap()));
		}
		return resultVo;
	}
}
