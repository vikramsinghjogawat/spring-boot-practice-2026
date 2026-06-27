package com.java.SpringDemo.springAi_gemini;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class GeminiChatController {

    private final ChatModel chatModel;
    private final ChatClient chatClient;
    //Bean is configured in AIConfig class
    public GeminiChatController(ChatModel chatModel, ChatClient chatClient) {
        this.chatModel = chatModel;
        this.chatClient = chatClient;
    }

    @PostMapping("/api/chatgemini")
    public Output chat (@RequestBody @Valid Input input){
        String content = chatModel.call(input.prompt());
        return new Output(content);
    }
    //Testing Prompt
    @PostMapping("/api/withPrompt")
    public Output chatwithPrompt(@RequestBody @Valid Input input){
        SystemMessage systemMessage = new SystemMessage("You are a very funny chat bot");
        UserMessage userMessage = new UserMessage(input.prompt());
        Prompt prompts = new Prompt(List.of(systemMessage,userMessage));
        return new Output(chatModel.call(prompts).toString());

    }
    @PostMapping("/api/prompt-template")
    public Output chatWithPromptTemplate(@RequestBody Requirement requirement){
        PromptTemplate promptTemplate = new PromptTemplate("""
                You are a unique individual with Senior software development 
                and knowledge of Medicine in general. Give me {numberOfIdeas} ideas
                for beyond the thinking to solve through software engineering in medicine.
                Give only one line answers for each number of ideas.
                """);
        Message message = promptTemplate.createMessage(Map.of("title", requirement.title(), "numberOfIdeas", requirement.numberOfIdeas()));
        return new Output(chatClient.prompt().messages(message).call().content());
    }
    private record Input(@NotBlank String prompt){}
    private record Output(String content){}
    private record Requirement(String title,Integer numberOfIdeas){}
}
