package com.duoju.daq.interceptor;

import java.net.URLDecoder;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.duoju.daq.util.ConstantsUtil;
import com.xxx.common.util.ip.IpUtil;


/**
 * 拦截器
 * Project:     <duoju-app>
 * File Name:   <BaseInterceptor.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2016年4月10日 下午5:32:48
 */
public class BaseInterceptor extends HandlerInterceptorAdapter{
	
	private static Logger log = LoggerFactory.getLogger(BaseInterceptor.class);
	
	private AtomicInteger requestNum = new AtomicInteger();

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		requestNum.addAndGet(1);
		log.info("IP: " + IpUtil.getIpAddr(request));
		if (!StringUtils.isBlank(request.getParameter("json"))) {
			StringBuffer accessAddressBuffer = new StringBuffer();
			accessAddressBuffer.append(ConstantsUtil.SERVER_HOST).append(request.getRequestURI()).append("?json=").append(request.getParameter("json"));
			log.info("URL: " + accessAddressBuffer.toString());
		}else{
			log.info("URL: " + ConstantsUtil.SERVER_HOST + request.getRequestURI());
		}
		log.info("请求数: " + requestNum);
		return true;
	}
}
