package com.truncate.base.util;

import com.truncate.base.exception.CommonException;
import com.truncate.base.exception.ErrorCode;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述: 转换工具类
 * 版权: Copyright (c) 2017
 * 作者: 王功俊(wanggj@thinkive.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月30日
 * 创建时间: 13:48
 */
public class ConvertUtil
{

	private static final Logger logger = LoggerFactory.getLogger(ConvertUtil.class);

	/**
	 *@描述：string转int
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/30
	 *@时间:13:52
	 *
	 */
	public static int str2Int(String value, int defaultValue)
	{
		if(StringUtils.isEmpty(value))
		{
			logger.warn("字符转成int类型的参数为空,返回默认值!");
			return defaultValue;
		}
		else
		{
			try
			{
				return Integer.valueOf(value);
			}
			catch(NumberFormatException e)
			{
				logger.warn("字符[{}]转成int类型的失败,返回默认值!", new Object[] { value });
				return defaultValue;
			}
		}
	}

	public static int str2Int(String value)
	{
		return str2Int(value, 0);
	}

	/**
	 *@描述：string转long
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/30
	 *@时间:14:00
	 *
	 */
	public static long str2Long(String value, long defaultValue)
	{
		if(StringUtils.isEmpty(value))
		{
			logger.warn("字符转成long类型的参数为空,返回默认值!");
			return defaultValue;
		}
		else
		{
			try
			{
				return Long.valueOf(value);
			}
			catch(NumberFormatException e)
			{
				logger.warn("字符[{}]转成long类型的失败,返回默认值!", new Object[] { value });
				return defaultValue;
			}
		}
	}

	public static long str2Long(String value)
	{
		return str2Long(value, 0L);
	}

	/**
	 *@描述：string转double
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/30
	 *@时间:14:06
	 *
	 */
	public static double str2Double(String value, double defaultValue)
	{
		if(StringUtils.isEmpty(value))
		{
			logger.warn("字符转成double类型的参数为空,返回默认值!");
			return defaultValue;
		}
		else
		{
			try
			{
				return Double.valueOf(value);
			}
			catch(NumberFormatException e)
			{
				logger.warn("字符[{}]转成double类型的失败,返回默认值!", new Object[] { value });
				return defaultValue;
			}
		}
	}

	public static double str2Double(String value)
	{
		return str2Double(value, 0.0D);
	}

	/**
	 *@描述：将一个数分解成多个2的幂相加的数
	 * 		  如：15=8+4+2+1=2^3+2^2+2^1+2^0
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/30
	 *@时间:14:12
	 *
	 */
	public static List<Long> splitNumber(long value)
	{
		String binaryStr = Long.toBinaryString(value);
		int length = binaryStr.length();
		List<Long> resultList = new ArrayList<Long>();
		for(int i = 0; i < length; i++)
		{
			char temp = binaryStr.charAt(i);
			if(temp != '0')
			{
				//等价于：2^(leng-i-1)
				long perNum = 1 << length - i - 1;
				resultList.add(perNum);
			}
		}
		return resultList;
	}

	/**
	 *@描述：创建重复的字符
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/30
	 *@时间:14:50
	 *
	 */
	public static String createRepeat(String mark, String splitPoint, int repeatCount)
	{
		if(repeatCount <= 0)
		{
			throw new CommonException(ErrorCode.NUMBER_MUST_EXCEED_ERROR, new Object[] { 0 });
		}
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < repeatCount; i++)
		{
			builder.append(mark);
			builder.append(splitPoint);
		}
		return builder.substring(0, builder.length() - 1);
	}

}
