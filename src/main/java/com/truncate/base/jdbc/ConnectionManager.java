package com.truncate.base.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 描述: 数据库连接管理
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月30日
 * 创建时间: 16:26
 */
public class ConnectionManager
{

	private static final Logger logger = LoggerFactory.getLogger(ConnectionManager.class);

	/**
	 *@描述：获取数据库连接
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/30
	 *@时间:18:03
	 *
	 */
	public static Connection getConnection(String dbName)
	{
		ComboPooledDataSource dataSource = DBSourceManager.getComboPooledDataSource(dbName);
		if(dataSource == null)
		{
			throw new JdbcException("数据源" + dbName + "未配置!");
		}
		try
		{
			return dataSource.getConnection();
		}
		catch(SQLException e)
		{
			throw new JdbcException("从数据库[" + dbName + "]连接池中获取连接失败!", e);
		}
	}

	/**
	 *@描述：关闭连接
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/30
	 *@时间:18:05
	 *
	 */
	public static void releasleConnection(Connection connection)
	{
		if(connection != null)
		{
			try
			{
				connection.close();
			}
			catch(SQLException e)
			{
				throw new JdbcException("关闭连接失败!", e);
			}
		}
	}

	public static void beginTransaction(Connection connection)
	{
		try
		{
			connection.setAutoCommit(false);
		}
		catch(SQLException e)
		{
			throw new JdbcException("开启数据事务失败!", e);
		}
	}

	public static void stopTransaction(Connection connection)
	{
		try
		{
			connection.setAutoCommit(true);
		}
		catch(SQLException e)
		{
			throw new JdbcException("关闭数据库事务失败!", e);
		}
	}

	public static void commitTransaction(Connection connection)
	{
		try
		{
			connection.commit();
		}
		catch(SQLException e)
		{
			throw new JdbcException("提交事务失败!", e);
		}
	}

	public static void rollBackTransaction(Connection connection)
	{
		try
		{
			connection.rollback();
		}
		catch(SQLException e)
		{
			throw new JdbcException("回滚事务失败!", e);
		}
	}

	public static DatabaseType getDatabaseType(Connection connection)
	{
		try
		{
			String productName = connection.getMetaData().getDatabaseProductName();
			if("oracle".equalsIgnoreCase(productName))
			{
				return DatabaseType.ORACLE;
			}
			else if("mysql".equalsIgnoreCase(productName))
			{
				return DatabaseType.MYSQL;
			}
			throw new JdbcException("暂不支持的数据库类型!");
		}
		catch(SQLException e)
		{
			throw new JdbcException("获取数据类型失败!", e);
		}
	}
}
