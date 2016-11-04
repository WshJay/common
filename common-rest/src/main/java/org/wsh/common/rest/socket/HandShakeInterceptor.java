
package org.wsh.common.rest.socket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.wsh.common.enums.SessionKey;
import org.wsh.common.enums.msg.MessageKey;
import org.wsh.common.model.basic.UserBasicDO;
import org.wsh.common.util.logger.LoggerService;

import static javax.swing.text.html.CSS.getAttribute;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  Socket建立连接（握手）和断开
 * since Date： 2016/11/3 16:49
 */
@Component
public class HandShakeInterceptor extends LoggerService implements HandshakeInterceptor {

	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession(false);
			// 标记用户
			UserBasicDO userBasicDO = (UserBasicDO)session.getAttribute(SessionKey.user.name());
			String groupId = (String)session.getAttribute(MessageKey.GROUP_ID.name());
			if(userBasicDO != null && StringUtils.isNotBlank(groupId)){
				attributes.put(SessionKey.user.name(), userBasicDO);
				attributes.put(MessageKey.GROUP_ID.name(),groupId);
				logger.info("Websocket:用户名[" + userBasicDO.getUserName() + "]已经建立连接");
			}else{
				return false;
			}
		}
		return true;
	}

	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
	}
}
