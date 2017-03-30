package com.truncate.base.domain;

import com.truncate.base.util.ConvertUtil;

import java.util.HashMap;

/**
 * ����: ������
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��30��
 * ����ʱ��: 15:20
 */
public class DataRow extends HashMap
{

	public void set(String key, Object value)
	{
		this.put(key, value);
	}

	/**
	 *@��������ȡ�ַ���
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/30
	 *@ʱ��:15:27
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
	 *@��������ȡint����
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/30
	 *@ʱ��:15:29
	 *
	 */
	public int getInt(String key)
	{
		String value = getString(key);
		return ConvertUtil.str2Int(value);
	}

	/**
	 *@��������ȡlong����
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/30
	 *@ʱ��:15:30
	 *
	 */
	public long getLong(String key)
	{
		String value = getString(key);
		return ConvertUtil.str2Long(value);
	}

	/**
	 *@��������ȡdouble����
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/30
	 *@ʱ��:15:30
	 *
	 */
	public double getDouble(String key)
	{
		String value = getString(key);
		return ConvertUtil.str2Double(value);
	}

}
