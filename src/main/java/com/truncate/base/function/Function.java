package com.truncate.base.function;

import com.truncate.base.domain.ResultVo;
import com.truncate.base.interceptor.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ����: ���ܽӿ�
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��29��
 * ����ʱ��: 15:10
 */
public interface Function
{

	ResultVo execute(HttpServletRequest request, HttpServletResponse response, RequestParam requestParam);
}
