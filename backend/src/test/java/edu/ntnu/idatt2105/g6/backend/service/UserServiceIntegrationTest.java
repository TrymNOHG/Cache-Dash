package edu.ntnu.idatt2105.g6.backend.service;

import edu.ntnu.idatt2105.g6.backend.dto.users.UserDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.UserUpdateDTO;
import edu.ntnu.idatt2105.g6.backend.exception.exists.UserExistsException;
import edu.ntnu.idatt2105.g6.backend.exception.not_found.UserNotFoundException;
import edu.ntnu.idatt2105.g6.backend.model.users.Role;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import edu.ntnu.idatt2105.g6.backend.repo.users.UserRepository;
import edu.ntnu.idatt2105.g6.backend.service.security.AuthenticationService;
import edu.ntnu.idatt2105.g6.backend.service.users.UserService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
public class UserServiceIntegrationTest {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;


    @Test
    public void updateUser_updates_correct_field(){

        UserDTO userDTO = new UserDTO("Test", "123", "Test test", "test@gmail.com",null,null,null, Role.USER);
        authenticationService.register(userDTO);

        UserUpdateDTO userUpdateDTO = new UserUpdateDTO("Test", null, "Mr Test", null, null, null, null, null);
        userService.updateUser(userUpdateDTO);
        User user = userRepository.findByUsername("Test").orElseThrow();

        assertEquals("Mr Test", user.getFullName());
    }

    @Test
    public void updateUser_does_not_update_if_only_null(){

        UserDTO userDTO = new UserDTO("Test", "123", "Test test", "test@gmail.com",null,null,null, Role.USER);
        authenticationService.register(userDTO);
        User user1 = userRepository.findByUsername("Test").orElseThrow();
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO("Test", null, null, null, null, null, null, null);
        userService.updateUser(userUpdateDTO);
        User user2 = userRepository.findByUsername("Test").orElseThrow();

        assertEquals(user1, user2);
    }

    @Test
    public void updateUser_throws_UserNotFoundException_for_non_existing_user(){

        UserDTO userDTO = new UserDTO("Test", "123", "Test test", "test@gmail.com",null,null,null, Role.USER);
        authenticationService.register(userDTO);

        UserUpdateDTO userUpdateDTO = new UserUpdateDTO("Nothing here", null, null, null, null, null, null, null);

        assertThrows(UserNotFoundException.class, () -> {
            userService.updateUser(userUpdateDTO);
        });

    }

    @Test
    @AutoConfigureTestDatabase
    public void updateUser_throws_UserExistsException_for_already_existing_user(){

        UserDTO userDTO1 = new UserDTO("Test", "123", "Test test", "test@gmail.com",null,null,null, Role.USER);
        UserDTO userDTO2 = new UserDTO("Existing", "123", "Test test", "test@gmail.com",null,null,null, Role.USER);
        authenticationService.register(userDTO1);
        authenticationService.register(userDTO2);

        UserUpdateDTO userUpdateDTO = new UserUpdateDTO("Test", "Existing", null, null, null, null, null, null);

        assertThrows(UserExistsException.class, () -> {
            userService.updateUser(userUpdateDTO);
        });

    }
}
