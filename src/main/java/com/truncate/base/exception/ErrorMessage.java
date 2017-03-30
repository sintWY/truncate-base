package com.truncate.base.exception;

import java.lang.annotation.*;

/**
 * ����: �����������ע��
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��28��
 * ����ʱ��: 22:10
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ErrorMessage
{

	String value();
}
