package com.java.SpringDemo.springAi;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ChatController {

    private final ChatClient chatClient;
    //Bean is configured in AIConfig class
    ChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

        @PostMapping("/api/chat")
        Output chat (@RequestBody @Valid Input input){
            String content = chatClient.prompt(input.prompt()).call().content();
            return new Output(content);
        }


    record Input(@NotBlank String prompt){}
    record Output(String content){}
}
