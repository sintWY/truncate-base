package com.truncate.base.domain;

import com.truncate.base.util.ConvertUtil;

import java.util.HashMap;

/**
 * 描述: 数据行
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月30日
 * 创建时间: 15:20
 */
public class DataRow extends HashMap
{

	public void set(String key, Object value)
	{
		this.put(key, value);
	}

	/**
	 *@描述：获取字符串
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/30
	 *@时间:15:27
	 *
	 */
	public String getString(String key)
	{
		Object object = get(key);
		if(object == null)
		{
			return "";
		}
		if(object instanceof String)
		{
			return (String) object;
		}
		else
		{
			return object.toString();
		}
	}

	/**
	 *@描述：获取int类型
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/30
	 *@时间:15:29
	 *
	 */
	public int getInt(String key)
	{
		String value = getString(key);
		return ConvertUtil.str2Int(value);
	}

	/**
	 *@描述：获取long类型
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/30
	 *@时间:15:30
	 *
	 */
	public long getLong(String key)
	{
		String value = getString(key);
		return ConvertUtil.str2Long(value);
	}

	/**
	 *@描述：获取double类型
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/30
	 *@时间:15:30
	 *
	 */
	public double getDouble(String key)
	{
		String value = getString(key);
		return ConvertUtil.str2Double(value);
	}

}
