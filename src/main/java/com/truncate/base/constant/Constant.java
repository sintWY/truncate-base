package com.truncate.base.constant;

/**
 * 描述: 通用常量
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月29日
 * 创建时间: 14:19
 */
public class Constant
{

	/**
	 *@描述：字符集
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/31
	 *@时间:22:55
	 *
	 */
	public static final class Charset
	{

		public static final String UTF8 = "UTF-8";

		public static final String GBK = "GBK";
	}

	/**
	 *@描述：功能号类型
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/31
	 *@时间:22:55
	 *
	 */
	public static final class FunctionType
	{

		//本地接口
		public static final String LOCAL = "0";

		//三方接口
		public static final String THIRD = "1";
	}

	public static final class DatabaseType
	{

		public static final String MYSQL = "mysql";

		public static final String ORACLE = "oracle";

		public static final String DB2 = "db2";

		public static final String SQLSERVER = "sqlserver";
	}

}
