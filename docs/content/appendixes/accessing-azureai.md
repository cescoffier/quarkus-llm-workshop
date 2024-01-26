# Accessing Azure OpenAI

If you already have an Azure subscription, you can use it to create an [Azure OpenAI](https://azure.microsoft.com/products/ai-services/openai-service) resource.
Otherwise, you can create a [free Azure subscription](https://azure.microsoft.com/free) and use it to create an Azure OpenAI resource.
Then, you need to use the Azure OpenAI service to generate the API key and endpoint URL.
You can either do that using the [Azure Portal](https://portal.azure.com) or the [Azure CLI](https://learn.microsoft.com/cli/azure).

The easiest being use the Azure CLI, we recommend that you [install it](https://learn.microsoft.com/cli/azure/install-azure-cli) and use it to create the Azure OpenAI resource and generate the API key and endpoint URL.
Once Azure CLI is installed and you have your Azure subscription, sign in to your Azure account:

```shell
az login
```

???+ note "Check Azure subscription"
    If you have several Azure subscription, make sure you are using the right one.
    For that, you can execute the following command to list all your subscriptions and set the one you want to use:

    ```shell
    az account list --output table
    az account set --subscription <subscription-id>
    az account show
    ```

Then, execute the following command to create the Azure OpenAI resources:

```shell
echo "Setting up environment variables..."
echo "----------------------------------"
PROJECT="<give-your-project-a-name>"
RESOURCE_GROUP="rg-$PROJECT"
LOCATION="eastus"
TAG="$PROJECT"
AI_SERVICE="ai-$PROJECT"
AI_DEPLOYMENT="gpt35turbo"

echo "Creating the resource group..."
echo "------------------------------"
az group create \
  --name "$RESOURCE_GROUP" \
  --location "$LOCATION" \
  --tags system="$TAG"

echo "Creating the Cognitive Service..."
echo "---------------------------------"
az cognitiveservices account create \
  --name "$AI_SERVICE" \
  --resource-group "$RESOURCE_GROUP" \
  --location "$LOCATION" \
  --custom-domain "$AI_SERVICE" \
  --tags system="$TAG" \
  --kind "OpenAI" \
  --sku "S0"

echo "Deploying the model..."
echo "----------------------"
az cognitiveservices account deployment create \
  --name "$AI_SERVICE" \
  --resource-group "$RESOURCE_GROUP" \
  --deployment-name "$AI_DEPLOYMENT" \
  --model-name "gpt-35-turbo" \
  --model-version "0301"  \
  --model-format "OpenAI" \
  --sku-capacity 1 \
  --sku-name "Standard"
```

Once the script has executed, you can use the following command to get the API key and endpoint URL.
You will need these properties later one when you will configure the application.

```shell
echo "Storing the key and endpoint in environment variables..."
echo "--------------------------------------------------------"
AZURE_OPENAI_KEY=$(
  az cognitiveservices account keys list \
    --name "$AI_SERVICE" \
    --resource-group "$RESOURCE_GROUP" \
    | jq -r .key1
  )
AZURE_OPENAI_ENDPOINT=$(
  az cognitiveservices account show \
    --name "$AI_SERVICE" \
    --resource-group "$RESOURCE_GROUP" \
    | jq -r .properties.endpoint
  )

# Set the properties
echo "--------------------------------------------------"
echo "The following properties can be copied to either the application.properties:"
echo "--------------------------------------------------"
echo "quarkus.langchain4j.azure-openai.api-key=$AZURE_OPENAI_KEY"
echo "quarkus.langchain4j.azure-openai.deployment-id=$AI_DEPLOYMENT"
echo "quarkus.langchain4j.azure-openai.resource-name=$AI_SERVICE"
```

Once you've finished the workshop, remember to delete the Azure OpenAI resources to avoid being charged for it:

```shell
# Clean up
az group delete \
  --name "$RESOURCE_GROUP" \
  --yes

az cognitiveservices account purge \
  --name "$AI_SERVICE" \
  --resource-group "$RESOURCE_GROUP" \
  --location "$LOCATION"

az cognitiveservices account delete \
  --name "$AI_SERVICE" \
  --resource-group "$RESOURCE_GROUP"
```
