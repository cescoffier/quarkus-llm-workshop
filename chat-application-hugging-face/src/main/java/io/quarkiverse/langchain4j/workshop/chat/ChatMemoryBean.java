package io.quarkiverse.langchain4j.workshop.chat;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class ChatMemoryBean implements ChatMemoryProvider {

    private final Map<Object, ChatMemory> memories = new ConcurrentHashMap<>();

    @Override
    public ChatMemory get(Object memoryId) {
        return memories.computeIfAbsent(memoryId, id -> MessageWindowChatMemory.builder()
                .maxMessages(3)
                .id(memoryId)
                .build());
    }

    public void clear(Object session) {
        memories.remove(session);
    }
}
