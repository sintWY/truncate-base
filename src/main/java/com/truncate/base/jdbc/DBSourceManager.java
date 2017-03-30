package com.truncate.base.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.truncate.base.exception.CommonException;
import com.truncate.base.exception.ErrorCode;
import com.truncate.base.util.ConvertUtil;
import com.truncate.base.util.XmlUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述: 数据源管理类
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月30日
 * 创建时间: 16:53
 */
public class DBSourceManager
{

	private static final Logger logger = LoggerFactory.getLogger(DBSourceManager.class);

	private static final String DBSOURCE_CONFIG_FILE = "c3p0-config.xml";

	private static final Map<String, ComboPooledDataSource> DB_POOL_MAP = new HashMap<String, ComboPooledDataSource>();

	static
	{
		loadDBSourceConfig();
	}

	private static void loadDBSourceConfig()
	{
		Document document = XmlUtil.getDocumentFromClassPath(DBSOURCE_CONFIG_FILE);
		Element rootElement = document.getRootElement();
		if(rootElement == null)
		{
			throw new CommonException(ErrorCode.FILE_CONTENT_FORMAT_ERROR);
		}
		List<Element> dbSourceElements = rootElement.elements("db-source");
		if(dbSourceElements != null && !dbSourceElements.isEmpty())
		{
			for(Element dbSourceElement : dbSourceElements)
			{
				String dbName = dbSourceElement.attributeValue("name");
				DBSource dbSource = new DBSource();
				dbSource.setDbName(dbName);
				List<Element> properties = dbSourceElement.elements("property");
				for(Element property : properties)
				{
					String propertyName = property.attributeValue("name");
					String propertyValue = property.getTextTrim();
					if("user".equals(propertyName))
					{
						dbSource.setUser(propertyValue);
					}
					else if("password".equals(propertyName))
					{
						dbSource.setPassword(propertyValue);
					}
					else if("driver".equals(propertyName))
					{
						dbSource.setDriver(propertyValue);
					}
					else if("url".equals(propertyName))
					{
						dbSource.setUrl(propertyValue);
					}
					else if("acquireIncrement".equals(propertyName))
					{
						dbSource.setAcquireIncrement(ConvertUtil.str2Int(propertyValue));
					}
					else if("initialPoolSize".equals(propertyName))
					{
						dbSource.setInitialPoolSize(ConvertUtil.str2Int(propertyValue));
					}
					else if("minPoolSize".equals(propertyName))
					{
						dbSource.setMinPoolSize(ConvertUtil.str2Int(propertyValue));
					}
					else if("maxPoolSize".equals(propertyName))
					{
						dbSource.setMaxPoolSize(ConvertUtil.str2Int(propertyValue));
					}
					else if("checkoutTimeout".equals(propertyName))
					{
						dbSource.setCheckoutTimeout(ConvertUtil.str2Int(propertyValue));
					}
					else if("maxStatements".equals(propertyName))
					{
						dbSource.setMaxStatements(ConvertUtil.str2Int(propertyValue));
					}
					else if("maxStatementsPerConnection".equals(propertyName))
					{
						dbSource.setMaxStatementsPerConnection(ConvertUtil.str2Int(propertyValue));
					}
					else if("maxIdleTime".equals(propertyName))
					{
						dbSource.setMaxIdleTime(ConvertUtil.str2Int(propertyValue));
					}
					else if("idleConnectionTestPeriod".equals(propertyName))
					{
						dbSource.setIdleConnectionTestPeriod(ConvertUtil.str2Int(propertyValue));
					}
				}
				//初始化数据库连接池
				initDBConnectionPool(dbSource);
			}
		}
		else
		{
			logger.warn("数据库配置文件[{}]中未配置数据源!", new Object[] { DBSOURCE_CONFIG_FILE });
		}
	}

	/**
	 *@描述：初始化池
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/30
	 *@时间:17:20
	 *
	 */
	private static void initDBConnectionPool(DBSource dbSource)
	{
		try
		{
			ComboPooledDataSource dataSource = new ComboPooledDataSource();
			dataSource.setUser(dbSource.getUser());
			dataSource.setPassword(dbSource.getPassword());
			dataSource.setDriverClass(dbSource.getDriver());
			dataSource.setJdbcUrl(dbSource.getUrl());
			dataSource.setAcquireIncrement(dbSource.getAcquireIncrement());
			dataSource.setInitialPoolSize(dbSource.getInitialPoolSize());
			dataSource.setMinPoolSize(dbSource.getMinPoolSize());
			dataSource.setMaxPoolSize(dbSource.getMaxPoolSize());
			dataSource.setCheckoutTimeout(dbSource.getCheckoutTimeout());
			dataSource.setMaxStatements(dbSource.getMaxStatements());
			dataSource.setMaxStatementsPerConnection(dbSource.getMaxStatementsPerConnection());
			dataSource.setMaxIdleTime(dbSource.getMaxIdleTime());
			dataSource.setIdleConnectionTestPeriod(dbSource.getIdleConnectionTestPeriod());
			DB_POOL_MAP.put(dbSource.getDbName(), dataSource);
		}
		catch(PropertyVetoException e)
		{
			logger.error("初始化数据源[{}]失败!", new Object[] { dbSource.getDbName() });
		}
	}

	/**
	 *@描述：获取指定的数据库连接池
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/30
	 *@时间:17:54
	 *
	 */
	public static ComboPooledDataSource getComboPooledDataSource(String name)
	{
		return DB_POOL_MAP.get(name);
	}

	public static void init()
	{
	}
}
