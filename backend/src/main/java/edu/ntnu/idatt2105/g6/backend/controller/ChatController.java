package edu.ntnu.idatt2105.g6.backend.controller;

import edu.ntnu.idatt2105.g6.backend.dto.chat.ConversationDTO;
import edu.ntnu.idatt2105.g6.backend.dto.chat.ConversationLoadDTO;
import edu.ntnu.idatt2105.g6.backend.dto.chat.MessageDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingLoadDTO;
import edu.ntnu.idatt2105.g6.backend.model.chat.Conversation;
import edu.ntnu.idatt2105.g6.backend.model.chat.Message;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import edu.ntnu.idatt2105.g6.backend.service.chat.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
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
    private final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @PostMapping("/new")
    @Operation(summary = "Create new conversation")
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> startConversation(@ParameterObject @RequestBody ConversationDTO conversation) {
        logger.info("User with username: "  + conversation.getUsername1());
        logger.info("Is trying to start a conversation with: " + conversation.getUsername2());
        logger.info("With the initial message: " + conversation.getMessage());
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
    @Operation(summary = "Load conversation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loading conversations",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConversationLoadDTO.class)) })
    })
    @ResponseBody
    public ResponseEntity<Object> loadConversations(@ParameterObject @RequestBody String username) {
        try {
            logger.info("User trying to load conversation : " + username);
            List<ConversationLoadDTO> conversations = chatService.loadAllConversations(username.substring(0, username.length()-1));
            logger.info("Sending list of conversations: " + conversations);
            return ResponseEntity.ok(conversations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error loading conversations");
        }
    }


}
