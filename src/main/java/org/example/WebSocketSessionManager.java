package org.example;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketSessionManager {

    private ConcurrentHashMap<String,WebSocketSession> map =new ConcurrentHashMap<>();

    public boolean addSession(WebSocketSession session){
        return null != map.putIfAbsent(session.getId(),session);
    }

    public boolean removeSession(WebSocketSession session)
    {
        return null != map.remove(session.getId());
    }

    public boolean hasSessions(){
        return this.map.size()>0;
    }

    public Enumeration<WebSocketSession> getAllSessions(){
        return map.elements();
    }
}
