package edu.ntnu.idatt2105.g6.backend.controller;

import edu.ntnu.idatt2105.g6.backend.dto.chat.ConversationDTO;
import edu.ntnu.idatt2105.g6.backend.dto.chat.ConversationLoadDTO;
import edu.ntnu.idatt2105.g6.backend.dto.chat.MessageDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingLoadDTO;
import edu.ntnu.idatt2105.g6.backend.model.chat.Message;
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
        logger.info("A conversation between " + conversation.getUsername1() + " and " + conversation.getUsername2() + " is being created");
        chatService.startConversation(conversation);
        logger.info("A conversation between " + conversation.getUsername1() + " and " + conversation.getUsername2() + " was created");

        return ResponseEntity.ok().build();
    }

    @PostMapping("/send")
    @Operation(summary = "Send message")
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> saveMessage(@ParameterObject @RequestBody MessageDTO message) {
        chatService.addMessage(message);
        logger.info("A message was sent from " + message.username());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/load")
    @Operation(summary = "Load conversation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loading conversations",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConversationLoadDTO.class)) })}
    )
//    @ExceptionHandler(UserNotFoundException.class)
    //TODO: add token?
    public ResponseEntity<Object> loadConversations(@ParameterObject @RequestBody String username) {
        logger.info("All conversations with " +username + " is being loaded");
        List<ConversationLoadDTO> conversations = chatService.loadAllConversations(username);
        logger.info("All conversations with " +username + " was loaded");

        return ResponseEntity.ok(conversations);
    }


}
