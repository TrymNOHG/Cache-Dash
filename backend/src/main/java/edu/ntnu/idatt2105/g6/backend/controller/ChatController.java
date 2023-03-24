package edu.ntnu.idatt2105.g6.backend.controller;

import edu.ntnu.idatt2105.g6.backend.dto.chat.ConversationDTO;
import edu.ntnu.idatt2105.g6.backend.dto.chat.ConversationLoadDTO;
import edu.ntnu.idatt2105.g6.backend.dto.chat.MessageDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingDTO;
import edu.ntnu.idatt2105.g6.backend.model.chat.Message;
import edu.ntnu.idatt2105.g6.backend.service.chat.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/user/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/new")
    @Operation(summary = "Create new conversation")
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> startConversation(@ParameterObject @RequestBody ConversationDTO conversation) {
        chatService.startConversation(conversation);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/send")
    @Operation(summary = "Send message")
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> saveMessage(@ParameterObject @RequestBody MessageDTO message) {
        chatService.addMessage(message);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/load")
//    @ExceptionHandler(UserNotFoundException.class)
    //TODO: add token?
    public ResponseEntity<Object> loadConversations(@ParameterObject @RequestBody String username) {
        List<ConversationLoadDTO> conversations = chatService.loadAllConversations(username);
        return ResponseEntity.ok(conversations);
    }


}
