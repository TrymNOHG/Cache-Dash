package edu.ntnu.idatt2105.g6.backend.service;

import edu.ntnu.idatt2105.g6.backend.dto.users.UserDTO;
import edu.ntnu.idatt2105.g6.backend.model.users.Role;
import edu.ntnu.idatt2105.g6.backend.repo.users.UserRepository;
import edu.ntnu.idatt2105.g6.backend.service.security.AuthenticationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@RunWith(SpringRunner.class)
public class AuthServiceIntegrationTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Test
    public void register_user_returns_correct_response(){
        UserDTO userDTO = new UserDTO("Test", "123", "Test Test", "test@gmail.com", Role.USER);

        assertDoesNotThrow(() -> {
            authenticationService.register(userDTO);
        });

    }
}
