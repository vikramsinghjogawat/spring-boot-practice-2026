package com.java.SpringDemo.springAi_gemini;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class GeminiChatController {

    private final ChatModel chatClient;
    //Bean is configured in AIConfig class
    public GeminiChatController(ChatModel chatModel) {
        this.chatClient = chatModel;
    }

    @PostMapping("/api/chatgemini")
    public OutputGemini chat (@RequestBody @Valid InputGemini input){
        String content = chatClient.call(input.prompt());
        return new OutputGemini(content);
    }


    public record InputGemini(@NotBlank String prompt){}
    public record OutputGemini(String content){}
}
