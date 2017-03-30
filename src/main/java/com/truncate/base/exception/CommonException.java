package com.truncate.base.exception;

/**
 * 描述: 通用异常类
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月28日
 * 创建时间: 22:03
 */
public class CommonException extends RuntimeException
{

	//错误代码
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
