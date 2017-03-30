package com.truncate.base.jdbc;

/**
 * ����: ����Դ����
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��30��
 * ����ʱ��: 16:44
 */
public class DBSource
{

	//���ݿ�����
	private String dbName;

	//�û���
	private String user;

	//����
	private String password;

	//������
	private String driver;

	//���ݿ��ַ
	private String url;

	//ÿ�λ�ȡ���ӵ�����
	private int acquireIncrement;

	//��ʼ�����ӵ�����
	private int initialPoolSize;

	//������С������
	private int minPoolSize;

	//�������������
	private int maxPoolSize;

	//��ȡ���ӳ�ʱʱ��
	private int checkoutTimeout;

	//�����statement�������
	private int maxStatements;

	//ÿ�����ӻ����statement�������
	private int maxStatementsPerConnection;

	//���ӵ�������ʱ��
	private int maxIdleTime;

	//������ӵ�ʱ����
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
