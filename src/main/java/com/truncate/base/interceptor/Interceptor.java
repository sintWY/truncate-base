package com.truncate.base.interceptor;

import com.truncate.base.domain.ResultVo;

/**
 * 描述: 拦截器
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月29日
 * 创建时间: 18:16
 */
public interface Interceptor
{

	ResultVo intercept(ActionInvocation actionInvocation);
}
