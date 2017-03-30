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
 * ����: �������ܻ���
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��29��
 * ����ʱ��: 15:25
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
			logger.error("�����ܺ�[{}]ʧ��,������룺{},������Ϣ��{}", new Object[] { functionItem.getId(), resultVo.getErrorNo(), resultVo.getErrorMessage() });
			resultVo.setErrorNo(commonException.getErrorCode());
			resultVo.setErrorMessage(commonException.getMessage());
		}
		catch(Exception exception)
		{
			logger.error("�����ܺ�[{}]����ϵͳ�쳣!", new Object[] { functionItem.getId() }, exception);
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
