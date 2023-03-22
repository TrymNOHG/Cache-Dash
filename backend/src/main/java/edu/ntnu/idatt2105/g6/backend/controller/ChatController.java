package edu.ntnu.idatt2105.g6.backend.controller;

import edu.ntnu.idatt2105.g6.backend.dto.chat.ConversationDTO;
import edu.ntnu.idatt2105.g6.backend.dto.chat.ConversationLoadDTO;
import edu.ntnu.idatt2105.g6.backend.dto.chat.MessageDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingDTO;
import edu.ntnu.idatt2105.g6.backend.model.chat.Message;
import edu.ntnu.idatt2105.g6.backend.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/send")
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> saveMessage(@RequestBody MessageDTO message) {
        chatService.addMessage(message);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/load")
//    @ExceptionHandler(UserNotFoundException.class)
    //TODO: add token?
    public ResponseEntity<Object> loadConversations(@RequestBody String username) {
        List<ConversationLoadDTO> conversations = chatService.loadAllConversations(username);
        return ResponseEntity.ok(conversations);
    }


}
