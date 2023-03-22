package edu.ntnu.idatt2105.g6.backend.repo;//package edu.ntnu.idatt2105.g6.backend.repo;


import edu.ntnu.idatt2105.g6.backend.model.users.Role;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import edu.ntnu.idatt2105.g6.backend.repo.users.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
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