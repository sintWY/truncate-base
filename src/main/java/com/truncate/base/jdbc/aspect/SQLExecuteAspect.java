package com.truncate.base.jdbc.aspect;

import com.truncate.base.util.JsonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述: SQL执行代理
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年04月14日
 * 创建时间: 14:55
 */
@Aspect
public class SQLExecuteAspect
{

	private static final Logger logger = LoggerFactory.getLogger(SQLExecuteAspect.class);

	@Pointcut("execution(public * *(..)),within(* com.truncate.base.jdbc.dbsession.DBSession+")
	public void execute()
	{
	}

	@Around("execute()")
	public Object time(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
	{
		if(logger.isDebugEnabled())
		{
			logger.debug("执行方法：{}. 输入的参数：{}", new Object[] { proceedingJoinPoint.toShortString(), JsonUtil.toString(proceedingJoinPoint.getArgs()) });
		}
		long start = System.currentTimeMillis();
		Object result = proceedingJoinPoint.proceed();
		long end = System.currentTimeMillis();
		long cost = end - start;
		if(logger.isDebugEnabled())
		{
			logger.debug("执行花费的时间：{}ms. 返回结果：{}", new Object[] { cost, JsonUtil.toString(result) });
		}
		return result;
	}
}
