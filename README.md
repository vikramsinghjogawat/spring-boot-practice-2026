# spring-ai-practice-2026
# My learnings
To create AI powered applications we can use Spring AI or LangChain4j

**Question:Why Spring Ai? Why don't we use various ai models directly?**

**Answer**: All the various providers(Open AI, Anthropic, Google Gemini etc)
provide the connectors/SDK.

If we use the sdk directly than we lose portability if our chosen provider is not
the correct fit for us. We will need to rewrite for new provider.

Spring AI is the abstraction layer on top of all these providers  so it is just minimal
overhead to change from one provider to another and it can just be configuration based.
<hr>

    Table of contents for this module:
    1. Intro to Open AI
    2. Prompt templates
    3. Structured Outputs
    4.Chat Memory
    5. Embedding Models & Vector stores
    6. RAG
    7.Tool calling
    8. MCP
<hr>

# Chat with Open AI
1. Create Open AI key
2. Set OPENAI_API_KEY env var
3. Create Spring Boot Application with Open AI
4. Implement Rest end point to talk to Open AI
<hr>
   First go to platform.openai.com
   Click on API key and create a new secret key
   Copy secret key an put it in application.prop like below

        //spring.ai.openai.api-key=${OPENAI_API_KEY}
       //spring.ai.openai.chat.options.model=gpt-5
       //spring.ai.openai.chat.options.temperature=1
