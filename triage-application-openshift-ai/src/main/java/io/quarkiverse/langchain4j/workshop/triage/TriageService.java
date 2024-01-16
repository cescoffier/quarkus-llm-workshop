package io.quarkiverse.langchain4j.workshop.triage;

import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.smallrye.faulttolerance.api.RateLimit;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;

import java.time.temporal.ChronoUnit;

@RegisterAiService
public interface TriageService {

    @UserMessage("""
            You are working for a bank. You are an AI processing reviews about financial products. You need to triage the reviews into positive and negative ones.
            You must answer with `positive` or `negative` accordingly.

            {review}

            """)
    @Retry(maxRetries = 2)
    @Fallback(fallbackMethod = "fallback")
    @RateLimit(value = 2, window = 10, windowUnit = ChronoUnit.SECONDS)
    String triage(String review);

    static String fallback(String review) {
        return "Sorry, we are unable to process your review at the moment. Please try again later.";
    }

}
