package me.escoffier.workshop.chat;

import io.smallrye.mutiny.infrastructure.Infrastructure;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;

@ServerEndpoint("/chatbot")
public class ChatBotWebSocket {

    @Inject
    ChatService chat;

    @Inject
    ChatMemoryBean chatMemoryBean;

    @OnClose
    void onClose(Session session) {
        chatMemoryBean.clear(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        Infrastructure.getDefaultExecutor().execute(() -> {
            String response = chat.chat(session, message);
            try {
                session.getBasicRemote().sendText(response);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

}
