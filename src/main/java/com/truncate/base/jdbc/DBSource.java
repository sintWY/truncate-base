package com.truncate.base.jdbc;

/**
 * 描述: 数据源对象
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月30日
 * 创建时间: 16:44
 */
public class DBSource
{

	//数据库名称
	private String dbName;

	//用户名
	private String user;

	//密码
	private String password;

	//驱动类
	private String driver;

	//数据库地址
	private String url;

	//每次获取连接的数量
	private int acquireIncrement;

	//初始化连接的数量
	private int initialPoolSize;

	//池中最小连接数
	private int minPoolSize;

	//池中最大连接数
	private int maxPoolSize;

	//获取连接超时时间
	private int checkoutTimeout;

	//缓存的statement最大数量
	private int maxStatements;

	//每个连接缓存的statement最大数量
	private int maxStatementsPerConnection;

	//连接的最大空闲时间
	private int maxIdleTime;

	//检测连接的时间间隔
	private int idleConnectionTestPeriod;

	public String getDbName()
	{
		return dbName;
	}

	public void setDbName(String dbName)
	{
		this.dbName = dbName;
	}

	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getDriver()
	{
		return driver;
	}

	public void setDriver(String driver)
	{
		this.driver = driver;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public int getAcquireIncrement()
	{
		return acquireIncrement;
	}

	public void setAcquireIncrement(int acquireIncrement)
	{
		this.acquireIncrement = acquireIncrement;
	}

	public int getInitialPoolSize()
	{
		return initialPoolSize;
	}

	public void setInitialPoolSize(int initialPoolSize)
	{
		this.initialPoolSize = initialPoolSize;
	}

	public int getMinPoolSize()
	{
		return minPoolSize;
	}

	public void setMinPoolSize(int minPoolSize)
	{
		this.minPoolSize = minPoolSize;
	}

	public int getMaxPoolSize()
	{
		return maxPoolSize;
	}

	public void setMaxPoolSize(int maxPoolSize)
	{
		this.maxPoolSize = maxPoolSize;
	}

	public int getCheckoutTimeout()
	{
		return checkoutTimeout;
	}

	public void setCheckoutTimeout(int checkoutTimeout)
	{
		this.checkoutTimeout = checkoutTimeout;
	}

	public int getMaxStatements()
	{
		return maxStatements;
	}

	public void setMaxStatements(int maxStatements)
	{
		this.maxStatements = maxStatements;
	}

	public int getMaxStatementsPerConnection()
	{
		return maxStatementsPerConnection;
	}

	public void setMaxStatementsPerConnection(int maxStatementsPerConnection)
	{
		this.maxStatementsPerConnection = maxStatementsPerConnection;
	}

	public int getMaxIdleTime()
	{
		return maxIdleTime;
	}

	public void setMaxIdleTime(int maxIdleTime)
	{
		this.maxIdleTime = maxIdleTime;
	}

	public int getIdleConnectionTestPeriod()
	{
		return idleConnectionTestPeriod;
	}

	public void setIdleConnectionTestPeriod(int idleConnectionTestPeriod)
	{
		this.idleConnectionTestPeriod = idleConnectionTestPeriod;
	}
}
