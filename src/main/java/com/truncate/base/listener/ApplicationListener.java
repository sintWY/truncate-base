package com.truncate.base.listener;

import com.truncate.base.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * ����:	servlet�����ļ�����
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��29��
 * ����ʱ��: 19:33
 */
public class ApplicationListener implements ServletContextListener
{

	private static final Logger logger = LoggerFactory.getLogger(ApplicationListener.class);

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent)
	{
		if(logger.isInfoEnabled())
		{
			logger.info("Application is start...");
		}
		ServletContext context = servletContextEvent.getServletContext();
		Application.setServletContext(context);
		Application.setRootPath(context.getRealPath("/"));
		init();
	}

	public void init()
	{

	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent)
	{
		if(logger.isInfoEnabled())
		{
			logger.info("Application is stop...");
		}
	}
}
