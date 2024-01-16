# Slot 3: Deploying an LLM using OpenShift AI

Welcome to the third slot, where we delve into the process of deploying a Language Model (LLM) using OpenShift AI. 
In the subsequent slot, we will leverage this deployed model to rework the functionality of the triage application introduced in the first slot.

## Deploying a Model on OpenShift AI
OpenShift AI provides a streamlined solution for deploying and managing models on the OpenShift platform, offering robust model-serving capabilities. 
In this demonstration, we'll employ OpenShift AI using [Caikit+TGIS](https://caikit.github.io/website/) runtime to deploy and run of our LLM model.

We are reusing an existing model. 
OpenShift AI also proposes a tool suite to create, train and evaluate models.

???+ info
    The OpenShift AI team is discussing an alternative approach to serve LLM based on [vLLM](https://docs.vllm.ai/en/latest/).
    However, this approach is not yet available in OpenShift AI. 

## Accessing the Deployed Model

Once successfully deployed, your model becomes accessible through a REST API. 
OpenShift AI will furnish you with the URL, and depending on the desired action and the model's capabilities, append one of the following paths to the end of the inference endpoint URL:

```
:443/api/v1/task/server-streaming-text-generation
:443/api/v1/task/text-generation
```

For the Quarkus integration, the latter endpoint is utilized. 

As depicted by these paths, the model serving platform employs the HTTPS port of your OpenShift router (typically port 443) to handle external API requests.

Utilize these endpoints for making API requests to your deployed model. 
A practical example using the curl command is illustrated below:

```shell
$ curl --json '{
"model_id": "<model_name>",
"inputs": "<query_text>"
}' https://<inference_endpoint_url>:443/api/v1/task/text-generation
```

## Summary

This concludes our exploration of the third slot in the workshop. 
We've covered the steps involved in serving and invoking an LLM hosted on OpenShift AI. 
In the upcoming and final slot, we will customize the triage application introduced in the first slot to incorporate the capabilities of the recently deployed Language Model.
