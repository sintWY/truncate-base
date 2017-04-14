package com.truncate.base.util;

import java.util.Random;
import java.util.UUID;

/**
 * ����: ����ַ����ɹ�����
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��31��
 * ����ʱ��: 22:14
 */
public class RandomUtil
{

	private static final String CHAR_MARK_TARGET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private static final String DIGIT_MARK_TARGET = "0123456789";

	private static final Random DEFAULT_RANDOM = new Random();

	/**
	 *@����������UUID
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/31
	 *@ʱ��:22:15
	 *
	 */
	public static String getUUID()
	{
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	/**
	 *@������������ɴ��ַ�
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/31
	 *@ʱ��:22:19
	 *
	 */
	public static String randomChar(int length)
	{
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < length; i++)
		{
			int index = DEFAULT_RANDOM.nextInt(CHAR_MARK_TARGET.length());
			builder.append(CHAR_MARK_TARGET.charAt(index));
		}
		return builder.toString();
	}

	/**
	 *@������������ɴ�����
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/31
	 *@ʱ��:22:21
	 *
	 */
	public static String randomDigit(int length)
	{
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < length; i++)
		{
			int index = DEFAULT_RANDOM.nextInt(DIGIT_MARK_TARGET.length());
			builder.append(DIGIT_MARK_TARGET.charAt(index));
		}
		return builder.toString();
	}
}
