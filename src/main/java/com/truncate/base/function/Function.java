package com.truncate.base.function;

import com.truncate.base.config.FunctionItem;
import com.truncate.base.domain.ResultVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述: 功能接口
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月29日
 * 创建时间: 15:10
 */
public interface Function
{

	ResultVo execute(FunctionItem functionItem, HttpServletRequest request, HttpServletResponse response);
}
