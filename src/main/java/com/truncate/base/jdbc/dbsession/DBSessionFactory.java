package com.truncate.base.jdbc.dbsession;

import com.truncate.base.jdbc.ConnectionManager;

import java.sql.Connection;

/**
 * 描述: session工厂类
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月30日
 * 创建时间: 22:34
 */
public class DBSessionFactory
{

	/**
	 *@描述：获取数据库会话
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/30
	 *@时间:22:36
	 *
	 */
	public static DBSession getDBSession(String dbName)
	{
		Connection connection = ConnectionManager.getConnection(dbName);
		return new DBSessionImpl(connection);
	}
}
