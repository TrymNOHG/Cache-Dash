package edu.ntnu.idatt2105.g6.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ntnu.idatt2105.g6.backend.BackendApplication;
import edu.ntnu.idatt2105.g6.backend.dto.users.UserCreateDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.UserLoadDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.UserUpdateDTO;
import edu.ntnu.idatt2105.g6.backend.exception.ControllerAdvisor;
import edu.ntnu.idatt2105.g6.backend.model.users.Role;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import edu.ntnu.idatt2105.g6.backend.security.AuthenticationRequest;
import edu.ntnu.idatt2105.g6.backend.security.AuthenticationResponse;
import edu.ntnu.idatt2105.g6.backend.security.JwtAuthenticationFilter;
import edu.ntnu.idatt2105.g6.backend.security.SecurityConfig;
import edu.ntnu.idatt2105.g6.backend.service.security.AuthenticationService;
import edu.ntnu.idatt2105.g6.backend.service.users.UserService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
////@SpringBootTest(
////        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
////        classes = BackendApplication.class)
////@TestPropertySource(
////        locations = "classpath:application-test.yml")
////@AutoConfigureMockMvc
////@ExtendWith(SpringExtension.class)
//@WebMvcTest
//class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    UserController userController;
//
//    @MockBean
//    UserService userService;
//
//    @MockBean
//    AuthenticationService authenticationService;
//
//    @MockBean
//    JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    @Before
//    public void setUp() {
//        this.mockMvc = MockMvcBuilders.standaloneSetup(userController)
//                .setControllerAdvice(new ControllerAdvisor())
//                .build();
//    }
//
//    @Test
//    void test1() throws Exception {
//        UserUpdateDTO user = new UserUpdateDTO("Trym", null, null,
//                null, null, null, null, null);
//        this.mockMvc.perform(post("/profile/update", user));
//
//    }
//
//}

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authService;



    @Test
    public void registerNewUser() throws Exception {
        // Arrange
        UserCreateDTO user = new UserCreateDTO("username", "password", "John doe", "john@gmail.com", null, null, null, Role.USER);

        // Act
        MvcResult mvcResult = mockMvc.perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        String response = mvcResult.getResponse().getContentAsString();
        AuthenticationResponse authenticationResponse = objectMapper.readValue(response, AuthenticationResponse.class);
        assertNotNull(authenticationResponse.getToken());
    }

    @Test
    public void authenticateUser() throws Exception {
        // Arrange
        UserCreateDTO user = new UserCreateDTO("username", "password", "John doe", "john@gmail.com", null, null, null, Role.USER);
        authService.register(user);

        AuthenticationRequest authenticationRequest = new AuthenticationRequest("username", "password");

        // Act
        MvcResult mvcResult = mockMvc.perform(post("/user/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authenticationRequest)))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        String response = mvcResult.getResponse().getContentAsString();
        AuthenticationResponse authenticationResponse = objectMapper.readValue(response, AuthenticationResponse.class);
        assertNotNull(authenticationResponse.getToken());
    }

    @Test
    public void loadUserByUsername() throws Exception {
        // Arrange
        UserCreateDTO userCreateDTO = new UserCreateDTO("username", "password", "John doe", "john@gmail.com", null, null, null, Role.USER);
        authService.register(userCreateDTO);

        // Act
        MvcResult mvcResult = mockMvc.perform(get("/user/load/{username}", userCreateDTO.username()))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        String response = mvcResult.getResponse().getContentAsString();
        UserLoadDTO userLoadDTO = objectMapper.readValue(response, UserLoadDTO.class);
        assertEquals(userCreateDTO.username(), userLoadDTO.username());
    }

}