package org.tony.stockquotes;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.connection.stream.MapRecord;
//import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Enumeration;

@Component
public class StockQuotesPusher {

    @Autowired
    private WebSocketSessionManager webSocketSessionManager;


/*    @Scheduled(fixedRate=2000)
    public void doPush() throws Exception{
        if(webSocketSessionManager.hasSessions()){
            Enumeration<WebSocketSession> allSessions =  webSocketSessionManager.getAllSessions();
            while(allSessions.hasMoreElements()){
                WebSocketSession session= allSessions.nextElement();
                session.sendMessage(new TextMessage("Server echo: "+session.getId()+" stock quotes"));
                session.sendMessage(new TextMessage("--------------"));
            }
        }

    }*/
}
