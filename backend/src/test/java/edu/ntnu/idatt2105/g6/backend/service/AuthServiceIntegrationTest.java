package edu.ntnu.idatt2105.g6.backend.service;

import edu.ntnu.idatt2105.g6.backend.dto.users.UserCreateDTO;
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

            UserCreateDTO userCreateDTO = new UserCreateDTO("Test", "123", "Test Test", "test@gmail.com",null,null,null, Role.USER);
            AuthenticationResponse authenticationResponse = authenticationService.register(userCreateDTO);
            assertNotNull(authenticationResponse);
        }

        @Test
        public void registerUser_generates_unique_tokens(){

            UserCreateDTO userCreateDTO1 = new UserCreateDTO("Test", "123", "Test Test", "test@gmail.com",null,null,null, Role.USER);
            UserCreateDTO userCreateDTO2 = new UserCreateDTO("Test2", "124", "Test Test2", "test2@gmail.com",null,null,null, Role.USER);

            AuthenticationResponse authenticationResponse1 = authenticationService.register(userCreateDTO1);
            AuthenticationResponse authenticationResponse2 = authenticationService.register(userCreateDTO2);

            assertNotEquals(authenticationResponse1, authenticationResponse2);
        }
    }


}
