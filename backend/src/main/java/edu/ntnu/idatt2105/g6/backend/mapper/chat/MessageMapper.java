package edu.ntnu.idatt2105.g6.backend.mapper.chat;

import edu.ntnu.idatt2105.g6.backend.dto.chat.MessageDTO;
import edu.ntnu.idatt2105.g6.backend.dto.chat.MessageLoadDTO;
import edu.ntnu.idatt2105.g6.backend.model.chat.Conversation;
import edu.ntnu.idatt2105.g6.backend.model.chat.Message;
import edu.ntnu.idatt2105.g6.backend.model.users.User;

import java.sql.Timestamp;
import java.util.Date;

/**

 A mapper class that maps between {@link MessageDTO}, {@link Message}, {@link Conversation} and {@link User}.
 */
public class MessageMapper {

    /**
     * Maps {@link MessageDTO}, {@link Conversation} and {@link User} to {@link Message}.
     *
     * @param conversation The {@link Conversation} associated with the {@link Message}.
     * @param sender The {@link User} that sent the {@link Message}.
     * @param text The text of the {@link Message}.
     * @return The mapped {@link Message} object.
     */
   public static Message toMessage(Conversation conversation, User sender, String text){
       Timestamp timestamp = new Timestamp(new Date().getTime());
       Message message = Message
               .builder()
               .conversation(conversation)
               .sender(sender)
               .message(text)
               .timestamp(timestamp)
               .build();
       return message;
   }

   public static MessageLoadDTO toMessageLoadDTO(Message message) {
       return new MessageLoadDTO(message.getMessageId(), message.getSender().getUsername(),
               message.getMessage(), message.getTimestamp());
   }

}
