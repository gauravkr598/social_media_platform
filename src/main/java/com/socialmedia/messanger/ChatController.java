package com.socialmedia.messanger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

@Controller
@RequestMapping("/user")
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
    	System.out.println("Hello message : " + chatMessage.getSender());
        System.out.println(chatMessage.getType());
        System.out.println(chatMessage.getContent());
        return chatMessage;
    }
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, 
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        System.out.println("Hello message : " + chatMessage.getSender());
        System.out.println(chatMessage.getType());
        System.out.println(chatMessage.getContent());
        
        return chatMessage;
     }
    @GetMapping("/open")
    public String openChat() {
    	
    	return "request-messanger/index";
    }
    @GetMapping("/select")
    public String selectUser() {
    	
    	return "request-messanger/select-user";
    }

}
