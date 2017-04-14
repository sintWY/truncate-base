package com.truncate.base.jdbc.dbsession;

import com.truncate.base.constant.Constant;
import com.truncate.base.domain.DataPage;
import com.truncate.base.domain.DataRow;
import com.truncate.base.jdbc.ConnectionManager;
import com.truncate.base.jdbc.DBSourceManager;
import com.truncate.base.jdbc.JdbcException;
import com.truncate.base.util.ConvertUtil;
import com.truncate.base.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * 描述: 数据库会话实现
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月30日
 * 创建时间: 16:15
 */
public class DBSessionImpl implements DBSession
{

	private static final Logger logger = LoggerFactory.getLogger(DBSessionImpl.class);

	//数据库连接
	private Connection connection;

	//数据库类型
	private Constant.DatabaseType databaseType;

	public DBSessionImpl(Connection connection)
	{
		this.connection = connection;
		this.databaseType = ConnectionManager.getDatabaseType(connection);
	}

	public DBSessionImpl(String name)
	{
		this.connection = ConnectionManager.getConnection(name);
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
		}
		return resultList;
	}

	@Override
	public List<DataRow> queryList(String sql, Object[] args, int starRow, int rows)
	{
		StringBuilder sqlBuilder = new StringBuilder();
		if(databaseType == Constant.DatabaseType.MYSQL)
		{
			sqlBuilder.append("SELECT * FROM ( ");
			sql = sql.toUpperCase();
			int tempIndex = sql.indexOf("ORDER BY");
			if(tempIndex > 0)
			{
				sqlBuilder.append(sql.substring(0, tempIndex));
			}
			sqlBuilder.append(sql.substring(tempIndex));
			sqlBuilder.append(" ) _T1 ");
			sqlBuilder.append(" LIMIT ");
			sqlBuilder.append(starRow);
			sqlBuilder.append(" , ");
			sqlBuilder.append(rows);
		}
		return queryList(sqlBuilder.toString(), args);
	}

	@Override
	public List<DataRow> queryList(String sql, Object[] args, int rows)
	{
		return queryList(sql, args, 0, rows);
	}

	@Override
	public DataPage queryPage(String sql, Object[] args, int currentPage, int numPerPage)
	{
		int totalRows = queryTotalRows(sql, args);
		DataPage dataPage = new DataPage(currentPage, numPerPage, totalRows);
		if(databaseType == Constant.DatabaseType.MYSQL)
		{
			List<DataRow> dataList = queryList(sql, args, dataPage.getStartRow(), numPerPage);
			dataPage.setDataList(dataList);
		}
		return dataPage;
	}

	@Override
	public DataPage queryPage(String sql, int currentPage, int numPerPage)
	{
		return queryPage(sql, null, currentPage, numPerPage);
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
		}
		return value;
	}

	@Override
	public int update(String sql, Object[] args)
	{
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
		}
	}

	@Override
	public int[] batchUpdate(String sql, Object[][] args)
	{
		PreparedStatement preparedStatement = null;
		try
		{
			preparedStatement = connection.prepareStatement(sql);
			for(Object[] arg : args)
			{
				for(int j = 0; j < arg.length; j++)
				{
					preparedStatement.setObject(j + 1, arg[j]);
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

	private int queryTotalRows(String sql, Object[] args)
	{
		String sqlBuilder = "SELECT COUNT(*) FROM ( " + sql + " ) _TAB1";
		return queryInt(sqlBuilder, args);
	}

	private DataRow turnDataRow(ResultSet resultSet, ResultSetMetaData metaData)
	{
		DataRow data = new DataRow();
		try
		{
			int columnCount = metaData.getColumnCount();
			for(int i = 0; i < columnCount; i++)
			{
				String fieldName;
				if(this.databaseType == Constant.DatabaseType.MYSQL)
				{
					fieldName = metaData.getColumnLabel(i + 1).toLowerCase();
				}
				else
				{
					fieldName = metaData.getColumnName(i + 1).toLowerCase();
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
			throw new JdbcException("数据行转换为DataRow失败!", e);
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
				throw new JdbcException("关闭PreparedStatement失败!", e);
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
				throw new JdbcException("关闭结果集失败!");
			}
		}
	}
}
