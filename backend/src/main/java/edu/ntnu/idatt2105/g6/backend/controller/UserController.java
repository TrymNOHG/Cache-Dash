package edu.ntnu.idatt2105.g6.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingDTO;
import edu.ntnu.idatt2105.g6.backend.dto.listing.ListingLoadDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.UserCreateDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.UserLoadDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.UserPasswordUpdateDTO;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentication token",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthenticationResponse.class)) })}
    )
    public ResponseEntity<Object> register(@ParameterObject @RequestBody UserCreateDTO user) {
            logger.info("User " + user.username() + " is being registered!");
            return ResponseEntity.ok(authService.register(user));

    }

    @PostMapping("/auth/authenticate")
    @Operation(summary = "Authenticate a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentication token",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthenticationResponse.class)) })}
    )
    public ResponseEntity<AuthenticationResponse> register(@ParameterObject @RequestBody AuthenticationRequest request) throws Exception {
            logger.info("New Authentication request: " + request.toString());
            return ResponseEntity.ok(authService.authenticate(request));

    }

    @PutMapping(value = "/update/user", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Update user")
    public ResponseEntity<Object> update(@ParameterObject @RequestPart("userUpdateDTO") String userUpdateDTO,
                                         @ParameterObject @RequestPart("profilePicture") List<MultipartFile> profilePicture,
                                         Authentication authentication) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserUpdateDTO user = objectMapper.readValue(userUpdateDTO, UserUpdateDTO.class);
        if(!Objects.equals(authentication.getName(), user.username())) {
            logger.info("The user who sent the request is not the same as the one being changed.");
            throw new UnauthorizedException(authentication.getName());
        }

        byte[] profilePic;

        if(!profilePicture.isEmpty()) profilePic = profilePicture.get(0).getBytes();
        else profilePic = null;

        logger.info(String.format("User %s wants to been updated!", user.username()));
        userService.updateUser(user, profilePic);
        logger.info(String.format("User %s has been updated!", user.username()));

        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/update/password")
    @Operation(summary = "Update user")
    public ResponseEntity<Object> updatePassword(@ParameterObject @RequestBody UserPasswordUpdateDTO passwordUpdateDTO,
                                                 Authentication authentication) throws IOException {

        logger.info(String.format("User %s wants to been updated!", authentication.getName()));
        userService.updateUserPassword(passwordUpdateDTO, authentication.getName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/load")
    @Operation(summary = "Load user using current session token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loading user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserLoadDTO.class)) })}
    )
    public ResponseEntity<Object> load(@ParameterObject @AuthenticationPrincipal UserDetails user) {
        logger.info("Attempting to load user!");
        UserLoadDTO userLoadDTO = userService.loadUserDTOByUsername(user.getUsername());
        logger.info("User has been loaded!");
        return ResponseEntity.ok(userLoadDTO);
    }

    @GetMapping("/load/{username}")
    @Operation(summary = "Load user using current session token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loading user by username",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserLoadDTO.class)) })}
    )
    public ResponseEntity<Object> load(@ParameterObject @PathVariable String username) {
        logger.info("Attempting to load user:  " + username);
        UserLoadDTO userLoadDTO = userService.loadUserDTOByUsername(username);
        logger.info("User has been loaded!");
        return ResponseEntity.ok(userLoadDTO);
    }


}
