package edu.ntnu.idatt2105.g6.backend.service.chat;

import edu.ntnu.idatt2105.g6.backend.dto.chat.ConversationDTO;
import edu.ntnu.idatt2105.g6.backend.dto.chat.ConversationLoadDTO;
import edu.ntnu.idatt2105.g6.backend.dto.chat.MessageDTO;
import edu.ntnu.idatt2105.g6.backend.mapper.chat.ConversationMapper;
import edu.ntnu.idatt2105.g6.backend.mapper.chat.MessageMapper;
import edu.ntnu.idatt2105.g6.backend.model.chat.Conversation;
import edu.ntnu.idatt2105.g6.backend.model.chat.Message;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import edu.ntnu.idatt2105.g6.backend.repo.chat.ConversationRepository;
import edu.ntnu.idatt2105.g6.backend.repo.chat.MessageRepository;
import edu.ntnu.idatt2105.g6.backend.repo.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService implements IChatService{

    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Override
    public void startConversation(ConversationDTO conversationDTO) {
        User user1 = userRepository.findByUsername(conversationDTO.getUsername1()).orElseThrow();
        User user2 = userRepository.findByUsername(conversationDTO.getUsername2()).orElseThrow();

        Conversation conversation = ConversationMapper.toConversation(user1, user2);

        conversationRepository.save(conversation);

        MessageDTO messageDTO = new MessageDTO(conversation.getConversationId(), conversationDTO.getUsername1(), conversationDTO.getMessage());
        addMessage(messageDTO);
    }

    @Override
    public ConversationLoadDTO loadConversation(Long conversationId) {
        Conversation conversation = conversationRepository.findByConversationId(conversationId).orElseThrow();
        return ConversationMapper.loadConversation(conversation);
    }

    @Override
    public List<ConversationLoadDTO> loadAllConversations(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        List<Conversation> conversationList = conversationRepository.findAllByUser1OrUser2(user, user).orElseThrow();
        return conversationList.stream().map(ConversationMapper::loadConversation).toList();
    }

    @Override
    public void addMessage(MessageDTO messageDTO) {
        Conversation conversation = conversationRepository.findByConversationId(messageDTO.conversationId()).orElseThrow();
        User user = userRepository.findByUsername(messageDTO.username()).orElseThrow();
        Message message = MessageMapper.toMessage(conversation, user, messageDTO.message());
        messageRepository.save(message);
    }
}
