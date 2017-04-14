package com.truncate.base.filter;

import com.truncate.base.constant.Constant;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * ����: //TODO ������
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��31��
 * ����ʱ��: 21:26
 */
public class CharacterEncodingFilter implements Filter
{

	//����
	private String encoding;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		this.encoding = filterConfig.getInitParameter("encoding");
		if(StringUtils.isEmpty(encoding))
		{
			this.encoding = Constant.Charset.UTF8;
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException
	{
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		filterChain.doFilter(request, response);
	}

	@Override
	public void destroy()
	{
	}
}
