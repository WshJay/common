package org.wsh.common.rest.socket;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-11-03 15:31
 */
public class LogWebSocketHandler extends TextWebSocketHandler {

    private SimpMessagingTemplate template;

    public LogWebSocketHandler(SimpMessagingTemplate template) {
        this.template = template;
        System.out.println("初始化 handler");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String text = message.getPayload(); // 获取提交过来的消息
        System.out.println("handMessage:" + text);
        // template.convertAndSend("/topic/getLog", text); // 这里用于广播
        session.sendMessage(message);
    }
}
