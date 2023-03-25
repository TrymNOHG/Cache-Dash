package edu.ntnu.idatt2105.g6.backend.service.security;

import edu.ntnu.idatt2105.g6.backend.dto.users.UserCreateDTO;
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


@RequiredArgsConstructor
@Service
public class AuthenticationService implements IAuthenticationService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    private final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

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
            throw new IllegalStateException("Username already exists");
        userRepository.save(user);

        logger.info(String.format("User %s has been saved in the DB!", user.getUsername()));

        String jwtToken = jwtService.generateToken(user);
        logger.info("Their JWT is: " + jwtToken);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

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