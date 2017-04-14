package com.truncate.base.util;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ����: ���乤����
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��24��
 * ����ʱ��: 17:38
 */
public class ReflectUtil
{

	private static final Logger logger = LoggerFactory.getLogger(ReflectUtil.class);

	/**
	 *@���������������������
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/25
	 *@ʱ��:1:41
	 *
	 */
	public static Object reflectObject(String className)
	{
		if(StringUtils.isEmpty(className))
		{
			throw new IllegalArgumentException("ʵ���಻��Ϊ��!");
		}
		try
		{
			return Class.forName(className).newInstance();
		}
		catch(ClassNotFoundException e)
		{
			logger.error("ʵ����[" + className + "]���ܲ�����!", e);
		}
		catch(IllegalAccessException e)
		{
			logger.error("ʵ����[" + className + "]��ʼ��ʧ��!", e);
		}
		catch(InstantiationException e)
		{
			logger.error("ʵ����[" + className + "]��ʼ��ʧ��!", e);
		}
		throw new RuntimeException("����õ��Ķ���Ϊ��!");
	}
}
