package com.truncate.base.jdbc.dbsession;

import com.truncate.base.jdbc.ConnectionManager;

import java.sql.Connection;

/**
 * ����: session������
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��30��
 * ����ʱ��: 22:34
 */
public class DBSessionFactory
{

	/**
	 *@��������ȡ���ݿ�Ự
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/30
	 *@ʱ��:22:36
	 *
	 */
	public static DBSession getDBSession(String dbName)
	{
		Connection connection = ConnectionManager.getConnection(dbName);
		return new DBSessionImpl(connection);
	}
}
