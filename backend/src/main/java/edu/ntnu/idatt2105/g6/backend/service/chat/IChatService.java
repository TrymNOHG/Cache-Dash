package edu.ntnu.idatt2105.g6.backend.service.chat;


import edu.ntnu.idatt2105.g6.backend.dto.chat.ConversationDTO;
import edu.ntnu.idatt2105.g6.backend.dto.chat.ConversationLoadDTO;
import edu.ntnu.idatt2105.g6.backend.dto.chat.MessageDTO;
import edu.ntnu.idatt2105.g6.backend.model.chat.Conversation;

import java.util.List;

public interface IChatService {

    void startConversation(ConversationDTO conversationDTO);
    ConversationLoadDTO loadConversation(Long conversationId);
    List<ConversationLoadDTO> loadAllConversations(String username);
    void addMessage(MessageDTO messageDTO);

}
