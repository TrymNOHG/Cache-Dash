package edu.ntnu.idatt2105.g6.backend.service;

import edu.ntnu.idatt2105.g6.backend.dto.users.UserDTO;
import edu.ntnu.idatt2105.g6.backend.model.users.Role;
import edu.ntnu.idatt2105.g6.backend.repo.users.UserRepository;
import edu.ntnu.idatt2105.g6.backend.security.AuthenticationResponse;
import edu.ntnu.idatt2105.g6.backend.service.security.AuthenticationService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
public class AuthServiceIntegrationTest {


    @Nested
    @SpringBootTest
    class RegisterUser{
        @Autowired
        private AuthenticationService authenticationService;

        @MockBean
        private UserRepository userRepository;

        @Test
        public void registerUser_returns_response(){

            UserDTO userDTO = new UserDTO("Test", "123", "Test Test", "test@gmail.com",null,null,null, Role.USER);
            AuthenticationResponse authenticationResponse = authenticationService.register(userDTO);
            assertNotNull(authenticationResponse);
        }

        @Test
        public void registerUser_generates_unique_tokens(){

            UserDTO userDTO1 = new UserDTO("Test", "123", "Test Test", "test@gmail.com",null,null,null, Role.USER);
            UserDTO userDTO2 = new UserDTO("Test2", "124", "Test Test2", "test2@gmail.com",null,null,null, Role.USER);

            AuthenticationResponse authenticationResponse1 = authenticationService.register(userDTO1);
            AuthenticationResponse authenticationResponse2 = authenticationService.register(userDTO2);

            assertNotEquals(authenticationResponse1, authenticationResponse2);
        }
    }


}
