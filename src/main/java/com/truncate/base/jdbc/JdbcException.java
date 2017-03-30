package com.truncate.base.jdbc;

/**
 * ����: jdbc�쳣
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��30��
 * ����ʱ��: 17:59
 */
public class JdbcException extends RuntimeException
{

	public JdbcException()
	{
		super();
	}

	public JdbcException(String message)
	{
		super(message);
	}

	public JdbcException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public JdbcException(Throwable cause)
	{
		super(cause);
	}
}
