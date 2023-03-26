package edu.ntnu.idatt2105.g6.backend.mapper.chat;

import edu.ntnu.idatt2105.g6.backend.dto.chat.ConversationDTO;
import edu.ntnu.idatt2105.g6.backend.dto.chat.ConversationLoadDTO;
import edu.ntnu.idatt2105.g6.backend.model.chat.Conversation;
import edu.ntnu.idatt2105.g6.backend.model.chat.Message;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**

 ConversationMapper class provides static methods for mapping between {@link Conversation}, {@link ConversationDTO},
 and {@link ConversationLoadDTO} objects.
 */
@Service
public class ConversationMapper {

    /**

     Converts two user objects to a new {@link Conversation} object.

     @param user1 the first user in the conversation
     @param user2 the second user in the conversation
     @return a new Conversation object with the two users and an empty message list
     */
    public static Conversation toConversation(User user1, User user2){
        return Conversation
                .builder()
                .user1(user1)
                .user2(user2)
                .messages(new ArrayList<>())
                .build();
    }

    /**
     Converts a {@link Conversation} object to a {@link ConversationLoadDTO} object.

     @param conversation the conversation object to convert
     @return a new ConversationLoadDTO object with the conversation ID, messages, and usernames of the two users
     */
    public static ConversationLoadDTO loadConversation(Conversation conversation){
        ConversationLoadDTO conversationLoadDTO = ConversationLoadDTO
                .builder()
                .conversationId(conversation.getConversationId())
                .messages(conversation.getMessages())
                .username1(conversation.getUser1().getUsername())
                .username2(conversation.getUser2().getUsername())
                .build();
        return conversationLoadDTO;
    }
}
