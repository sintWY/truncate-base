package com.truncate.base.jdbc.dbsession;

import com.truncate.base.domain.DataRow;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;

/**
 * ����: ���ݿ�Ự
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��30��
 * ����ʱ��: 16:01
 */
public interface DBSession
{

	void beginTransaction();

	void stopTransaction();

	void commitTransaction();

	void rollBackTransaction();

	void close();

	DataRow queryMap(String sql);

	DataRow queryMap(String sql, Object[] args);

	List<DataRow> queryList(String sql);

	List<DataRow> queryList(String sql, Object[] args);

	List<DataRow> queryList(String sql, Object[] args, int starRow, int endRow);

	List<DataRow> queryList(String sql, Object[] args, int rows);

	int queryInt(String sql);

	int queryInt(String sql, Object[] args);

	long queryLong(String sql);

	long queryLong(String sql, Object[] args);

	double queryDouble(String sql);

	double queryDouble(String sql, Object[] args);

	String queryString(String sql);

	String queryString(String sql, Object[] args);

	int update(String sql, Object[] args);

	int[] batchUpdate(String sql, Object[][] args);

	void insert(String tableName, DataRow data);

	DataRow turnDataRow(ResultSet resultSet, ResultSetMetaData metaData);
}
