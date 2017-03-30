package com.truncate.base.util;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 描述: Json工具类
 * 版权: Copyright (c) 2016
 * 公司:
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2016年12月29日
 * 创建时间: 15:38
 */
public class JsonUtil
{

	private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 *@描述：将对象转为json字符输出
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/29
	 *@时间:16:27
	 *
	 */
	public static String toString(Object object)
	{
		try
		{
			return objectMapper.writeValueAsString(object);
		}
		catch(IOException e)
		{
			logger.error("对象序列化成json字符串出错!", e);
		}
		return "";
	}

	/**
	 *@描述：字符串转为对象
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/29
	 *@时间:16:27
	 *
	 */
	public static <T extends Object> T toObject(String json, Class clazz)
	{
		T t = null;
		try
		{
			t = (T) objectMapper.readValue(json, clazz);
		}
		catch(IOException e)
		{
			logger.error("json字符串转为对象失败!", e);
		}
		return t;
	}
}
