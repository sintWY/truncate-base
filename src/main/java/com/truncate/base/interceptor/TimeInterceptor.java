package com.truncate.base.interceptor;

import com.truncate.base.domain.ResultVo;
import com.truncate.base.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ����: �ӿ�ʱ��������
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��04��01��
 * ����ʱ��: 18:35
 */
public class TimeInterceptor implements Interceptor
{

	private static final Logger logger = LoggerFactory.getLogger(TimeInterceptor.class);

	@Override
	public ResultVo intercept(ActionInvocation actionInvocation)
	{
		long start = System.currentTimeMillis();
		ResultVo resultVo = actionInvocation.invoke();
		long end = System.currentTimeMillis();
		logger.info("���ܺ�[{}]����ʱ��Ϊ��{}ms,���صĽ��Ϊ��{}", new Object[] { actionInvocation.getFuncNo(), end - start, JsonUtil.toString(resultVo) });
		return resultVo;
	}
}
