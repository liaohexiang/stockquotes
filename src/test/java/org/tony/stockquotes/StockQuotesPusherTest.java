package org.tony.stockquotes;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Collections;
import java.util.Enumeration;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StockQuotesPusherTest extends TestCase {

    @Mock
    private WebSocketSessionManager webSocketSessionManager;

    @Mock
    private WebSocketSession webSocketSession;

    @InjectMocks
    private StockQuotesPusher stockQuotesPusher;

    @Test
    public void testDoPushWithSingleActiveSession() throws Exception {
        // Setup
        String sessionId = "test-session-123";
        when(webSocketSessionManager.hasSessions()).thenReturn(true);
        when(webSocketSession.getId()).thenReturn(sessionId);
        Enumeration<WebSocketSession> singleSession = Collections.enumeration(
                Collections.singletonList(webSocketSession)
        );
        when(webSocketSessionManager.getAllSessions()).thenReturn(singleSession);

        // Execute
        stockQuotesPusher.doPush();

        // Verify
        verify(webSocketSession).sendMessage(new TextMessage("Server echo: " + sessionId + " stock quotes"));
        verify(webSocketSession).sendMessage(new TextMessage("--------------"));
    }
}