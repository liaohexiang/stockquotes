package org.tony.stockquotes;

import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;*/
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private WebSocketSessionManager manager;

    @Autowired
    private StreamMessageListenerContainer<String, MapRecord<String, String,String>> streamContainer;

    @Autowired
    private StringRedisTemplate template;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 处理WebSocket连接建立的逻辑
        // 可以在这里订阅特定的消息流、处理连接建立逻辑等
        manager.addSession(session);

        /*streamContainer.receive(Consumer.from("Stock-Quotes",session.getId()),
                StreamOffset.fromStart("mystream"),
                new MyStreamListener(session));*/
        String message = "Hello, client!";
        session.sendMessage(new TextMessage(message));
        session.sendMessage(new TextMessage( template.opsForStream().read(StreamOffset.fromStart("mystream")).toString()));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 处理接收到的文本消息的逻辑
        // 可以在这里处理接收到的消息、发送消息给客户端等
        session.sendMessage(new TextMessage("Server echo:"+message.getPayload()));
    }

    private static class MyStreamListener implements StreamListener<String,MapRecord<String,String,String>> {


        private WebSocketSession session;
        // Create a new instance of MyStreamListener
        public MyStreamListener(WebSocketSession session){
            // Assign the session parameter to the session instance variable
            this.session = session;
        }
        // Override the onMessage method
        @Override
        public void onMessage(MapRecord<String, String, String> message) {
            try {
                // Print a line of hyphens
                System.out.println("--------------------------------");
                // Send a text message using the session instance variable
                session.sendMessage(new TextMessage(message.getValue().toString()));
            } catch (IOException e) {
                // Print the stack trace of the exception
                e.printStackTrace();
            }
        }
    }
}
