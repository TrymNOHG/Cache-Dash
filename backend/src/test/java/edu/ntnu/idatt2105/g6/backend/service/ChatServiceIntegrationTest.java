package edu.ntnu.idatt2105.g6.backend.service;

import edu.ntnu.idatt2105.g6.backend.dto.chat.ConversationDTO;
import edu.ntnu.idatt2105.g6.backend.dto.chat.ConversationLoadDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.UserDTO;
import edu.ntnu.idatt2105.g6.backend.mapper.chat.MessageMapper;
import edu.ntnu.idatt2105.g6.backend.model.chat.Conversation;
import edu.ntnu.idatt2105.g6.backend.model.chat.Message;
import edu.ntnu.idatt2105.g6.backend.model.users.Role;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import edu.ntnu.idatt2105.g6.backend.repo.chat.ConversationRepository;
import edu.ntnu.idatt2105.g6.backend.repo.chat.MessageRepository;
import edu.ntnu.idatt2105.g6.backend.repo.users.UserRepository;
import edu.ntnu.idatt2105.g6.backend.service.chat.ChatService;
import edu.ntnu.idatt2105.g6.backend.service.security.AuthenticationService;
import edu.ntnu.idatt2105.g6.backend.service.users.UserService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.Socket;
import java.net.SocketOption;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
            String message = chatService.loadConversation(1L).getMessages().get(0).getMessage();
            assertEquals("Hello World", message);

        }


    }


    @Nested
    @SpringBootTest
    class LoadConversation{



    }

}
