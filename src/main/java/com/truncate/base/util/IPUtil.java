package com.truncate.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * ����: ip������
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��04��01��
 * ����ʱ��: 10:39
 */
public class IPUtil
{

	private static final Logger logger = LoggerFactory.getLogger(IPUtil.class);

	/**
	 *@��������request�л�ȡip
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/4/1
	 *@ʱ��:10:40
	 *
	 */
	public static String getRequestIp(HttpServletRequest request)
	{
		String ipAddress = request.getHeader("x-forwarded-for");
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress))
		{
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress))
		{
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress))
		{
			ipAddress = request.getRemoteAddr();
			if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1"))
			{
				//��������ȡ�������õ�IP
				InetAddress inet = null;
				try
				{
					inet = InetAddress.getLocalHost();
					ipAddress = inet.getHostAddress();
				}
				catch(UnknownHostException e)
				{
					logger.warn("��ȡ�û�IPʧ��,����ԭʼIP��");
					ipAddress = "127.0.0.1";
				}
			}
		}
		//����ͨ�����������������һ��IPΪ�ͻ�����ʵIP,���IP����','�ָ�
		if(ipAddress != null && ipAddress.length() > 15)
		{
			if(ipAddress.indexOf(",") > 0)
			{
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}
}
