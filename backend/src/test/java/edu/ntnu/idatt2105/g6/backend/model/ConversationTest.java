package edu.ntnu.idatt2105.g6.backend.model;

import edu.ntnu.idatt2105.g6.backend.model.chat.Conversation;
import edu.ntnu.idatt2105.g6.backend.model.chat.Message;
import edu.ntnu.idatt2105.g6.backend.model.users.Role;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConversationTest {

    @Nested
    class Null_columns_constructors {

        @Test
        void conversation_id_cannot_be_null(){
            //TODO does not throw error for empty constructor
            User user1 = new User("test1", "123", "test1", "test1", Role.USER);
            User user2 = new User("test2", "123", "test2", "test2", Role.USER);
            List<Message> messages = new ArrayList<>();
            assertThrows(NullPointerException.class, () -> {
                Conversation conversation = new Conversation(null, user1, user2, messages);
            });
        }

        @Test
        void user1_cannot_be_null(){
            //TODO does not throw error for empty constructor
            User user2 = new User("test2", "123", "test2", "test2", Role.USER);
            List<Message> messages = new ArrayList<>();
            assertThrows(NullPointerException.class, () -> {
                Conversation conversation = new Conversation(1L, null, user2, messages);
            });
        }

        @Test
        void user2_cannot_be_null(){
            //TODO does not throw error for empty constructor
            User user1 = new User("test1", "123", "test1", "test1", Role.USER);
            List<Message> messages = new ArrayList<>();
            assertThrows(NullPointerException.class, () -> {
                Conversation conversation = new Conversation(1L, user1, null, messages);
            });
        }

        @Test
        void messages_cannot_be_null(){
            //TODO does not throw error for empty constructor
            User user1 = new User("test1", "123", "test1", "test1", Role.USER);
            User user2 = new User("test2", "123", "test2", "test2", Role.USER);
            assertThrows(NullPointerException.class, () -> {
                Conversation conversation = new Conversation(1L, user1, user2, null);
            });
        }

    }

    @Nested
    class Null_variables{

        @Test
        void conversation_id_cannot_be_set_to_null(){
            User user1 = new User("test1", "123", "test1", "test1", Role.USER);
            User user2 = new User("test2", "123", "test2", "test2", Role.USER);
            List<Message> messages = new ArrayList<>();
            Conversation conversation = new Conversation(1L, user1, user2, messages);
            assertThrows(NullPointerException.class, () -> {
                conversation.setConversationId(null);
            });
        }

        @Test
        void user1_cannot_be_set_to_null(){
            User user1 = new User("test1", "123", "test1", "test1", Role.USER);
            User user2 = new User("test2", "123", "test2", "test2", Role.USER);
            List<Message> messages = new ArrayList<>();
            Conversation conversation = new Conversation(1L, user1, user2, messages);
            assertThrows(NullPointerException.class, () -> {
                conversation.setUser1(null);
            });
        }

        @Test
        void user2_cannot_be_set_to_null(){
            User user1 = new User("test1", "123", "test1", "test1", Role.USER);
            User user2 = new User("test2", "123", "test2", "test2", Role.USER);
            List<Message> messages = new ArrayList<>();
            Conversation conversation = new Conversation(1L, user1, user2, messages);
            assertThrows(NullPointerException.class, () -> {
                conversation.setUser2(null);
            });
        }

        @Test
        void messages_cannot_be_set_to_null(){
            User user1 = new User("test1", "123", "test1", "test1", Role.USER);
            User user2 = new User("test2", "123", "test2", "test2", Role.USER);
            List<Message> messages = new ArrayList<>();
            Conversation conversation = new Conversation(1L, user1, user2, messages);
            assertThrows(NullPointerException.class, () -> {
                conversation.setMessages(null);
            });
        }
    }

    @Nested
    class Getters{

        @Test
        void conversation_id_getter_returns_correct_value(){
            User user1 = new User("test1", "123", "test1", "test1", Role.USER);
            User user2 = new User("test2", "123", "test2", "test2", Role.USER);
            List<Message> messages = new ArrayList<>();
            Conversation conversation = new Conversation(1L, user1, user2, messages);
            assertEquals(1L, conversation.getConversationId());
        }

        @Test
        void user1_getter_returns_correct_value(){
            User user1 = new User("test1", "123", "test1", "test1", Role.USER);
            User user2 = new User("test2", "123", "test2", "test2", Role.USER);
            List<Message> messages = new ArrayList<>();
            Conversation conversation = new Conversation(1L, user1, user2, messages);
            assertEquals(user1, conversation.getUser1());
        }

        @Test
        void user2_getter_returns_correct_value(){
            User user1 = new User("test1", "123", "test1", "test1", Role.USER);
            User user2 = new User("test2", "123", "test2", "test2", Role.USER);
            List<Message> messages = new ArrayList<>();
            Conversation conversation = new Conversation(1L, user1, user2, messages);
            assertEquals(user2, conversation.getUser2());
        }

        @Test
        void messages_getter_returns_correct_value(){
            User user1 = new User("test1", "123", "test1", "test1", Role.USER);
            User user2 = new User("test2", "123", "test2", "test2", Role.USER);
            List<Message> messages = new ArrayList<>();
            Conversation conversation = new Conversation(1L, user1, user2, messages);
            assertEquals(messages, conversation.getMessages());
        }
    }


}