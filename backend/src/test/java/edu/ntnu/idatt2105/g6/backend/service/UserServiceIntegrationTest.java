package edu.ntnu.idatt2105.g6.backend.service;

import edu.ntnu.idatt2105.g6.backend.dto.users.UserCreateDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.UserDeletionDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.UserUpdateDTO;
import edu.ntnu.idatt2105.g6.backend.exception.UnauthorizedException;
import edu.ntnu.idatt2105.g6.backend.exception.exists.UserExistsException;
import edu.ntnu.idatt2105.g6.backend.exception.not_found.UserNotFoundException;
import edu.ntnu.idatt2105.g6.backend.model.users.Role;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import edu.ntnu.idatt2105.g6.backend.repo.users.UserRepository;
import edu.ntnu.idatt2105.g6.backend.service.security.AuthenticationService;
import edu.ntnu.idatt2105.g6.backend.service.users.UserService;
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
public class UserServiceIntegrationTest {



    @Nested
    @SpringBootTest
    class UpdateUser{
        @Autowired
        AuthenticationService authenticationService;

        @Autowired
        UserService userService;

        @Autowired
        UserRepository userRepository;

        @Test
        public void updateUser_updates_correct_field(){
            UserCreateDTO userCreateDTO = new UserCreateDTO("Test", "123", "Test test", "test@gmail.com",null,null,null, Role.USER);
            authenticationService.register(userCreateDTO);

            UserUpdateDTO userUpdateDTO = new UserUpdateDTO("Test", null, "Mr Test", null, null, null, null, null);
            userService.updateUser(userUpdateDTO);

            User user = userRepository.findByUsername("Test").orElseThrow();
            System.out.println(user);

            assertEquals("Mr Test", user.getFullName());
        }

        @Test
        public void updateUser_does_not_update_if_only_null(){

            UserCreateDTO userCreateDTO = new UserCreateDTO("Test", "123", "Test test", "test@gmail.com",null,null,null, Role.USER);
            authenticationService.register(userCreateDTO);
            User user1 = userRepository.findByUsername("Test").orElseThrow();
            UserUpdateDTO userUpdateDTO = new UserUpdateDTO("Test", null, null, null, null, null, null, null);
            userService.updateUser(userUpdateDTO);
            User user2 = userRepository.findByUsername("Test").orElseThrow();

            assertEquals(user1, user2);
        }

        @Test
        public void updateUser_throws_UserNotFoundException_for_non_existing_user(){

            UserCreateDTO userCreateDTO = new UserCreateDTO("Test", "123", "Test test", "test@gmail.com",null,null,null, Role.USER);
            authenticationService.register(userCreateDTO);

            UserUpdateDTO userUpdateDTO = new UserUpdateDTO("Nothing here", null, null, null, null, null, null, null);

            assertThrows(UserNotFoundException.class, () -> {
                userService.updateUser(userUpdateDTO);
            });

        }

        @Test
        public void updateUser_throws_UserExistsException_for_already_existing_user(){
            UserCreateDTO userCreateDTO1 = new UserCreateDTO("Test", "123", "Test test", "test@gmail.com",null,null,null, Role.USER);
            UserCreateDTO userCreateDTO2 = new UserCreateDTO("Existing", "123", "Test test", "test@gmail.com",null,null,null, Role.USER);
            authenticationService.register(userCreateDTO1);
            authenticationService.register(userCreateDTO2);

            UserUpdateDTO userUpdateDTO = new UserUpdateDTO("Test", "Existing", null, null, null, null, null, null);

            assertThrows(UserExistsException.class, () -> {
                userService.updateUser(userUpdateDTO);
            });

        }
    }

    @Nested
    @SpringBootTest
    class DeleteUser{
        @Autowired
        AuthenticationService authenticationService;

        @Autowired
        UserService userService;

        @Autowired
        UserRepository userRepository;

        @Test
        public void deleteUser_deletes_user(){

            UserCreateDTO userCreateDTO1 = new UserCreateDTO("Test", "123", "Test test", "test@gmail.com",null,null,null, Role.USER);
            authenticationService.register(userCreateDTO1);
            assertDoesNotThrow(() -> {
                userRepository.findByUsername("Test");
            });

            UserDeletionDTO userDeletionDTO = new UserDeletionDTO("Test", "Test");
            userService.deleteUser(userDeletionDTO);
            assertThrows(UserNotFoundException.class, () -> {
                userRepository.findByUsername("Test").orElseThrow(() -> new UserNotFoundException("Test"));
            });

        }

        @Test
        public void deleteUser_throws_UnauthorizedException(){
            UserCreateDTO userCreateDTO1 = new UserCreateDTO("Test", "123", "Test test", "test@gmail.com",null,null,null, Role.USER);
            UserCreateDTO userCreateDTO2 = new UserCreateDTO("Mr Test", "124", "Mr test", "Mrtest@gmail.com",null,null,null, Role.USER);
            authenticationService.register(userCreateDTO1);
            authenticationService.register(userCreateDTO2);

            UserDeletionDTO userDeletionDTO = new UserDeletionDTO("Mr Test", "Test");

            assertThrows(UnauthorizedException.class, () -> {
                userService.deleteUser(userDeletionDTO);
            });
        }

        @Test
        public void admin_can_delete_user(){
            UserCreateDTO userCreateDTO1 = new UserCreateDTO("Test", "123", "Test test", "test@gmail.com",null,null,null, Role.USER);
            UserCreateDTO userCreateDTO2 = new UserCreateDTO("Mr Test", "124", "Mr test", "Mrtest@gmail.com",null,null,null, Role.ADMIN);
            authenticationService.register(userCreateDTO1);
            authenticationService.register(userCreateDTO2);

            UserDeletionDTO userDeletionDTO = new UserDeletionDTO("Mr Test", "Test");
            System.out.println(userRepository.findByUsername("Mr Test").orElseThrow().getRole());
            assertDoesNotThrow(() -> {
                userService.deleteUser(userDeletionDTO);
            });
        }
    }



}
