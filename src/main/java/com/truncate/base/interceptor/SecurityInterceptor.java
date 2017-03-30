package com.truncate.base.interceptor;

import com.truncate.base.domain.ResultVo;
import com.truncate.base.exception.CommonException;
import com.truncate.base.exception.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述: 权限拦截器
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月29日
 * 创建时间: 23:58
 */
public class SecurityInterceptor implements Interceptor
{

	private static final Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);

	@Override
	public ResultVo intercept(ActionInvocation actionInvocation)
	{
		logger.info("进入权限拦截器...");
		throw new CommonException(ErrorCode.NOT_LOGIN_ERROR);
	}
}
