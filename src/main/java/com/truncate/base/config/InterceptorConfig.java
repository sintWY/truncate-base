package com.truncate.base.config;

import com.truncate.base.exception.CommonException;
import com.truncate.base.exception.ErrorCode;
import com.truncate.base.interceptor.Interceptor;
import com.truncate.base.util.ReflectUtil;
import com.truncate.base.util.XmlUtil;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 描述: //TODO 类描述
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月29日
 * 创建时间: 21:48
 */
public class InterceptorConfig
{

	private static final Logger logger = LoggerFactory.getLogger(InterceptorConfig.class);

	private static final String INTERCEPTOR_CONFIG_FILE = "interceptor.xml";

	private static final Map<String, Interceptor> INTERCEPTOR_MAP = new HashMap<String, Interceptor>();

	private static final Map<String, InterceptorStack> INTERCEPTOR_STACK_MAP = new HashMap<String, InterceptorStack>();

	static
	{
		loadInterceptorConfig();
	}

	private static void loadInterceptorConfig()
	{
		Document document = XmlUtil.getDocumentFromClassPath(INTERCEPTOR_CONFIG_FILE);
		Element rootElement = document.getRootElement();
		if(rootElement == null)
		{
			throw new CommonException(ErrorCode.FILE_CONTENT_FORMAT_ERROR);
		}

		//拦截器列表
		Element interceptorsElement = rootElement.element("interceptors");
		if(interceptorsElement == null)
		{
			throw new CommonException(ErrorCode.FILE_CONTENT_FORMAT_ERROR);
		}
		List<Element> interceptorElements = interceptorsElement.elements("iterceptor");
		if(interceptorElements != null && !interceptorElements.isEmpty())
		{
			for(Element interceptorElement : interceptorElements)
			{
				String name = interceptorElement.attributeValue("name");
				String className = interceptorElement.attributeValue("class");
				//				String description = interceptorElement.attributeValue("description");
				Interceptor interceptor = (Interceptor) ReflectUtil.reflectObject(className);
				INTERCEPTOR_MAP.put(name, interceptor);
			}
		}
		else
		{
			logger.warn("未配置任何拦截器!");
		}

		//拦截器栈节点
		Element interceptorStacksElement = rootElement.element("interceptor-stacks");
		List<Element> interceptorStackElements = interceptorStacksElement.elements("interceptor-stack");
		if(interceptorStackElements != null && !interceptorStackElements.isEmpty())
		{
			for(Element interceptorStackElement : interceptorStackElements)
			{
				String stackName = interceptorStackElement.attributeValue("name");
				InterceptorStack interceptorStack = new InterceptorStack();
				interceptorStack.setStackName(stackName);

				List<Element> refInterceptorElements = interceptorStackElement.elements("item");
				if(refInterceptorElements != null && !refInterceptorElements.isEmpty())
				{
					for(Element refInterceptorElement : refInterceptorElements)
					{
						String refInterceptorName = refInterceptorElement.attributeValue("ref");
						Interceptor refInterceptor = INTERCEPTOR_MAP.get(refInterceptorName);
						interceptorStack.addtInterceptor(refInterceptor);
					}
				}

				INTERCEPTOR_STACK_MAP.put(stackName, interceptorStack);
			}
		}
		else
		{
			logger.warn("未配置任何拦截器栈!");
		}

		//默认拦截器栈节点
		Element defaultInterceptorStackElement = rootElement.element("default-interceptor-stack");
		InterceptorStack defaultInterceptorStack;
		if(defaultInterceptorStackElement != null)
		{
			String defaultInterceptorStackName = defaultInterceptorStackElement.attributeValue("ref");
			defaultInterceptorStack = INTERCEPTOR_STACK_MAP.get(defaultInterceptorStackName);
		}
		else
		{
			defaultInterceptorStack = new InterceptorStack();
			logger.warn("未配置默认拦截器栈!");
		}

		//请求拦截器节点
		List<Element> invokeInterceptorElements = rootElement.elements("invoke-interceptor");
		if(invokeInterceptorElements != null && !invokeInterceptorElements.isEmpty())
		{
			for(Element invokeInterceptorElement : invokeInterceptorElements)
			{
				List<Integer> filterFuncNos = new ArrayList<Integer>();
				String filterText = invokeInterceptorElement.elementText("filter-function");
				if(StringUtils.isNotEmpty(filterText))
				{
					String[] filterFunctionArr = filterText.split("\\|");
					for(String funcNo : filterFunctionArr)
					{
						filterFuncNos.add(Integer.valueOf(funcNo));
					}
				}

				//拦截功能号组
				String groupName = invokeInterceptorElement.attributeValue("groupName");
				List<FunctionItem> functionItemList = FunctionConfig.getGroupFunctionList(groupName);
				if(functionItemList != null && !functionItemList.isEmpty())
				{
					//
					LinkedHashSet<Interceptor> tempInterceptorSet = new LinkedHashSet<Interceptor>();

					//配置的拦截器栈
					List<Element> refInterceptorStackElements = invokeInterceptorElement.elements("ref-interceptor-stack");
					if(refInterceptorStackElements != null && !refInterceptorStackElements.isEmpty())
					{
						for(Element refInterceptorStackElement : refInterceptorStackElements)
						{
							String refInterceptorStackName = refInterceptorStackElement.getTextTrim();
							InterceptorStack refInterceptorStack = INTERCEPTOR_STACK_MAP.get(refInterceptorStackName);
							if(refInterceptorStack != null)
							{
								LinkedHashSet<Interceptor> interceptorSet = refInterceptorStack.getInterceptors();
								if(interceptorSet != null && !interceptorSet.isEmpty())
								{
									tempInterceptorSet.addAll(interceptorSet);
								}
							}
						}
					}

					//配置的拦截器
					List<Element> refInterceptorElements = invokeInterceptorElement.elements("ref-interceptor");
					if(refInterceptorElements != null && !refInterceptorElements.isEmpty())
					{
						for(Element refInterceptorElement : refInterceptorElements)
						{
							String refInterceptorName = refInterceptorElement.getTextTrim();
							Interceptor interceptor = INTERCEPTOR_MAP.get(refInterceptorName);
							if(interceptor != null)
							{
								tempInterceptorSet.add(interceptor);
							}
							else
							{
								logger.warn("功能号组[{}]配置的拦截器[{}]不存在!", new String[] { groupName, refInterceptorName });
							}
						}
					}

					for(FunctionItem functionItem : functionItemList)
					{
						if(!filterFuncNos.contains(functionItem.getId()))
						{
							functionItem.addInterceptors(tempInterceptorSet);
						}

						//加载默认拦截器栈
						functionItem.addInterceptors(defaultInterceptorStack.getInterceptors());
					}
				}
				else
				{
					logger.warn("功能号组[{}]中没有配置功能号信息!", new Object[] { groupName });
				}
			}
		}
		else
		{
			logger.warn("未配置请求拦截器!");
		}
	}

	public static void init()
	{
	}
}
