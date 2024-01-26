# Introduction

You are about to embark on a transformative journey as we explore the integration of AI into enterprise applications during this workshop. 
With technology evolving at an unprecedented pace, this workshop aims to demystify the process of infusing Artificial Intelligence and Large Language Models (LLM) into Quarkus applications.

<figure markdown>
![Quarkus Langchain4J](./assets/overall.jpg)
<figcaption>Overall overview of the workshop</figcaption>
</figure>



## Once upon a time, Quarkus

Quarkus was released in March 2019 by a team of engineers with the dream of supercharging Java for Cloud/Kubernetes deployments while bringing joy back to developers. 
Over the past decades, Java has dominated enterprise development, being used to build and run some of the most critical applications in the world. 
However, with the rise of Cloud and Kubernetes as preferred deployment platforms, Java struggled to keep up with the new requirements of Cloud Native development. 
Quarkus was created to bridge this gap.

If you visit the [Quarkus website](https://quarkus.io), you'll find that Quarkus is "_A Kubernetes Native Java stack tailored for OpenJDK HotSpot & GraalVM, crafted from the best of breed Java libraries and standards_." 
This somewhat cryptic description does an outstanding job at using bankable keywords. 
Additionally, it's proudly proclaimed: "_Supersonic Subatomic Java_," signifying that Quarkus is exceptionally fast and lightweight. 
But what exactly does Quarkus *do*?

In practice, Quarkus is a stack designed for developing distributed systems and modern applications in Java or Kotlin. 
Quarkus applications are optimized for the Cloud, containers, and Kubernetes. 
While Quarkus can be used in other environments, the principles infused in Quarkus have made containerization of applications more efficient.

In this workshop, you will learn how to use Quarkus to implement and execute enterprise Java applications interacting with LLMs.

## When Quarkus meets AI

AI is reshaping the software landscape, influencing user interactions and the foundations of software development. 
However, AI and Java haven't always been the best of friends.

The Quarkus team has been contemplating this challenge for a while. 
They've been figuring out how to make AI and Java work together, but not just any Java: Quarkus Java.

Integrating AI can be approached in various ways, but properly integrating AI into an enterprise application is not trivial. 
There are numerous challenges, including:

- How to integrate AI into an application without leaking concepts everywhere?
- How to integrate multiple models without having to deal with many different APIs
- How to make this AI integration *observable*?
- What about auditing?
- How to provide data to the AI to improve prediction accuracy?

In this workshop, you will learn how to integrate AI into a Quarkus application.
We are going to use [Quarkus Langchain4J](https://github.com/quarkiverse/quarkus-langchain4j), a Quarkus extension that provides a unified API to interact with multiple AI models (based on [langchain4j](https://github.com/langchain4j/langchain4j))

<figure markdown>
![Quarkus LLM Integration](./assets/ai-models.jpg)
  <figcaption>Integrating multiple LLMs models</figcaption>
</figure>

???+ note

    This workshop is not about teaching you how to use AI. 
    Instead, it's about how to integrate AI into a Quarkus application.

???+ note

    To learn more about Quarkus and LLM integration, you can refer to the [*Quarkus meets LangChain4j* article](https://quarkus.io/blog/quarkus-meets-langchain4j/).

## What are you going to learn?

This workshop is divided into four parts.

* **Slot 1: Unveiling the Potential - Integrating OpenAI with Quarkus**

  - Experience the hype: Learn to integrate OpenAI into a Quarkus application seamlessly.
  - Designing Interactions: Explore strategies for crafting meaningful interactions with large language models.
  - AI Empowerment: Discover tools and techniques for providing robust control over AI within your application.

* **Slot 2: Augmented Generation - Extending Hosted LLMs with Local Documents**

  - Shift gears: Explore another LLM and extend its capabilities using local documents.
  - Retrieval Augmented Generation: Delve into one of the core patterns in AI usage, understanding the synergy between your data and LLM interactions.

* **Slots 3 & 4: OpenShift AI - Training and Hosting Your Own Model**

  - Harness the power of OpenShift AI and its MLOps capabilities: Learn how it facilitates the training and hosting of custom AI models.
  - Practical Integration: Witness the integration of a custom model into a Quarkus application to complete the end-to-end narrative.

## Prerequisites

This workshop aims to be as self-explanatory as possible. 
Follow the instructions, perform the tasks, and feel free to ask for clarification or assistance—our team is here to help.

Firstly, ensure you have a 64-bit computer with admin rights (for installing necessary tools) and at least 8GB of RAM.

This workshop will use the following software, tools, frameworks that you need to install and understand:

* Any IDE you feel comfortable with (e.g., Intellij IDEA, Eclipse IDE, VS Code).
* JDK {{ attributes.versions.java}}.
* Docker or Podman (see instructions in [the appendix](./appendixes/installing-docker.md)).
* cURL (should be installed; if not, check [the appendix](./appendixes/installing-curl.md)).
* Ollama and the Llama 2 model (see instructions in [the appendix](./appendixes/installing-ollama.md)).
* An Azure OpenAI key (see instructions in [the appendix](./appendixes/accessing-azureai.md)).

We will also use Maven {{ attributes.versions.maven }}, but there's no need to install it. The workshop scaffolding includes a maven wrapper, `mvnw`.

!!! info
    This workshop assumes a bash shell. If you run on Windows, particularly, adjust the commands accordingly or install WSL. See [the appendix for installation instructions](./appendixes/installing-wsl.md).

## Getting Started

Once you have installed all the prerequisites, you can start the workshop. 
Download and unzip the [workshop archive](https://github.com/cescoffier/quarkus-llm-workshop/blob/main/dist/quarkus-llm-workshop-workshop.zip), open a terminal in the `quarkus-llm-workshop` directory, and run the following command to start the workshop:

```bash
$ ./mvnw verify
```

This command will build the code of the workshop. 
During the workshop, we will explore each part of the code, explaining how it works and demonstrating how to run the various applications.

**It's time to witness some code in action!**
