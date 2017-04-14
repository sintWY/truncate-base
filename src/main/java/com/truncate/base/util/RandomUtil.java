package com.truncate.base.util;

import java.util.Random;
import java.util.UUID;

/**
 * 描述: 随机字符生成工具类
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月31日
 * 创建时间: 22:14
 */
public class RandomUtil
{

	private static final String CHAR_MARK_TARGET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private static final String DIGIT_MARK_TARGET = "0123456789";

	private static final Random DEFAULT_RANDOM = new Random();

	/**
	 *@描述：生成UUID
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/31
	 *@时间:22:15
	 *
	 */
	public static String getUUID()
	{
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	/**
	 *@描述：随机生成纯字符
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/31
	 *@时间:22:19
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
	 *@描述：随机生成纯数字
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/31
	 *@时间:22:21
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
