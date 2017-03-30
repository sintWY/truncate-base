package com.truncate.base.jdbc.dbsession;

import com.truncate.base.domain.DataRow;
import com.truncate.base.jdbc.ConnectionManager;
import com.truncate.base.jdbc.DatabaseType;
import com.truncate.base.jdbc.JdbcException;
import com.truncate.base.util.ConvertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * ����: ���ݿ�Ựʵ��
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��30��
 * ����ʱ��: 16:15
 */
public class DBSessionImpl implements DBSession
{

	private static final Logger logger = LoggerFactory.getLogger(DBSessionImpl.class);

	//���ݿ�����
	private Connection connection;

	//���ݿ�����
	private DatabaseType databaseType;

	public DBSessionImpl(Connection connection)
	{
		this.connection = connection;
		this.databaseType = ConnectionManager.getDatabaseType(connection);
	}

	@Override
	public void beginTransaction()
	{
		ConnectionManager.beginTransaction(connection);
	}

	@Override
	public void stopTransaction()
	{
		ConnectionManager.stopTransaction(connection);
	}

	@Override
	public void commitTransaction()
	{
		ConnectionManager.commitTransaction(connection);
	}

	@Override
	public void rollBackTransaction()
	{
		ConnectionManager.rollBackTransaction(connection);
	}

	@Override
	public void close()
	{
		ConnectionManager.releasleConnection(connection);
	}

	@Override
	public DataRow queryMap(String sql)
	{
		return queryMap(sql, null);
	}

	@Override
	public DataRow queryMap(String sql, Object[] args)
	{
		long start = System.currentTimeMillis();
		DataRow data = new DataRow();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try
		{
			preparedStatement = connection.prepareCall(sql);
			if(args != null && args.length > 0)
			{
				for(int i = 1; i <= args.length; i++)
				{
					preparedStatement.setObject(i, args[i - 1]);
				}
			}
			resultSet = preparedStatement.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			if(resultSet.next())
			{
				data = turnDataRow(resultSet, metaData);
			}
		}
		catch(SQLException e)
		{
			throw new JdbcException(e);
		}
		finally
		{
			closeResultSet(resultSet);
			closePrepareStatement(preparedStatement);

			long end = System.currentTimeMillis();
			logCost(start, end, sql, args);
		}
		return data;
	}

	@Override
	public List<DataRow> queryList(String sql)
	{
		return queryList(sql, null);
	}

	@Override
	public List<DataRow> queryList(String sql, Object[] args)
	{
		long start = System.currentTimeMillis();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<DataRow> resultList = new ArrayList<DataRow>();
		try
		{
			preparedStatement = connection.prepareStatement(sql);
			if(args != null && args.length > 0)
			{
				for(int i = 1; i <= args.length; i++)
				{
					preparedStatement.setObject(i, args[i - 1]);
				}
			}
			resultSet = preparedStatement.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			while(resultSet.next())
			{
				resultList.add(turnDataRow(resultSet, metaData));
			}
		}
		catch(SQLException e)
		{
			throw new JdbcException(e);
		}
		finally
		{
			closeResultSet(resultSet);
			closePrepareStatement(preparedStatement);

			long end = System.currentTimeMillis();
			logCost(start, end, sql, args);
		}
		return resultList;
	}

	@Override
	public List<DataRow> queryList(String sql, Object[] args, int starRow, int endRow)
	{
		int totalRows = queryInt(sql, args);
		endRow = endRow < totalRows ? endRow : totalRows;
		StringBuilder sqlBuilder = new StringBuilder();
		if(databaseType == DatabaseType.MYSQL)
		{
			sqlBuilder.append("SELECT * FROM ( ");
			sqlBuilder.append(sql);
			sqlBuilder.append(" LIMIT ");
			sqlBuilder.append(starRow);
			sqlBuilder.append(" , ");
			sqlBuilder.append(endRow);
			sqlBuilder.append(" ) ");
		}
		return queryList(sqlBuilder.toString(), args);
	}

	@Override
	public List<DataRow> queryList(String sql, Object[] args, int rows)
	{
		int totalRows = queryInt(sql, args);
		int endRow = rows < totalRows ? rows : totalRows;
		return queryList(sql, args, 0, endRow);
	}

	@Override
	public int queryInt(String sql)
	{
		return queryInt(sql, null);
	}

	@Override
	public int queryInt(String sql, Object[] args)
	{
		String value = queryString(sql, args);
		return ConvertUtil.str2Int(value);
	}

	@Override
	public long queryLong(String sql)
	{
		return queryLong(sql, null);
	}

	@Override
	public long queryLong(String sql, Object[] args)
	{
		String value = queryString(sql, args);
		return ConvertUtil.str2Long(value);
	}

	@Override
	public double queryDouble(String sql)
	{
		return queryDouble(sql, null);
	}

	@Override
	public double queryDouble(String sql, Object[] args)
	{
		String value = queryString(sql, args);
		return ConvertUtil.str2Double(value);
	}

	@Override
	public String queryString(String sql)
	{
		return queryString(sql, null);
	}

