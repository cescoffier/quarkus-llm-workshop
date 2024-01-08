# Installing LLAMA2

To install LLAMA 2, we will use [ollama](https://ollama.ai/).
If you use Windows, you can follow the instructions from [ollama's official Docker image](https://ollama.ai/blog/ollama-is-now-available-as-an-official-docker-image).
Note that when using Docker, the model will be running in a container.
Without tuning, it is quite slow.

If you use Linux or Mac, download ollama from [ollama's download page](https://ollama.ai/download) and follow the installation instructions.

Once installed, you should have access to the `ollama` command line tool:

```bash
$ ollama --version
ollama version is 0.1.17
```

Ollama can run multiple models, as you can see on [ollama's library](https://ollama.ai/library).
We will use the `llama2` model, which is a GPT-2 model trained on a large corpus of text.

Pull the model using:

```bash
$ ollama pull llama2 
```

???+ warning  
The [LLAMA2](https://ollama.ai/library/llama2) model is quite large (> 3.8GB). Make sure you have enough disk space.

Once pulled, we will be able to use it.
