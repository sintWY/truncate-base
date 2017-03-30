package com.truncate.base.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * ����: ���ݿ����ӹ���
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��30��
 * ����ʱ��: 16:26
 */
public class ConnectionManager
{

	private static final Logger logger = LoggerFactory.getLogger(ConnectionManager.class);

	/**
	 *@��������ȡ���ݿ�����
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/30
	 *@ʱ��:18:03
	 *
	 */
	public static Connection getConnection(String dbName)
	{
		ComboPooledDataSource dataSource = DBSourceManager.getComboPooledDataSource(dbName);
		if(dataSource == null)
		{
			throw new JdbcException("����Դ" + dbName + "δ����!");
		}
		try
		{
			return dataSource.getConnection();
		}
		catch(SQLException e)
		{
			throw new JdbcException("�����ݿ�[" + dbName + "]���ӳ��л�ȡ����ʧ��!", e);
		}
	}

	/**
	 *@�������ر�����
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/30
	 *@ʱ��:18:05
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
				throw new JdbcException("�ر�����ʧ��!", e);
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
			throw new JdbcException("������������ʧ��!", e);
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
			throw new JdbcException("�ر����ݿ�����ʧ��!", e);
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
			throw new JdbcException("�ύ����ʧ��!", e);
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
			throw new JdbcException("�ع�����ʧ��!", e);
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
			throw new JdbcException("�ݲ�֧�ֵ����ݿ�����!");
		}
		catch(SQLException e)
		{
			throw new JdbcException("��ȡ��������ʧ��!", e);
		}
	}
}
