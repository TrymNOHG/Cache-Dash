package edu.ntnu.idatt2105.g6.backend.service;

import edu.ntnu.idatt2105.g6.backend.dto.chat.ConversationDTO;
import edu.ntnu.idatt2105.g6.backend.dto.chat.MessageDTO;
import edu.ntnu.idatt2105.g6.backend.exception.not_found.ConversationNotFoundException;
import edu.ntnu.idatt2105.g6.backend.exception.not_found.UserNotFoundException;
import edu.ntnu.idatt2105.g6.backend.model.users.Role;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import edu.ntnu.idatt2105.g6.backend.repo.chat.ConversationRepository;
import edu.ntnu.idatt2105.g6.backend.repo.chat.MessageRepository;
import edu.ntnu.idatt2105.g6.backend.repo.users.UserRepository;
import edu.ntnu.idatt2105.g6.backend.service.chat.ChatService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
public class ChatServiceIntegrationTest {



    @Nested
    @SpringBootTest
    class StartConversation{

        @Autowired
        UserRepository userRepository;

        @Autowired
        ChatService chatService;

        @Autowired
        ConversationRepository conversationRepository;

        @Autowired
        MessageRepository messageRepository;




        @Test
        public void startConversation_does_not_throw_exception(){
            ConversationDTO conversationDTO = new ConversationDTO("Hello World", "Test1", "Test2");
            User user1 = User
                    .builder()
                    .username("Test1")
                    .password("123")
                    .fullName("Test test")
                    .email("test@gamil.com")
                    .role(Role.ADMIN)
                    .build();
            User user2 = User
                    .builder()
                    .username("Test2")
                    .password("123")
                    .fullName("Test test")
                    .email("test@gamil.com")
                    .role(Role.ADMIN)
                    .build();

            userRepository.save(user1);
            userRepository.save(user2);

            assertDoesNotThrow(() -> {
                chatService.startConversation(conversationDTO);
            });


        }

        @Test
        public void startConversation_saves_first_message(){
            ConversationDTO conversationDTO = new ConversationDTO("Hello World", "Test1", "Test2");
            User user1 = User
                    .builder()
                    .username("Test1")
                    .password("123")
                    .fullName("Test test")
                    .email("test@gamil.com")
                    .role(Role.ADMIN)
                    .build();
            User user2 = User
                    .builder()
                    .username("Test2")
                    .password("123")
                    .fullName("Test test")
                    .email("test@gamil.com")
                    .role(Role.ADMIN)
                    .build();

            userRepository.save(user1);
            userRepository.save(user2);
            chatService.startConversation(conversationDTO);
            String message = chatService.loadConversation(1L).getMessages().get(0).message();
            assertEquals("Hello World", message);

        }


    }


    @Nested
    @SpringBootTest
    class LoadConversation {
        @Autowired
        UserRepository userRepository;

        @Autowired
        ChatService chatService;

        @Autowired
        ConversationRepository conversationRepository;

        @Autowired
        MessageRepository messageRepository;


        @Test
        public void loadConversation_does_not_throw_exception() {
            ConversationDTO conversationDTO = new ConversationDTO("Hello World", "Test1", "Test2");
            User user1 = User
                    .builder()
                    .username("Test1")
                    .password("123")
                    .fullName("Test test")
                    .email("test@gamil.com")
                    .role(Role.ADMIN)
                    .build();
            User user2 = User
                    .builder()
                    .username("Test2")
                    .password("123")
                    .fullName("Test test")
                    .email("test@gamil.com")
                    .role(Role.ADMIN)
                    .build();

            userRepository.save(user1);
            userRepository.save(user2);
            chatService.startConversation(conversationDTO);

            assertDoesNotThrow(() -> {
                chatService.loadConversation(1L);
            });


        }

        @Test
        public void loadConversation_throws_ConversationNotFoundException_for_non_existing_conversation() {
            assertThrows(ConversationNotFoundException.class, () -> {
                chatService.loadConversation(1L);
            });


        }
    }

    @Nested
    @SpringBootTest
    class LoadAllConversations{
        @Autowired
        UserRepository userRepository;

        @Autowired
        ChatService chatService;

        @Autowired
        ConversationRepository conversationRepository;

        @Autowired
        MessageRepository messageRepository;


        @Test
        public void loadAllConversations_does_not_throw_exception() {
            ConversationDTO conversationDTO = new ConversationDTO("Hello World", "Test1", "Test2");
            User user1 = User
                    .builder()
                    .username("Test1")
                    .password("123")
                    .fullName("Test test")
                    .email("test@gamil.com")
                    .role(Role.ADMIN)
                    .build();
            User user2 = User
                    .builder()
                    .username("Test2")
                    .password("123")
                    .fullName("Test test")
                    .email("test@gamil.com")
                    .role(Role.ADMIN)
                    .build();

            userRepository.save(user1);
            userRepository.save(user2);
            chatService.startConversation(conversationDTO);

            assertDoesNotThrow(() -> {
                chatService.loadAllConversations("Test1");
            });


        }

        @Test
        public void loadConversation_throws_UserNotFoundException_for_non_existing_user() {
            assertThrows(UserNotFoundException.class, () -> {
                chatService.loadAllConversations("Test");
            });


        }


    }

    @Nested
    @SpringBootTest
    class AddMessage{
        @Autowired
        UserRepository userRepository;

        @Autowired
        ChatService chatService;

        @Autowired
        ConversationRepository conversationRepository;

        @Autowired
        MessageRepository messageRepository;


        @Test
        public void addMessage_does_not_throw_exception() {
            ConversationDTO conversationDTO = new ConversationDTO("Hello World", "Test1", "Test2");
            User user1 = User
                    .builder()
                    .username("Test1")
                    .password("123")
                    .fullName("Test test")
                    .email("test@gamil.com")
                    .role(Role.ADMIN)
                    .build();
            User user2 = User
                    .builder()
                    .username("Test2")
                    .password("123")
                    .fullName("Test test")
                    .email("test@gamil.com")
                    .role(Role.ADMIN)
                    .build();

            userRepository.save(user1);
            userRepository.save(user2);
            chatService.startConversation(conversationDTO);
            MessageDTO messageDTO = new MessageDTO(1L, "Test1", "Hello");

            assertDoesNotThrow(() -> {
                chatService.addMessage(messageDTO);
            });


        }

        @Test
        public void addMessage_throws_UserNotFoundException_for_invalid_sender() {
            ConversationDTO conversationDTO = new ConversationDTO("Hello World", "Test1", "Test2");
            User user1 = User
                    .builder()
                    .username("Test1")
                    .password("123")
                    .fullName("Test test")
                    .email("test@gamil.com")
                    .role(Role.ADMIN)
                    .build();
            User user2 = User
                    .builder()
                    .username("Test2")
                    .password("123")
                    .fullName("Test test")
                    .email("test@gamil.com")
                    .role(Role.ADMIN)
                    .build();

            userRepository.save(user1);
            userRepository.save(user2);
            chatService.startConversation(conversationDTO);
            MessageDTO messageDTO = new MessageDTO(1L, "NotTest", "Hello");

            assertThrows(UserNotFoundException.class, () -> {
                chatService.addMessage(messageDTO);
            });



        }


    }

}
