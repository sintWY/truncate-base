package com.truncate.base.interceptor;

import com.truncate.base.domain.ResultVo;
import com.truncate.base.exception.CommonException;
import com.truncate.base.exception.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ����: Ȩ��������
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��29��
 * ����ʱ��: 23:58
 */
public class SecurityInterceptor implements Interceptor
{

	private static final Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);

	@Override
	public ResultVo intercept(ActionInvocation actionInvocation)
	{
		logger.info("����Ȩ��������...");
		throw new CommonException(ErrorCode.NOT_LOGIN_ERROR);
	}
}
