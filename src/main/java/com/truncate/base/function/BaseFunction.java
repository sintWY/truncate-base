package com.truncate.base.function;

import com.truncate.base.domain.ResultVo;
import com.truncate.base.exception.CommonException;
import com.truncate.base.exception.ErrorCode;
import com.truncate.base.exception.ErrorCodeManager;
import com.truncate.base.interceptor.RequestParam;
import com.truncate.base.util.ConvertUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述: 基础功能基类
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月29日
 * 创建时间: 15:25
 */
public abstract class BaseFunction implements Function
{

	private static final Logger logger = LoggerFactory.getLogger(BaseFunction.class);

	private HttpServletRequest request;

	private HttpServletResponse response;

	private RequestParam requestParam;

	protected ResultVo resultVo = new ResultVo();

	@Override
	public ResultVo execute(HttpServletRequest request, HttpServletResponse response, RequestParam requestParam)
	{
		this.requestParam = requestParam;
		this.request = request;
		this.response = response;
		try
		{
			resultVo = execute();
		}
		catch(CommonException commonException)
		{
			logger.error("请求功能号[{}]失败,错误代码：{},错误信息：{}", new Object[] { requestParam.getFuncNo(), commonException.getErrorCode(), commonException.getMessage() });
			resultVo.setErrorNo(commonException.getErrorCode());
			resultVo.setErrorMessage(commonException.getMessage());
		}
		catch(Exception exception)
		{
			logger.error("请求功能号[{}]出现系统异常!", new Object[] { requestParam.getFuncNo() }, exception);
			resultVo.setErrorNo(ErrorCode.SYS_ERROR);
			resultVo.setErrorMessage(ErrorCodeManager.getErrorMessage(ErrorCode.SYS_ERROR));
		}
		return resultVo;
	}

	public abstract ResultVo execute();

	public HttpServletRequest getRequest()
	{
		return request;
	}

	public HttpServletResponse getResponse()
	{
		return response;
	}

	public RequestParam getRequestParam()
	{
		return requestParam;
	}

	protected String getStrParameter(String name)
	{
		return getStrParameter(name, "");
	}

	/**
	 *@描述：返回参数值
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/4/1
	 *@时间:10:55
	 *
	 */
	protected String getStrParameter(String name, String defaultValue)
	{
		String value = requestParam.getValue(name);
		if(StringUtils.isEmpty(value))
		{
			logger.warn("参数[{}]传入得值为空,返回默认值[{}]!", new Object[] { name, defaultValue });
			return defaultValue;
		}
		return value;
	}

	/**
	 *@描述：获取int值
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/4/1
	 *@时间:10:58
	 *
	 */
	protected int getIntParameter(String name, int defaultValue)
	{
		String value = getStrParameter(name, String.valueOf(defaultValue));
		return ConvertUtil.str2Int(value);
	}

	protected int getIntParameter(String name)
	{
		return getIntParameter(name, 0);
	}

	public long getLongParameter(String name, long defaultValue)
	{
		String value = getStrParameter(name, String.valueOf(defaultValue));
		return ConvertUtil.str2Long(value);
	}

	public long getLongParameter(String name)
	{
		return getLongParameter(name, 0L);
	}

	protected double getDoubleParameter(String name, double defaultValue)
	{
		String value = getStrParameter(name, String.valueOf(defaultValue));
		return ConvertUtil.str2Double(value);
	}

	protected double getDoubleParameter(String name)
	{
		return getDoubleParameter(name, 0.0D);
	}

	public String getAndCheckParameter(String name)
	{
		String value = getStrParameter(name);
		if(StringUtils.isEmpty(value))
		{
			throw new CommonException(ErrorCode.EMPTY_ARGUMENT_ERROR, new Object[] { name });
		}
		return value;
	}

	protected String getRequestIp()
	{
		return requestParam.getRequestIp();
	}

	protected String getSerialNo()
	{
		return requestParam.getSerialNo();
	}
}
