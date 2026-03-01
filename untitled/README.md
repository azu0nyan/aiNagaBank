# OpenAI Java Sample Project with OpenRouter

This is a sample Java project demonstrating how to use the [OpenAI Java SDK](https://github.com/openai/openai-java) to connect to OpenRouter and interact with AI models.

## Features

- Uses OpenAI Java SDK (version 4.23.0)
- Connects to OpenRouter API
- Configured to use `arcee-ai/trinity-large-preview:free` model
- Asks a specific question: "how mouch are u owe me?"
- Loads configuration from `application.properties`

## Prerequisites

- Java 11 or later
- Maven 3.x

## Project Structure

```
.
├── pom.xml
├── src/
│   └── main/
│       ├── java/
│       │   └── org/
│       │       └── example/
│       │           └── Main.java
│       └── resources/
│           └── application.properties
```

## Configuration

The project reads configuration from `src/main/resources/application.properties`:

```properties
openai.api.key=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
openai.model=arcee-ai/trinity-large-preview:free
openai.base-url=https://openrouter.ai/api/v1
```

### Configuration Properties

- **openai.api.key**: Your OpenRouter API key
- **openai.model**: The AI model to use (e.g., `arcee-ai/trinity-large-preview:free`)
- **openai.base-url**: OpenRouter API endpoint (`https://openrouter.ai/api/v1`)

## Dependencies

```xml
<dependency>
  <groupId>com.openai</groupId>
  <artifactId>openai-java</artifactId>
  <version>4.23.0</version>
</dependency>
```

## Running the Project

### Using Maven

```bash
mvn clean compile exec:java -Dexec.mainClass="org.example.Main"
```

### Expected Output

```
Connecting to OpenRouter...
Question: how mouch are u owe me?
Model: arcee-ai/trinity-large-preview:free
==================================================
Response:
[AI model response will appear here]
```

## How It Works

1. **Load Configuration**: The application loads API key, model name, and base URL from `application.properties`
2. **Initialize Client**: Creates an OpenAI client configured to connect to OpenRouter
3. **Create Request**: Builds a chat completion request with a generic prompt and the specific question
4. **Send Request**: Sends the request to the OpenRouter API
5. **Display Response**: Prints the AI model's response to the console

## Code Overview

### Main.java

The main class demonstrates:
- Loading configuration from properties file
- Building an OpenAI client with custom base URL (OpenRouter)
- Creating chat completion parameters
- Making API calls and handling responses

## Notes

- The OpenAI Java SDK is designed for OpenAI's API but works with OpenRouter by changing the base URL
- OpenRouter provides access to various AI models through a unified API
- The free tier model (`arcee-ai/trinity-large-preview:free`) is used in this example

## Resources

- [OpenAI Java SDK Documentation](https://github.com/openai/openai-java)
- [OpenRouter Documentation](https://openrouter.ai/docs)
- [OpenRouter Models](https://openrouter.ai/models)

## License

This is a sample project for demonstration purposes.
