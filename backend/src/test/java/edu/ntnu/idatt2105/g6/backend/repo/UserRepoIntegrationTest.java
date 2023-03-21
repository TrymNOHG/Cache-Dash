package edu.ntnu.idatt2105.g6.backend.repo;//package edu.ntnu.idatt2105.g6.backend.repo;


import edu.ntnu.idatt2105.g6.backend.BackendApplication;
import edu.ntnu.idatt2105.g6.backend.exception.not_found.UserNotFoundException;
import edu.ntnu.idatt2105.g6.backend.model.users.Role;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import edu.ntnu.idatt2105.g6.backend.repo.users.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepoIntegrationTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void user_repository_retrieves_correct_user(){

        User user = User
                .builder()
                .username("Test")
                .password("123")
                .fullName("Test test")
                .email("test@test.test")
                .role(Role.USER)
                .build();
        repository.save(user);
        assertEquals(user, repository.findByUsername("Test").orElseThrow());
    }

    }