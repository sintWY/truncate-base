package com.truncate.base.interceptor;

import com.truncate.base.config.FunctionItem;
import com.truncate.base.domain.ResultVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * ����: �������
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��29��
 * ����ʱ��: 18:16
 */
public interface ActionInvocation
{

	ResultVo invoke();

	HttpServletRequest getRequest();

	HttpServletResponse getResponse();

	FunctionItem getFunctionItem();

	int getFuncNo();

	RequestParam getRequestParam();
}
