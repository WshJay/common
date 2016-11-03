
package org.wsh.common.rest.socket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.wsh.common.enums.SessionKey;
import org.wsh.common.model.basic.UserBasicDO;
import org.wsh.common.util.logger.LoggerService;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  Socket建立连接（握手）和断开
 * since Date： 2016/11/3 16:49
 */
public class HandShake extends LoggerService implements HandshakeInterceptor {

	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
		logger.info("Websocket:用户[ID:" + ((ServletServerHttpRequest) request).getServletRequest().getSession().getAttribute(SessionKey.user.name()) + "]已经建立连接");
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession(false);
			// 标记用户
			UserBasicDO userBasicDO = (UserBasicDO)session.getAttribute(SessionKey.user.name());
			if(userBasicDO != null){
				attributes.put(SessionKey.user.name(), userBasicDO.getId());
			}else{
				return false;
			}
		}
		return true;
	}

	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
	}

}
