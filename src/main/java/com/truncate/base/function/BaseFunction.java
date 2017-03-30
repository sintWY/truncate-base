package com.truncate.base.function;

import com.truncate.base.config.FunctionItem;
import com.truncate.base.domain.ResultVo;
import com.truncate.base.exception.CommonException;
import com.truncate.base.exception.ErrorCode;
import com.truncate.base.exception.ErrorCodeManager;
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

	private FunctionItem functionItem;

	protected ResultVo resultVo = new ResultVo();

	@Override
	public ResultVo execute(FunctionItem functionItem, HttpServletRequest request, HttpServletResponse response)
	{
		this.functionItem = functionItem;
		this.request = request;
		this.response = response;
		try
		{
			resultVo = execute();
		}
		catch(CommonException commonException)
		{
			logger.error("请求功能号[{}]失败,错误代码：{},错误信息：{}", new Object[] { functionItem.getId(), resultVo.getErrorNo(), resultVo.getErrorMessage() });
			resultVo.setErrorNo(commonException.getErrorCode());
			resultVo.setErrorMessage(commonException.getMessage());
		}
		catch(Exception exception)
		{
			logger.error("请求功能号[{}]出现系统异常!", new Object[] { functionItem.getId() }, exception);
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

	public FunctionItem getFunctionItem()
	{
		return functionItem;
	}
}
