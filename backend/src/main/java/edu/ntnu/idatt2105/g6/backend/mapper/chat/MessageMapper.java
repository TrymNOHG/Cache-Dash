package edu.ntnu.idatt2105.g6.backend.mapper.chat;

import edu.ntnu.idatt2105.g6.backend.dto.chat.MessageDTO;
import edu.ntnu.idatt2105.g6.backend.model.chat.Conversation;
import edu.ntnu.idatt2105.g6.backend.model.chat.Message;
import edu.ntnu.idatt2105.g6.backend.model.users.User;

import java.sql.Timestamp;
import java.util.Date;

public class MessageMapper {

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

}
