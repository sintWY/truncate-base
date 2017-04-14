package com.truncate.base.interceptor;

import com.truncate.base.config.FunctionConfig;
import com.truncate.base.config.FunctionItem;
import com.truncate.base.constant.Constant;
import com.truncate.base.domain.ResultVo;
import com.truncate.base.exception.CommonException;
import com.truncate.base.exception.ErrorCode;
import com.truncate.base.function.Function;
import com.truncate.base.util.ConvertUtil;
import com.truncate.base.util.ReflectUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * 描述: 基础请求执行对象
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

	private boolean executed;

	private ResultVo resultVo;

	private LinkedHashSet<Interceptor> intercrptorList;

	private Iterator<Interceptor> iterator;

	private RequestParam requestParam;

	public BaseActionInvocation(HttpServletRequest request, HttpServletResponse response)
	{
		this.request = request;
		this.response = response;
		this.executed = false;
		this.resultVo = new ResultVo();
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
		funcNo = ConvertUtil.str2Int(request.getParameter("funcNo"));
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
		this.requestParam = new RequestParam(request);

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
	public RequestParam getRequestParam()
	{
		return requestParam;
	}

	private ResultVo invokeLast()
	{
		ResultVo resultVo;
		String functionType = functionItem.getFunctionType();
		if(Constant.FunctionType.LOCAL.equals(functionType))
		{
			resultVo = invokeLocalClassFunction();
		}
		else
		{
			throw new CommonException(ErrorCode.NOT_SUPPORT_FUNCTION_TYPE_ERROR, new String[] { "type=" + functionType });
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
		return function.execute(request, response, requestParam);
	}
}
