package edu.ntnu.idatt2105.g6.backend.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Nested
    class When_Message_constructor_has_non_null_variable {

        @Test
        public void id_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                Message message = new Message(null, new Conversation(),
                        new User(), "Hi!", new Timestamp(new Date().getTime()));
            });
        }
        @Test
        public void conversation_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                Message message = new Message(1L, null,
                        new User(), "Hi!", new Timestamp(new Date().getTime()));
            });
        }

        @Test
        public void user_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                Message message = new Message(1L, new Conversation(),
                        null, "Hi!", new Timestamp(new Date().getTime()));
            });
        }

        @Test
        public void message_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                Message message = new Message(1L, new Conversation(),
                        new User(), null, new Timestamp(new Date().getTime()));
            });
        }

        @Test
        public void timestamp_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                Message message = new Message(1L, new Conversation(),
                        new User(), "Hi!", null);
            });
        }

    }

    //        Message(Long messageId, Conversation conversation, User sender, String message, Timestamp timestamp


    @Nested
    class When_setting_Message_non_null_variable {
        @Test
        public void id_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                Message message = new Message();
                message.setMessageId(null);
            });
        }
        @Test
        public void conversation_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                Message message = new Message();
                message.setConversation(null);
            });
        }

        @Test
        public void sender_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                Message message = new Message();
                message.setSender(null);
            });
        }

        @Test
        public void message_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                Message message = new Message();
                message.setMessage(null);
            });
        }

        @Test
        public void timestamp_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                Message message = new Message();
                message.setTimestamp(null);
            });
        }
    }

    @Nested
    class Message_can_properly_get {
        Message getMessage() {
            return new Message(1L, new Conversation(), new User(), "Hi!", new Timestamp(new Date().getTime()));
        }

        @Test
        void id() {
            Message message = getMessage();
            Long expectedId = 1L;
            Long actualId = message.getMessageId();

            assertEquals(expectedId, actualId);
        }

        @Test
        void conversation() {
            Message message = getMessage();
            Conversation expectedConversation = new Conversation();
            message.setConversation(expectedConversation);
            Conversation actualConversation = message.getConversation();

            assertEquals(expectedConversation, actualConversation);
        }

        @Test
        void sender() {
            Message message = getMessage();
            User expectedSender = new User();
            User actualSender = message.getSender();

            assertEquals(expectedSender, actualSender);
        }

        @Test
        void message() {
            Message message = getMessage();
            String expectedMessage = "Hi!";
            String actualMessage = message.getMessage();

            assertEquals(expectedMessage, actualMessage);
        }

        @Test
        void timestamp() {
            Message message = getMessage();
            Timestamp expectedTimestamp = new Timestamp(new Date().getTime());
            message.setTimestamp(expectedTimestamp);

            Timestamp actualTimestamp = message.getTimestamp();

            assertEquals(expectedTimestamp, actualTimestamp);
        }

    }

}