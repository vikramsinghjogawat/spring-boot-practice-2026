//package com.java.SpringDemo.springAi_openai;
//
//import jakarta.validation.Valid;
//import jakarta.validation.constraints.NotBlank;
//import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.ai.chat.model.ChatModel;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/")
//public class ChatController {
//
//    private final ChatClient chatClient;
//
//    private final ChatModel chatModel;
//    //Bean is configured in AIConfig class
//    ChatController(ChatClient chatClient, ChatModel chatModel) {
//        this.chatClient = chatClient;
//        this.chatModel = chatModel;
//    }
//
//        @PostMapping("/api/chat")
//        Output chat (@RequestBody @Valid Input input){
//            String content = chatModel.call(input.prompt());
//            return new Output(content);
//        }
//
//
//    record Input(@NotBlank String prompt){}
//    public record Output(String content){}
//}
