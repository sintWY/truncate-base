package com.truncate.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 描述: //TODO 类描述
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月30日
 * 创建时间: 0:37
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
			logger.error("写数据到客户端出错!", e);
		}
	}
}
