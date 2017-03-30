package com.truncate.base.util;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述: request工具类
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月28日
 * 创建时间: 21:55
 */
public class RequestUtil
{

	/**
	 *@描述：从reuqest中获取int数据
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/28
	 *@时间:21:57
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
	 *@描述：从reuqest中获取string数据
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/28
	 *@时间:21:59
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
