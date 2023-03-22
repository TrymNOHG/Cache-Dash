package edu.ntnu.idatt2105.g6.backend.service;

import edu.ntnu.idatt2105.g6.backend.dto.users.UserDTO;
import edu.ntnu.idatt2105.g6.backend.model.users.Role;
import edu.ntnu.idatt2105.g6.backend.repo.users.UserRepository;
import edu.ntnu.idatt2105.g6.backend.security.AuthenticationResponse;
import edu.ntnu.idatt2105.g6.backend.service.security.AuthenticationService;
import org.junit.Test;
import org.junit.jupiter.api.Nested;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
public class AuthServiceIntegrationTest {



    @Autowired
    private AuthenticationService authenticationService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void register_user_returns_response(){

        UserDTO userDTO = new UserDTO("Test", "123", "Test Test", "test@gmail.com",null,null,null, Role.USER);
        AuthenticationResponse authenticationResponse = authenticationService.register(userDTO);
        assertNotNull(authenticationResponse);
    }

    @Test
    public void register_user_generates_unique_tokens(){

        UserDTO userDTO1 = new UserDTO("Test", "123", "Test Test", "test@gmail.com",null,null,null, Role.USER);
        UserDTO userDTO2 = new UserDTO("Test2", "124", "Test Test2", "test2@gmail.com",null,null,null, Role.USER);

        AuthenticationResponse authenticationResponse1 = authenticationService.register(userDTO1);
        AuthenticationResponse authenticationResponse2 = authenticationService.register(userDTO2);

        assertNotEquals(authenticationResponse1, authenticationResponse2);
    }
}
