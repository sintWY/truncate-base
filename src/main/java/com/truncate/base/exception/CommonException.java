package com.truncate.base.exception;

/**
 * ����: ͨ���쳣��
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��28��
 * ����ʱ��: 22:03
 */
public class CommonException extends RuntimeException
{

	//�������
	private int errorCode;

	public CommonException(int errorCode)
	{
		super(ErrorCodeManager.getErrorMessage(errorCode));
		this.errorCode = errorCode;
	}

	public CommonException(int errorCode, Object[] targets)
	{
		super(ErrorCodeManager.getErrorMessage(errorCode, targets));
		this.errorCode = errorCode;
	}

	public CommonException(int errorCode, String errorMessage)
	{
		super(errorMessage);
		this.errorCode = errorCode;
	}

	public int getErrorCode()
	{
		return errorCode;
	}

	public void setErrorCode(int errorCode)
	{
		this.errorCode = errorCode;
	}

	public static void main(String[] args)
	{
		throw new CommonException(ErrorCode.EMPTY_ARGUMENT_ERROR, new String[] { "funcNo" });
	}
}
