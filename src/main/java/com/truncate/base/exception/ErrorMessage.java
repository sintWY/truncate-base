package com.truncate.base.exception;

import java.lang.annotation.*;

/**
 * 描述: 错误代码描述注解
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月28日
 * 创建时间: 22:10
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ErrorMessage
{

	String value();
}
