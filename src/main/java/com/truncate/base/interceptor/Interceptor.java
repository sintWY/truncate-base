package com.truncate.base.interceptor;

import com.truncate.base.domain.ResultVo;

/**
 * ����: ������
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��29��
 * ����ʱ��: 18:16
 */
public interface Interceptor
{

	ResultVo intercept(ActionInvocation actionInvocation);
}
