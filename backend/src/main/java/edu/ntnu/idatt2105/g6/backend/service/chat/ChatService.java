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

@Service
@RequiredArgsConstructor
public class ChatService implements IChatService{

    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(ChatController.class);


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

    @Override
    public ConversationLoadDTO loadConversation(Long conversationId) {
        logger.info("loading conversations with this conversationID");
        Conversation conversation = conversationRepository.findByConversationId(conversationId).orElseThrow(() -> new ConversationNotFoundException(conversationId));
        return ConversationMapper.loadConversation(conversation);
    }

    @Override
    public List<ConversationLoadDTO> loadAllConversations(String username) {
        logger.info("User: " + username + " is trying to load his conversations");
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        List<Conversation> conversationList = conversationRepository.findAllByUser1OrUser2(user, user).orElseThrow(() -> new ConversationNotFoundException(username));
        logger.info("All conversation for the user: " + conversationList);
        return conversationList.stream().map(ConversationMapper::loadConversation).toList();
    }

    @Transactional
    @Override
    public void addMessage(MessageDTO messageDTO) {
        Conversation conversation = conversationRepository.findByConversationId(messageDTO.conversationId()).orElseThrow(() -> new ConversationNotFoundException(messageDTO.conversationId()));
        User user = userRepository.findByUsername(messageDTO.username()).orElseThrow(() -> new UserNotFoundException(messageDTO.username()));
        Message message = MessageMapper.toMessage(conversation, user, messageDTO.message());
        messageRepository.save(message);

    }
}
