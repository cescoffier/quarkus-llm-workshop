# Slot 4: Utilizing a model deployed on OpenShift AI

Welcome to the final slot of the workshop!
In this session, we'll leverage the model deployed in the previous slot to customize the triage application introduced in the first slot.

Depending on the model that have been deployed, the functionality of the triage application will be different.
The application located in the `triage-application-openshift-ai` is using a simplistic LLM (`flant5s-l`) and thus is adapted accordingly.

???+ abstract "OpenShift AI with the Caikit+TGIS runtime"
    Strictly speaking, it's not just OpenShift AI but OpenShift AI with the Caikit+TGIS runtime that powers our model deployment.

## Using the OpenShift AI Quarkus integration

To interact with a model running on OpenShift AI, we'll include a specific dependency in the `pom.xml` file located in `triage-application-openshift-ai`.
This dependency is tailored for OpenShift AI and facilitates interaction with the model serving platform and its API:

```xml
<dependency>
    <groupId>io.quarkiverse.langchain4j</groupId>
    <artifactId>quarkus-langchain4j-openshift-ai</artifactId>
    <version>${quarkus-langchain.version}</version>
</dependency>
```
This dependency is equivalent to the ones we have used in the previous slots, but it is specific to OpenShift AI.
It will allow Quarkus LangChain4J to interact with the model serving platform and its API.

Additionally, we need to specify the model serving platform's URL and the model ID in the `application.properties` file:

```properties
quarkus.langchain4j.openshift-ai.base-url=https://flant5s-l-predictor-ch2023.apps.cluster-hj2qv.dynamic.redhatworkshops.io:443/api
quarkus.langchain4j.openshift-ai.chat-model.model-id=flant5s-l
```

For now, we are going to use the [Flan T5 model](https://paperswithcode.com/method/flan-t5) model.
This model is not as powerful as GPT 3.5.
Thus, the application is sightly simplified.

???+ note
    The `quarkus.tls.trust-all=true` property is included to bypass certificate validation, as the API uses HTTPS with self-signed certificates. This should not be done in production.

## The triage service

The `TriageService.java` interface is the cornerstone of the application:

```java
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

```

As you can see, it's a simplified version of the one used in the first slot:

1. We use only a `@UserMessage` and no `@SystemMessage`, as the model runtime do not allow multiple messages for now.
2. We do not ask the LLM to generate JSON, but just a `positive` or `negative` string.
3. We do not ask the LLM to generate a message for the user

Because, all the attendees are using the same endpoint, and we have limited resources, we keep the `@RateLimit` annotation to avoid overloading the model serving platform.

## Running the application

To run the application, you need to use the following command form the `triage-application-openshift-ai` directory:

```shell
mvn quarkus:dev
```

Then, open the browser at the following URL: http://localhost:8080, and start posting reviews.
Each time to click on the _submit_ button, it sends the review to the application, which invokes the computes the message and invokes
the LLM model.
Once received, the response is passed to the browser and displayed.

## Using another model

One of the great benefits of OpenShift AI is that it allows you to deploy multiple model.
So, we can switch to the [Mistral model](https://docs.mistral.ai/).

Open the `application.properties` file to comment the Flan T5 configuration, and uncomment the Mistral one:

```properties

# Flan Small
# quarkus.langchain4j.openshift-ai.base-url=https://flant5s-l-predictor-ch2023.apps.cluster-hj2qv.dynamic.redhatworkshops.io:443/api
# quarkus.langchain4j.openshift-ai.chat-model.model-id=flant5s-l

# Mistral 7B
quarkus.langchain4j.openshift-ai.base-url=https://mistral7b-xl-predictor-ch2023.apps.cluster-hj2qv.dynamic.redhatworkshops.io:443/api
quarkus.langchain4j.openshift-ai.chat-model.model-id=mistral7b-xl
quarkus.langchain4j.openshift-ai.timeout=60s

## The deployed model is using a self-signed certificate, so we need to trust it.
quarkus.tls.trust-all=true
```

Then, the next time you submit a review, it will call the Mistral model.
Note that it is a much more powerful model, so the response time is longer.

## Summary

This concludes this slot and the workshop.
In this slot we have seen how to use a model deployed on OpenShift AI with Quarkus LangChain4J.
We used two models: Flan T5 and Mistral, both served by OpenShift AI.

To summarize the workshop, we have seen how to:

* Integrate several LLMs in Quarkus applications (Azure OpenAI, (local) Llama, Llama on Hugging Face, Flan T5, and Mistral)
* We have used multiple LLM provides (Azure, Hugging Face, and OpenShift AI)
* We added tracing, metrics and auditing to our application to verify that it behaves correctly
* We used Quarkus fault tolerance to handle failures and rate limiting gracefully
* We have seen how to implement a RAG (retrieval augmented generation) pattern

We hope you enjoy the workshop.
We are eager to hear your positive and negative feedback and suggestions for improvements.
