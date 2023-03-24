package edu.ntnu.idatt2105.g6.backend.controller;

import edu.ntnu.idatt2105.g6.backend.dto.users.UserCreateDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.UserLoadDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.UserUpdateDTO;
import edu.ntnu.idatt2105.g6.backend.exception.UnauthorizedException;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import edu.ntnu.idatt2105.g6.backend.security.AuthenticationRequest;
import edu.ntnu.idatt2105.g6.backend.security.AuthenticationResponse;
import edu.ntnu.idatt2105.g6.backend.service.security.AuthenticationService;
import edu.ntnu.idatt2105.g6.backend.service.users.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authService;
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<AuthenticationResponse> register(@ParameterObject @RequestBody UserCreateDTO user) {
        try {
            logger.info("User " + user.username() + " is being registered!");
            return ResponseEntity.ok(authService.register(user));
        }catch (Exception e) {
            logger.warn("Internal error has occurred: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/auth/authenticate")
    @Operation(summary = "Authenticate a user")
    public ResponseEntity<AuthenticationResponse> register(@ParameterObject @RequestBody AuthenticationRequest request) throws Exception {
        try{
            logger.info("New Authentication request: " + request.toString());
            return ResponseEntity.ok(authService.authenticate(request));
        } catch (Exception e) {
            logger.warn("Internal error has occurred: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update")
    @Operation(summary = "Update user")
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> update(@ParameterObject @RequestBody UserUpdateDTO user) {
        userService.updateUser(user);
        logger.info(String.format("User %s has been updated!", user.username()));
        return ResponseEntity.ok().build();
    }


    @GetMapping("/load")
    @Operation(summary = "Load user using current session token")
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> load(@ParameterObject @AuthenticationPrincipal UserDetails user) {
        UserLoadDTO userLoadDTO = userService.loadUserByUsername(user.getUsername());
        logger.info("User has been loaded!");
        return ResponseEntity.ok().build();
    }




}
