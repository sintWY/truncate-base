package com.truncate.base.interceptor;

import com.truncate.base.domain.ResultVo;
import com.truncate.base.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 描述: 参数拦截器
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月29日
 * 创建时间: 19:19
 */
public class ParamInterceptor implements Interceptor
{

	private static final Logger logger = LoggerFactory.getLogger(ParamInterceptor.class);

	@Override
	public ResultVo intercept(ActionInvocation actionInvocation)
	{
		logger.info("进入参数拦截器...");
		Map<String, String> paraMap = actionInvocation.getParaMap();
		int funcNo = actionInvocation.getFuncNo();
		if(logger.isInfoEnabled())
		{
			logger.info("功能号[{}]的入参为：{}", funcNo, JsonUtil.toString(paraMap));
		}
		return actionInvocation.invoke();
	}
}
