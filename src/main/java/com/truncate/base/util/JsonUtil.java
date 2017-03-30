package com.truncate.base.util;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * ����: Json������
 * ��Ȩ: Copyright (c) 2016
 * ��˾:
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2016��12��29��
 * ����ʱ��: 15:38
 */
public class JsonUtil
{

	private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 *@������������תΪjson�ַ����
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/29
	 *@ʱ��:16:27
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
			logger.error("�������л���json�ַ�������!", e);
		}
		return "";
	}

	/**
	 *@�������ַ���תΪ����
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/29
	 *@ʱ��:16:27
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
			logger.error("json�ַ���תΪ����ʧ��!", e);
		}
		return t;
	}
}
