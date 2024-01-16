package io.quarkiverse.langchain4j.workshop.chat;

import io.smallrye.mutiny.infrastructure.Infrastructure;
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
        if (message.startsWith("<p>")) {
            message = message.substring(3);
        }
        if (message.endsWith("</p>")) {
            message = message.substring(0, message.length() - 4);
        }
        final var msg = message;
        Infrastructure.getDefaultExecutor().execute(() -> {
            String response = chat.chat(session, msg);
            try {
                session.getBasicRemote().sendText(response);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

}
