package com.truncate.base.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 描述: Spring工具类
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月31日
 * 创建时间: 20:45
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
	 *@描述：获取spring上下文
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/31
	 *@时间:20:51
	 *
	 */
	public static ApplicationContext getContext()
	{
		return ContextEnum.SPRING_CONTEXT.context;
	}

	/**
	 *@描述：返回对应的bean
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/31
	 *@时间:20:58
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
