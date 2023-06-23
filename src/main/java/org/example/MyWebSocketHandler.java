package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private WebSocketSessionManager manager;
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 处理WebSocket连接建立的逻辑
        // 可以在这里订阅特定的消息流、处理连接建立逻辑等
        manager.addSession(session);
        String message = "Hello, client!";
        session.sendMessage(new TextMessage(message));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 处理接收到的文本消息的逻辑
        // 可以在这里处理接收到的消息、发送消息给客户端等
        session.sendMessage(new TextMessage("Server echo:"+message.getPayload()));
    }
}
