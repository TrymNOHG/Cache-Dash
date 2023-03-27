package edu.ntnu.idatt2105.g6.backend.service.chat;

import edu.ntnu.idatt2105.g6.backend.controller.ChatController;
import edu.ntnu.idatt2105.g6.backend.dto.chat.ConversationDTO;
import edu.ntnu.idatt2105.g6.backend.dto.chat.ConversationLoadDTO;
import edu.ntnu.idatt2105.g6.backend.dto.chat.MessageDTO;
import edu.ntnu.idatt2105.g6.backend.exception.not_found.ConversationNotFoundException;
import edu.ntnu.idatt2105.g6.backend.exception.not_found.UserNotFoundException;
import edu.ntnu.idatt2105.g6.backend.mapper.chat.ConversationMapper;
import edu.ntnu.idatt2105.g6.backend.mapper.chat.MessageMapper;
import edu.ntnu.idatt2105.g6.backend.model.chat.Conversation;
import edu.ntnu.idatt2105.g6.backend.model.chat.Message;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import edu.ntnu.idatt2105.g6.backend.repo.chat.ConversationRepository;
import edu.ntnu.idatt2105.g6.backend.repo.chat.MessageRepository;
import edu.ntnu.idatt2105.g6.backend.repo.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 This service class handles the business logic for chat-related operations.
 It implements the IChatService interface.
 */
@Service
@RequiredArgsConstructor
public class ChatService implements IChatService{

    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(ChatController.class);


    /**
     Starts a new conversation between two users.

     @param conversationDTO The conversation data transfer object containing the two usernames and the initial message.
     @throws UserNotFoundException if one of the users does not exist in the system.
     */
    @Transactional
    @Override
    public void startConversation(ConversationDTO conversationDTO) {
        User user1 = userRepository.findByUsername(conversationDTO.getUsername1()).orElseThrow(() -> new UserNotFoundException(conversationDTO.getUsername1()));
        User user2 = userRepository.findByUsername(conversationDTO.getUsername2()).orElseThrow(() -> new UserNotFoundException(conversationDTO.getUsername1()));
        Conversation conversation = ConversationMapper.toConversation(user1, user2);

        conversation = conversationRepository.save(conversation);

        MessageDTO messageDTO = new MessageDTO(conversation.getConversationId(), conversationDTO.getUsername1(), conversationDTO.getMessage());
        addMessage(messageDTO);
    }

    /**
     Loads a conversation by its ID.

     @param conversationId The ID of the conversation to load.
     @return A conversation data transfer object containing information about the conversation and its messages.
     @throws ConversationNotFoundException if no conversation with the given ID exists in the system.
     */
    @Override
    public ConversationLoadDTO loadConversation(Long conversationId) {
        logger.info("loading conversations with this conversationID");
        Conversation conversation = conversationRepository.findByConversationId(conversationId).orElseThrow(() -> new ConversationNotFoundException(conversationId));
        return ConversationMapper.loadConversation(conversation);
    }

    /**
     Loads all conversations for a given user.

     @param username The username of the user to load conversations for.
     @return A list of conversation data transfer objects containing information about the conversations and their messages.
     @throws UserNotFoundException if the given user does not exist in the system.
     @throws ConversationNotFoundException if the given user does not have any conversations in the system.
     */
    @Override
    public List<ConversationLoadDTO> loadAllConversations(String username) {
        logger.info("User: " + username + " is trying to load his conversations");
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        List<Conversation> conversationList = conversationRepository.findAllByUser1OrUser2(user, user).orElseThrow(() -> new ConversationNotFoundException(username));
        logger.info("All conversation for the user: " + conversationList);
        return conversationList.stream().map(ConversationMapper::loadConversation).toList();
    }

    /**
     Adds a new message to a conversation.

     @param messageDTO The message data transfer object containing the conversation ID, the sender username, and the message content.
     @throws ConversationNotFoundException if no conversation with the given ID exists in the system.
     @throws UserNotFoundException if the sender username does not exist in the system.
     */
    @Transactional
    @Override
    public void addMessage(MessageDTO messageDTO) {
        Conversation conversation = conversationRepository.findByConversationId(messageDTO.conversationId()).orElseThrow(() -> new ConversationNotFoundException(messageDTO.conversationId()));
        User user = userRepository.findByUsername(messageDTO.username()).orElseThrow(() -> new UserNotFoundException(messageDTO.username()));
        Message message = MessageMapper.toMessage(conversation, user, messageDTO.message());
        messageRepository.save(message);
    }

    /**
     Deletes the conversation with the given conversation ID from the conversation repository.

     @param conversationId the ID of the conversation to be deleted
     @throws ConversationNotFoundException if the conversation with the given ID does not exist
     */
    @Override
    public void deleteConversation(Long conversationId) {
        Conversation conversationToDelete = conversationRepository.findByConversationId(conversationId)
                .orElseThrow(() -> new ConversationNotFoundException(conversationId));
        conversationRepository.delete(conversationToDelete);
    }
}
