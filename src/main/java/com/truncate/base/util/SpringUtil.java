package com.truncate.base.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ����: Spring������
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��31��
 * ����ʱ��: 20:45
 */
public class SpringUtil
{

	private enum ContextEnum
	{
		SPRING_CONTEXT;

		private ApplicationContext context;

		ContextEnum()
		{
			this.context = new ClassPathXmlApplicationContext("spring-context.xml");
		}
	}

	/**
	 *@��������ȡspring������
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/31
	 *@ʱ��:20:51
	 *
	 */
	public static ApplicationContext getContext()
	{
		return ContextEnum.SPRING_CONTEXT.context;
	}

	/**
	 *@���������ض�Ӧ��bean
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/31
	 *@ʱ��:20:58
	 *
	 */
	public static <T extends Object> T getBean(String name)
	{
		return (T) getContext().getBean(name);
	}

	public static void main(String[] args)
	{
		getContext();
	}
}
