package edu.ntnu.idatt2105.g6.backend.model.users;

import edu.ntnu.idatt2105.g6.backend.model.chat.Conversation;
import edu.ntnu.idatt2105.g6.backend.model.chat.Message;
import edu.ntnu.idatt2105.g6.backend.model.listing.Item;
import edu.ntnu.idatt2105.g6.backend.model.users.Role;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Nested
    class When_User_constructor_has_non_null_variable {

//        User(String username, String password, String fullName, String email, Role role)
        @Test
        public void username_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                User user = new User(null, "password", "Ole Norman", "Ole@gmail.com", Role.USER);
            });
        }
        @Test
        public void password_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                User user = new User("Ole123", null, "Ole Norman", "Ole@gmail.com", Role.USER);
            });
        }

        @Test
        public void full_name_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                User user = new User("Ole123", "password", null, "Ole@gmail.com", Role.USER);
            });
        }

        @Test
        public void email_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                User user = new User("Ole123", "password", "Ole Norman", null, Role.USER);
            });
        }

        @Test
        public void role_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                User user = new User("Ole123", "password", "Ole Norman", "Ole@gmail.com", null);
            });
        }

    }

    @Nested
    class When_setting_User_non_null_variable {
        @Test
        public void username_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                User user = new User();
                user.setUsername(null);
            });
        }
        @Test
        public void password_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                User user = new User();
                user.setPassword(null);
            });
        }

        @Test
        public void full_name_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                User user = new User();
                user.setFullName(null);
            });
        }

        @Test
        public void email_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                User user = new User();
                user.setEmail(null);
            });
        }

        @Test
        public void role_throws_NullPointer_Exception_if_null() {
            assertThrows(NullPointerException.class, () -> {
                User user = new User();
                user.setRole(null);
            });
        }
    }

    @Nested
    class User_can_properly_get {
        User getUser() {
            return new User("Ole123", "password", "Ole Norman", "Ole@gmail.com", Role.USER);
        }

        @Test
        void username() {
            User user = getUser();
            String expectedUsername = "Ole123";
            String actualUsername = user.getUsername();

            assertEquals(expectedUsername, actualUsername);
        }

        @Test
        void password() {
            User user = getUser();
            String expectedPassword = "password";
            String actualPassword = user.getPassword();

            assertEquals(expectedPassword, actualPassword);
        }

        @Test
        void fullName() {
            User user = getUser();
            String expectedFullName = "Ole Norman";
            String actualFullName = user.getFullName();

            assertEquals(expectedFullName, actualFullName);
        }

        @Test
        void email() {
            User user = getUser();
            String expectedEmail = "Ole@gmail.com";
            String actualEmail = user.getEmail();

            assertEquals(expectedEmail, actualEmail);
        }

        @Test
        void birthDate() {
            User user = getUser();
            Date expectedDate = new Date();
            user.setBirthDate(expectedDate);

            Date actualDate = user.getBirthDate();

            assertEquals(expectedDate, actualDate);
        }

        @Test
        void phone() {
            User user = getUser();
            String expectedPhone = "+4712345678";
            user.setPhone(expectedPhone);

            String actualPhone = user.getPhone();

            assertEquals(expectedPhone, actualPhone);
        }

        @Test
        void picture() {
            User user = getUser();
            byte[] expectedPicture = {};
            user.setPicture(expectedPicture);

            byte[] actualPicture = user.getPicture();

            assertEquals(expectedPicture, actualPicture);
        }

        @Test
        void listed_items(){
            List<Item> expectedItems = new ArrayList<>();
            List<Item> bookmarks = new ArrayList<>();
            List<Conversation> conversations1 = new ArrayList<>();
            List<Conversation> conversations2 = new ArrayList<>();
            List<Message> messages = new ArrayList<>();
            User user = new User(1L, "Ole123", "password", "Ole Norman", "Ole@gmail.com", Role.USER, new Date(), "+4712345678", new byte[4], expectedItems, bookmarks, conversations1, conversations2, messages);

            List<Item> actualItems = user.getListedItems();
            assertEquals(expectedItems, actualItems);
        }

        @Test
        void bookmarks(){
            List<Item> listedItems = new ArrayList<>();
            List<Item> expectedBookmarks = new ArrayList<>();
            List<Conversation> conversations1 = new ArrayList<>();
            List<Conversation> conversations2 = new ArrayList<>();
            List<Message> messages = new ArrayList<>();
            User user = new User(1L, "Ole123", "password", "Ole Norman", "Ole@gmail.com", Role.USER, new Date(), "+4712345678", new byte[4], listedItems, expectedBookmarks, conversations1, conversations2, messages);

            List<Item> actualBookmarks = user.getBookmarks();

            assertEquals(expectedBookmarks, actualBookmarks);
        }

        @Test
        void conversations() {
            List<Item> listedItems = new ArrayList<>();
            List<Item> bookmarks = new ArrayList<>();
            List<Conversation> expectedConversations1 = new ArrayList<>();
            List<Conversation> expectedConversations2 = new ArrayList<>();
            List<Message> messages = new ArrayList<>();
            User user = new User(1L, "Ole123", "password", "Ole Norman", "Ole@gmail.com", Role.USER, new Date(), "+4712345678", new byte[4], listedItems, bookmarks, expectedConversations1, expectedConversations2, messages);

            List<Conversation> actualConversation1 = user.getConversations1();
            List<Conversation> actualConversation2 = user.getConversations2();

            assertEquals(expectedConversations1, actualConversation1);
            assertEquals(expectedConversations2, actualConversation2);
        }

        @Test
        void messages() {
            List<Item> listedItems = new ArrayList<>();
            List<Item> bookmarks = new ArrayList<>();
            List<Conversation> conversations1 = new ArrayList<>();
            List<Conversation> conversations2 = new ArrayList<>();
            List<Message> expectedMessages = new ArrayList<>();
            User user = new User(1L, "Ole123", "password", "Ole Norman", "Ole@gmail.com", Role.USER, new Date(), "+4712345678", new byte[4], listedItems, bookmarks, conversations1, conversations2, expectedMessages);

            List<Message> actualMessages = user.getMessages();

            assertEquals(expectedMessages, actualMessages);
        }

    }

}
// String username, String password, String fullName, String email, Date birthDate, String phone, byte[] picture;
