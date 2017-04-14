package com.truncate.base.interceptor;

import com.truncate.base.config.FunctionItem;
import com.truncate.base.domain.ResultVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 描述: 请求对象
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月29日
 * 创建时间: 18:16
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
