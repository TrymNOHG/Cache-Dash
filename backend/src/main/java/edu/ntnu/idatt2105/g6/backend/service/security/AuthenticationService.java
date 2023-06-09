package edu.ntnu.idatt2105.g6.backend.service.security;

import edu.ntnu.idatt2105.g6.backend.dto.users.UserCreateDTO;
import edu.ntnu.idatt2105.g6.backend.exception.exists.UserExistsException;
import edu.ntnu.idatt2105.g6.backend.security.AuthenticationRequest;
import edu.ntnu.idatt2105.g6.backend.security.AuthenticationResponse;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import edu.ntnu.idatt2105.g6.backend.repo.users.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 This service class handles the business logic for authentication-related operations.
 It implements the IAuthenticationService interface.

 */
@RequiredArgsConstructor
@Service
public class AuthenticationService implements IAuthenticationService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    private final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    /**
     * Registers a user to the system.
     *
     * @param userCreateDTO the information of the user to be registered.
     * @return an AuthenticationResponse containing the JWT token of the user.
     * @throws UserExistsException if the username of the user already exists in the database.
     */
    @Transactional
    public AuthenticationResponse register(UserCreateDTO userCreateDTO) {
        User user = User
                .builder()
                .username(userCreateDTO.username())
                .password(passwordEncoder.encode(userCreateDTO.password()))
                .role(userCreateDTO.role())
                .fullName(userCreateDTO.fullName())
                .email(userCreateDTO.email())
                .build();
        if (userRepository.findByUsername(userCreateDTO.username()).isPresent())
            throw new UserExistsException("Username already exists");
        userRepository.save(user);

        logger.info(String.format("User %s has been saved in the DB!", user.getUsername()));

        String jwtToken = jwtService.generateToken(user);
        logger.info("Their JWT is: " + jwtToken);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    /**
     * Authenticates a user with the given credentials.
     *
     * @param request the authentication request containing the username and password of the user.
     * @return an AuthenticationResponse containing the JWT token of the authenticated user.
     * @throws UsernameNotFoundException if the username of the user is not found in the database.
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }
}