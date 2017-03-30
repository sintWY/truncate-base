package com.truncate.base.jdbc;

/**
 * 描述: jdbc异常
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月30日
 * 创建时间: 17:59
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
