package edu.ntnu.idatt2105.g6.backend.mapper.chat;

import edu.ntnu.idatt2105.g6.backend.dto.chat.ConversationDTO;
import edu.ntnu.idatt2105.g6.backend.dto.chat.ConversationLoadDTO;
import edu.ntnu.idatt2105.g6.backend.model.chat.Conversation;
import edu.ntnu.idatt2105.g6.backend.model.chat.Message;
import edu.ntnu.idatt2105.g6.backend.model.users.User;

public class ConversationMapper {

    public static Conversation toConversation(User user1, User user2){
        Conversation conversation = Conversation
                .builder()
                .user1(user1)
                .user2(user2)
                .build();
        return conversation;
    }

    public static ConversationLoadDTO loadConversation(Conversation conversation){
        ConversationLoadDTO conversationLoadDTO = ConversationLoadDTO
                .builder()
                .messages(conversation.getMessages())
                .username1(conversation.getUser1().getUsername())
                .username2(conversation.getUser2().getUsername())
                .build();
        return conversationLoadDTO;
    }
}
