package com.truncate.base.jdbc;

/**
 * 描述: //TODO 类描述
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月30日
 * 创建时间: 19:24
 */
public enum DatabaseType
{

	ORACLE("oracle"),
	MYSQL("mysql");

	private String type;

	DatabaseType(String type)
	{
		this.type = type;
	}
}
