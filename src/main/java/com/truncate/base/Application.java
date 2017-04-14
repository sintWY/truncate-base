package com.truncate.base;

import com.truncate.base.jdbc.DBSourceManager;

import javax.servlet.ServletContext;

/**
 * ����: Ӧ�������Ϣ
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��29��
 * ����ʱ��: 19:31
 */
public abstract class Application
{

	//Ӧ�ø�·��
	private static String rootPath;

	//servlet������
	private static ServletContext servletContext;

	public static String getRootPath()
	{
		return rootPath;
	}

	public static ServletContext getServletContext()
	{
		return servletContext;
	}

	/**
	 *@���������ó�ʼ������
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/31
	 *@ʱ��:22:03
	 */
	public static void init(ServletContext servletContext)
	{
		Application.servletContext = servletContext;
		Application.rootPath = servletContext.getRealPath("/");

		DBSourceManager.init();
	}
}
