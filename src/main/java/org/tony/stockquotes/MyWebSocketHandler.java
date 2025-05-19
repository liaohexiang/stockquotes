package org.tony.stockquotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
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
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 处理WebSocket连接关闭的逻辑
        // 可以在这里取消订阅特定的消息流、处理连接关闭逻辑等
        // 记录连接关闭信息
        System.out.println("WebSocket connection closed. Session ID: " + session.getId() +
                ", Reason: " + status.getCode() + " - " + status.getReason());
        manager.removeSession(session);
    }


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 处理接收到的文本消息的逻辑
        // 可以在这里处理接收到的消息、发送消息给客户端等
        session.sendMessage(new TextMessage("Server echo:" + message.getPayload()));
    }

}
