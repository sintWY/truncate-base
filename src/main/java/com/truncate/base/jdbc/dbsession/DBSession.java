package com.truncate.base.jdbc.dbsession;

import com.truncate.base.domain.DataRow;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;

/**
 * 描述: 数据库会话
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月30日
 * 创建时间: 16:01
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
