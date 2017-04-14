package com.truncate.base.jdbc.aspect;

import com.truncate.base.util.JsonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ����: SQLִ�д���
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��04��14��
 * ����ʱ��: 14:55
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
			logger.debug("ִ�з�����{}. ����Ĳ�����{}", new Object[] { proceedingJoinPoint.toShortString(), JsonUtil.toString(proceedingJoinPoint.getArgs()) });
		}
		long start = System.currentTimeMillis();
		Object result = proceedingJoinPoint.proceed();
		long end = System.currentTimeMillis();
		long cost = end - start;
		if(logger.isDebugEnabled())
		{
			logger.debug("ִ�л��ѵ�ʱ�䣺{}ms. ���ؽ����{}", new Object[] { cost, JsonUtil.toString(result) });
		}
		return result;
	}
}
