package com.truncate.base.interceptor;

import com.truncate.base.domain.ResultVo;
import com.truncate.base.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述: 接口时长拦截器
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年04月01日
 * 创建时间: 18:35
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
		logger.info("功能号[{}]调用时间为：{}ms,返回的结果为：{}", new Object[] { actionInvocation.getFuncNo(), end - start, JsonUtil.toString(resultVo) });
		return resultVo;
	}
}
