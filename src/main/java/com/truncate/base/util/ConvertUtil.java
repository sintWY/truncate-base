package com.truncate.base.util;

import com.truncate.base.exception.CommonException;
import com.truncate.base.exception.ErrorCode;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * ����: ת��������
 * ��Ȩ: Copyright (c) 2017
 * ����: ������(wanggj@thinkive.com)
 * �汾: 1.0 
 * ��������: 2017��03��30��
 * ����ʱ��: 13:48
 */
public class ConvertUtil
{

	private static final Logger logger = LoggerFactory.getLogger(ConvertUtil.class);

	/**
	 *@������stringתint
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/30
	 *@ʱ��:13:52
	 *
	 */
	public static int str2Int(String value, int defaultValue)
	{
		if(StringUtils.isEmpty(value))
		{
			logger.warn("�ַ�ת��int���͵Ĳ���Ϊ��,����Ĭ��ֵ!");
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
				logger.warn("�ַ�[{}]ת��int���͵�ʧ��,����Ĭ��ֵ!", new Object[] { value });
				return defaultValue;
			}
		}
	}

	public static int str2Int(String value)
	{
		return str2Int(value, 0);
	}

	/**
	 *@������stringתlong
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/30
	 *@ʱ��:14:00
	 *
	 */
	public static long str2Long(String value, long defaultValue)
	{
		if(StringUtils.isEmpty(value))
		{
			logger.warn("�ַ�ת��long���͵Ĳ���Ϊ��,����Ĭ��ֵ!");
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
				logger.warn("�ַ�[{}]ת��long���͵�ʧ��,����Ĭ��ֵ!", new Object[] { value });
				return defaultValue;
			}
		}
	}

	public static long str2Long(String value)
	{
		return str2Long(value, 0L);
	}

	/**
	 *@������stringתdouble
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/30
	 *@ʱ��:14:06
	 *
	 */
	public static double str2Double(String value, double defaultValue)
	{
		if(StringUtils.isEmpty(value))
		{
			logger.warn("�ַ�ת��double���͵Ĳ���Ϊ��,����Ĭ��ֵ!");
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
				logger.warn("�ַ�[{}]ת��double���͵�ʧ��,����Ĭ��ֵ!", new Object[] { value });
				return defaultValue;
			}
		}
	}

	public static double str2Double(String value)
	{
		return str2Double(value, 0.0D);
	}

	/**
	 *@��������һ�����ֽ�ɶ��2������ӵ���
	 * 		  �磺15=8+4+2+1=2^3+2^2+2^1+2^0
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/30
	 *@ʱ��:14:12
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
				//�ȼ��ڣ�2^(leng-i-1)
				long perNum = 1 << length - i - 1;
				resultList.add(perNum);
			}
		}
		return resultList;
	}

	/**
	 *@�����������ظ����ַ�
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/30
	 *@ʱ��:14:50
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
