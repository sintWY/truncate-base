package com.truncate.base.jdbc;

/**
 * ����: //TODO ������
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��30��
 * ����ʱ��: 19:24
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