	@Override
	public String queryString(String sql, Object[] args)
	{
		long start = System.currentTimeMillis();
		String value = "";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try
		{
			preparedStatement = connection.prepareStatement(sql);
			if(args != null && args.length > 0)
			{
				for(int i = 1; i <= args.length; i++)
				{
					preparedStatement.setObject(i, args[i - 1]);
				}
			}
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				value = resultSet.getString(1);
			}
		}
		catch(SQLException e)
		{
			throw new JdbcException(e);
		}
		finally
		{
			closeResultSet(resultSet);
			closePrepareStatement(preparedStatement);

			long end = System.currentTimeMillis();
			logCost(start, end, sql, args);
		}
		return value;
	}

	@Override
	public int update(String sql, Object[] args)
	{
		long start = System.currentTimeMillis();
		PreparedStatement preparedStatement = null;
		try
		{
			preparedStatement = connection.prepareStatement(sql);
			if(args != null && args.length > 0)
			{
				for(int i = 1; i <= args.length; i++)
				{
					preparedStatement.setObject(i, args[i - 1]);
				}
			}
			return preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			throw new JdbcException(e);
		}
		finally
		{
			closePrepareStatement(preparedStatement);

			long end = System.currentTimeMillis();
			logCost(start, end, sql, args);
		}
	}

	@Override
	public int[] batchUpdate(String sql, Object[][] args)
	{
		long start = System.currentTimeMillis();
		PreparedStatement preparedStatement = null;
		try
		{
			preparedStatement = connection.prepareStatement(sql);
			for(int i = 0; i < args.length; i++)
			{
				for(int j = 0; j < args[i].length; j++)
				{
					preparedStatement.setObject(j + 1, args[i][j]);
				}
				preparedStatement.addBatch();
			}
			int[] arr = preparedStatement.executeBatch();
			preparedStatement.clearBatch();
			return arr;
		}
		catch(SQLException e)
		{
			throw new JdbcException(e);
		}
		finally
		{
			closePrepareStatement(preparedStatement);

			long end = System.currentTimeMillis();
			logCost(start, end, sql, args);
		}
	}

	@Override
	public void insert(String tableName, DataRow data)
	{
		StringBuilder sqlBuilder = new StringBuilder(" INSERT INTO ");
		sqlBuilder.append(tableName);
		sqlBuilder.append(" ( ");
		Set<String> keySet = data.keySet();
		Object[] args = new Object[keySet.size()];
		int index = 0;
		for(String key : keySet)
		{
			args[index] = data.get(key);
			sqlBuilder.append(key);
			if(index != keySet.size() - 1)
			{
				sqlBuilder.append(" , ");
			}
			index++;
		}
		sqlBuilder.append(" ( ");
		sqlBuilder.append(ConvertUtil.createRepeat("?", ",", keySet.size()));
		sqlBuilder.append(" ) ");
		update(sqlBuilder.toString(), args);
	}

	@Override
	public DataRow turnDataRow(ResultSet resultSet, ResultSetMetaData metaData)
	{
		DataRow data = new DataRow();
		try
		{
			int columnCount = metaData.getColumnCount();
			for(int i = 0; i < columnCount; i++)
			{
				String fieldName;
				if(this.databaseType == DatabaseType.MYSQL)
				{
					fieldName = metaData.getColumnLabel(i + 1);
				}
				else
				{
					fieldName = metaData.getColumnName(i + 1);
				}

				Object value = resultSet.getObject(fieldName);
				if(value instanceof Clob)
				{
					value = resultSet.getString(fieldName);
				}
				else if(value instanceof Blob)
				{
					value = resultSet.getByte(fieldName);
				}
				else if(value instanceof Date)
				{
					value = resultSet.getTimestamp(fieldName);
				}
				data.set(fieldName, value);
			}
		}
		catch(SQLException e)
		{
			throw new JdbcException("������ת��ΪDataRowʧ��!", e);
		}
		return data;
	}

	private void closePrepareStatement(PreparedStatement preparedStatement)
	{
		if(preparedStatement != null)
		{
			try
			{
				preparedStatement.clearBatch();
				preparedStatement.close();
			}
			catch(SQLException e)
			{
				throw new JdbcException("�ر�PreparedStatementʧ��!", e);
			}
		}
	}

	private void closeResultSet(ResultSet resultSet)
	{
		if(resultSet != null)
		{
			try
			{
				resultSet.close();
			}
			catch(SQLException e)
			{
				throw new JdbcException("�رս����ʧ��!");
			}
		}
	}

	private void logCost(long start, long end, String sql, Object[] args)
	{
		long cost = end - start;
		if(cost > 1000L)
		{
			logger.warn("SQL���[{}]ִ��ʱ�����,����:{},����ʱ�䣺{}ms.", new Object[] { sql, args == null ? "[]" : Arrays.toString(args), cost });
		}
		else
		{
			if(logger.isDebugEnabled())
			{
				logger.debug("ִ��SQL���:[{}],����:{},����ʱ�䣺{}ms.", new Object[] { sql, args == null ? "[]" : Arrays.toString(args), cost });
			}
		}
	}
}
