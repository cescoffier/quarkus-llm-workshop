package io.quarkiverse.langchain4j.workshop.chat;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.inject.Singleton;

@RegisterAiService
@Singleton
public interface ChatService {

    @SystemMessage("<<SYS>>You are a chat bot answering to customer request about bank products.<</SYS>>")
    @UserMessage("""
        Answer the customer request delimited with +++. The answer must be polite, and be relevant to the question.
        When you don't know, respond that you don't know the answer and the bank will contact the customer directly.

        +++
        {message}
        +++
        """)
    String chat(@MemoryId Object session, String message);

}
