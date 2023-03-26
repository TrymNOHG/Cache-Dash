package edu.ntnu.idatt2105.g6.backend.service;

import edu.ntnu.idatt2105.g6.backend.dto.users.UserCreateDTO;
import edu.ntnu.idatt2105.g6.backend.model.users.Role;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import edu.ntnu.idatt2105.g6.backend.repo.users.UserRepository;
import edu.ntnu.idatt2105.g6.backend.security.AuthenticationRequest;
import edu.ntnu.idatt2105.g6.backend.security.AuthenticationResponse;
import edu.ntnu.idatt2105.g6.backend.service.security.AuthenticationService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

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

    @Nested
    @SpringBootTest
    class Authenticate{
        @Autowired
        private AuthenticationService authenticationService;

        @MockBean
        AuthenticationManager authenticationManager;

        @Autowired
        private UserRepository userRepository;

        @Test
        public void authenticate_returns_response(){
            User user = User
                    .builder()
                    .username("Test")
                    .password("123")
                    .fullName("Test test")
                    .email("test@gamil.com")
                    .role(Role.USER)
                    .build();
            userRepository.save(user);
            AuthenticationRequest authenticationRequest = new AuthenticationRequest("Test", "123");

            when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            ))).thenReturn(null);
            AuthenticationResponse authenticationResponse = authenticationService.authenticate(authenticationRequest);
            assertNotNull(authenticationResponse);
        }


    }


}
