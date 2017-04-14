package com.truncate.base.util;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * ����: xml������
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��24��
 * ����ʱ��: 23:36
 */
public class XmlUtil
{

	private static final Logger logger = LoggerFactory.getLogger(XmlUtil.class);

	/**
	 *@��������xml�ļ��к�ȥDocument����
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/25
	 *@ʱ��:1:41
	 *
	 */
	public static Document getDocument(String filePath)
	{
		if(StringUtils.isNotEmpty(filePath))
		{
			File xmlFile = new File(filePath);
			if(xmlFile.exists())
			{
				SAXReader reader = new SAXReader();
				try
				{
					return reader.read(xmlFile);
				}
				catch(DocumentException e)
				{
					logger.error("��ȡ��xml�ļ�����", e);
				}
			}
			else
			{
				throw new IllegalArgumentException("�ļ�[" + filePath + "]������!");
			}
		}
		else
		{
			throw new IllegalArgumentException("�ļ�·������Ϊ��!");
		}
		throw new IllegalArgumentException("���ص��ĵ�����Ϊ��!");
	}

	/**
	 *@��������classes�¼����ļ�
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/29
	 *@ʱ��:12:49
	 *
	 */
	public static Document getDocumentFromClassPath(String path)
	{
		String classPath = XmlUtil.class.getResource("/").getPath();
		return getDocument(classPath + path);
	}
}
