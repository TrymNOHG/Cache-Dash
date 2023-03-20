package edu.ntnu.idatt2105.g6.backend.model;

import edu.ntnu.idatt2105.g6.backend.BackendApplication;
import edu.ntnu.idatt2105.g6.backend.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class)
@RequiredArgsConstructor
class UserTest {
    @Autowired
    UserRepository userRepository;
    @Test
    void username_cannot_be_null(){
        User user = new User();

        assertThrows(SQLException.class, () -> {
            userRepository.save(user);
        });

    }

}