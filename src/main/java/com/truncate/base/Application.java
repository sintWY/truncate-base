package com.truncate.base;

import javax.servlet.ServletContext;

/**
 * ����: Ӧ�������Ϣ
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��29��
 * ����ʱ��: 19:31
 */
public class Application
{

	private static String rootPath;

	private static ServletContext servletContext;

	public static String getRootPath()
	{
		return rootPath;
	}

	public static void setRootPath(String rootPath)
	{
		Application.rootPath = rootPath;
	}

	public static ServletContext getServletContext()
	{
		return servletContext;
	}

	public static void setServletContext(ServletContext servletContext)
	{
		Application.servletContext = servletContext;
	}
}
