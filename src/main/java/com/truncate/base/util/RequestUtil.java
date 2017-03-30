package com.truncate.base.util;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * ����: request������
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��28��
 * ����ʱ��: 21:55
 */
public class RequestUtil
{

	/**
	 *@��������reuqest�л�ȡint����
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/28
	 *@ʱ��:21:57
	 *
	 */
	public static int getInt(HttpServletRequest request, String name)
	{
		int intValue = 0;
		String value = request.getParameter(name);
		if(StringUtils.isNotEmpty(value))
		{
			intValue = Integer.valueOf(value);
		}
		return intValue;
	}

	/**
	 *@��������reuqest�л�ȡstring����
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/28
	 *@ʱ��:21:59
	 *
	 */
	public static String getString(HttpServletRequest request, String name)
	{
		String value = request.getParameter(name);
		if(StringUtils.isEmpty(value))
		{
			value = "";
		}
		return value;
	}
}
