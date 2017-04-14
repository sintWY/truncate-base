package com.truncate.base.interceptor;

import com.truncate.base.domain.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * ����: ����������
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��29��
 * ����ʱ��: 19:19
 */
public class ParamInterceptor implements Interceptor
{

	private static final Logger logger = LoggerFactory.getLogger(ParamInterceptor.class);

	@Override
	public ResultVo intercept(ActionInvocation actionInvocation)
	{
		logger.info("�������������...");
		Map<String, String> paraMap = actionInvocation.getRequestParam().getParaMap();
		int funcNo = actionInvocation.getFuncNo();
		String serialNo = actionInvocation.getRequestParam().getSerialNo();
		String requestIp = actionInvocation.getRequestParam().getRequestIp();
		if(logger.isInfoEnabled())
		{
			logger.info("����ͷ:[serialNo={},requestIp={}],���ܺ�:[{}],���:[{}]", new Object[] { serialNo, requestIp, funcNo, paraMap });
		}
		return actionInvocation.invoke();
	}
}
