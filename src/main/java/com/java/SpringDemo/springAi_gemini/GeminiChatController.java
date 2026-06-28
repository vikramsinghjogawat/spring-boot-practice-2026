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
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class GeminiChatController {

    private final Resource systemPrompt;

    private final ChatModel chatModel;
    private final ChatClient chatClient;
    //Bean is configured in AIConfig class
    public GeminiChatController(@Value("classpath:templates/SystemPrompt.txt") Resource systemPrompt, ChatModel chatModel, ChatClient chatClient) {
        this.systemPrompt = systemPrompt;
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

    @PostMapping("/api/with-resource-file")
    public Output chatPromptFromFile(@RequestBody @Valid Input input) throws IOException {
        String string = systemPrompt.getContentAsString(StandardCharsets.UTF_8);
        SystemMessage systemMessage = new SystemMessage(string);
        UserMessage userMessage = new UserMessage(input.prompt());
        Prompt prompts = new Prompt(List.of(systemMessage,userMessage));
        return new Output(chatModel.call(prompts).toString());

    }
    //Structured Output
    @PostMapping("/api/structured-list-output")
    public ListOuput listStructuredOutput(@RequestBody Requirement requirement){
        PromptTemplate promptTemplate = new PromptTemplate("""
                You are a unique individual with Senior software development
                and knowledge of Medicine in general. Give me {numberOfIdeas} ideas
                for beyond the thinking to solve through software engineering in medicine.
                Give only one line answers for each number of ideas.
                {format}
                """);
        ListOutputConverter listOutputConverter = new ListOutputConverter();
        Message message = promptTemplate.createMessage(Map.of("title", requirement.title(),
                "numberOfIdeas", requirement.numberOfIdeas(),
                "format",listOutputConverter.getFormat()
        ));
        String res = chatClient.prompt().messages(message).call().content();
        return new ListOuput(listOutputConverter.convert(res));
    }

    @PostMapping("/api/structured-map-output")
    public Map<String, Object> mapStructuredOutput(@RequestBody Requirement requirement){
        PromptTemplate promptTemplate = new PromptTemplate("""
                You are a unique individual with Senior knowledge of Medicine in general. Give me {numberOfIdeas} ideas
                for illness and common medicine.
                Give answers as key value pairs for each idea.
                {format}
                """);
        MapOutputConverter mapOutputConverter = new MapOutputConverter();
        Message message = promptTemplate.createMessage(Map.of("title", requirement.title(),
                "numberOfIdeas", requirement.numberOfIdeas(),
                "format",mapOutputConverter.getFormat()
        ));
        String res = chatClient.prompt().messages(message).call().content();
        MapOutput mapOutput = new MapOutput(mapOutputConverter.convert(res));
        return mapOutput.illnessMedicine();
    }

    @PostMapping("/api/structured-bean-output")
    public Student beanStructuredOutput(){
        PromptTemplate promptTemplate = new PromptTemplate("""
                Generate a dummy student object with this signture compliance:
                record Student(Integer rollNo,String name, Integer standard,List<String> subjects)
                {format}
                """);
        BeanOutputConverter<Student> beanOutputConverter = new BeanOutputConverter<>(Student.class);
        Message message = promptTemplate.createMessage(Map.of("format",beanOutputConverter.getFormat()));
        String content = chatClient.prompt().messages(message).call().content();
        return beanOutputConverter.convert(content);
    }
    //output {
    //    "rollNo": 101,
    //    "name": "John Doe",
    //    "standard": 10,
    //    "subjects": [
    //        "Mathematics",
    //        "Physics",
    //        "Chemistry"
    //    ]
    //}

     public record Input(@NotBlank String prompt){}
     public record Output(String content){}
     public record Requirement(String title,Integer numberOfIdeas){}
     public record ListOuput(List<String>  stringList){}
     public record MapOutput(Map<String,Object> illnessMedicine){}
     public record Student(Integer rollNo,String name, Integer standard,List<String> subjects){}
}