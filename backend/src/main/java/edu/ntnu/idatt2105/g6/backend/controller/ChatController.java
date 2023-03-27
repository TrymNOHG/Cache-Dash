package edu.ntnu.idatt2105.g6.backend.controller;

import edu.ntnu.idatt2105.g6.backend.dto.chat.ConversationDTO;
import edu.ntnu.idatt2105.g6.backend.dto.chat.ConversationLoadDTO;
import edu.ntnu.idatt2105.g6.backend.dto.chat.MessageDTO;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(value = "/load", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Load conversation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loading conversations",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConversationLoadDTO.class)) })
    })
    @ResponseBody
    public ResponseEntity<Object> loadConversations(@ParameterObject @RequestBody String username) {
        logger.info("All conversations with " +username + " is being loaded");
        List<ConversationLoadDTO> conversations = chatService.loadAllConversations(username);
        logger.info("All conversations with " +username + " was loaded");

        return ResponseEntity.ok(conversations);
    }

    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Load conversation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loading conversations",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConversationLoadDTO.class)) })
    })
    @ResponseBody
    public ResponseEntity<Object> deleteConversation(@ParameterObject @RequestBody Long conversationId) {
        try {
            //TODO: just check authentication token
            logger.info("Conversation Id to be delete : " + conversationId);
            chatService.deleteConversation(conversationId);
            logger.info("Conversation has been deleted!");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
