package com.truncate.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ����: //TODO ������
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��30��
 * ����ʱ��: 0:37
 */
public class ResponseUtil
{

	private static final Logger logger = LoggerFactory.getLogger(ResponseUtil.class);

	public static void write(String content, HttpServletResponse response)
	{
		try
		{
			PrintWriter writer = response.getWriter();

			writer.write(content);
		}
		catch(IOException e)
		{
			logger.error("д���ݵ��ͻ��˳���!", e);
		}
	}
}
