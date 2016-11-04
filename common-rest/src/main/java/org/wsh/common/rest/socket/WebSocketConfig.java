package org.wsh.common.rest.socket;

import javax.annotation.Resource;

import org.springframework.asm.Handle;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  WebScoket配置处理器
 * since Date： 2016/11/4 15:54
 */
@Configuration
@EnableWebMvc
@EnableWebSocket
@Component
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

	@Resource
	private SystemWebSocketHandler systemWebSocketHandler;

	@Resource
	private HandShakeInterceptor handShakeInterceptor;

	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(systemWebSocketHandler, "/ws").addInterceptors(handShakeInterceptor);

		registry.addHandler(systemWebSocketHandler, "/ws/sockjs").addInterceptors(handShakeInterceptor).withSockJS();
	}

}
