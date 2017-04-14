package com.truncate.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 描述: ip工具类
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年04月01日
 * 创建时间: 10:39
 */
public class IPUtil
{

	private static final Logger logger = LoggerFactory.getLogger(IPUtil.class);

	/**
	 *@描述：从request中获取ip
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/4/1
	 *@时间:10:40
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
				//根据网卡取本机配置的IP
				InetAddress inet = null;
				try
				{
					inet = InetAddress.getLocalHost();
					ipAddress = inet.getHostAddress();
				}
				catch(UnknownHostException e)
				{
					logger.warn("获取用户IP失败,返回原始IP。");
					ipAddress = "127.0.0.1";
				}
			}
		}
		//对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
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
