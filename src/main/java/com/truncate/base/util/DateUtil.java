package com.truncate.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ����: ���ڹ�����
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��25��
 * ����ʱ��: 0:52
 */
public class DateUtil
{

	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	//Ĭ�ϸ�ʽ
	private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 *@��������ʽ�����ڳ�ָ����ʽ
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/25
	 *@ʱ��:1:39
	 */
	public static String format(Date date, String pattern)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}

	/**
	 *@��������ʽ������ΪĬ�ϸ�ʽ
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/25
	 *@ʱ��:1:40
	 *
	 */
	public static String format(Date date)
	{
		return DEFAULT_DATE_FORMAT.format(date);
	}

	/**
	 *@���������ַ�������ָ����ʽתΪ����
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/25
	 *@ʱ��:1:40
	 *
	 */
	public static Date parse(String date, String pattern)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try
		{
			return simpleDateFormat.parse(date);
		}
		catch(ParseException e)
		{
			throw new IllegalArgumentException("���ڸ�ʽ����ȷ��");
		}
	}

	/**
	 *@���������ַ�������Ĭ�ϸ�ʽתΪ����
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/25
	 *@ʱ��:1:40
	 *
	 */
	public static Date parse(String date)
	{
		try
		{
			return DEFAULT_DATE_FORMAT.parse(date);
		}
		catch(ParseException e)
		{
			throw new IllegalArgumentException("���ڸ�ʽ����ȷ��");
		}
	}
}
