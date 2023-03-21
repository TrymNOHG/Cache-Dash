package edu.ntnu.idatt2105.g6.backend.repo;//package edu.ntnu.idatt2105.g6.backend.repo;
//
//import edu.ntnu.idatt2105.g6.backend.BackendApplication;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = BackendApplication.class)
////@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT)
////@SpringBootTest(webEnvironment=WebEnvironment.NONE)
////@SpringBootTest(webEnvironment=WebEnvironment.)
//class UserRepoIntegrationTest {
//
//    @Test
//    void test1() {
//
//    }
//
//}

import edu.ntnu.idatt2105.g6.backend.BackendApplication;
import edu.ntnu.idatt2105.g6.backend.repo.users.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = BackendApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.yml")
public class UserRepoIntegrationTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository repository;

    @Test
    public void test1(){
        assert true;
    }

    }