package edu.ntnu.idatt2105.g6.backend.service;

import edu.ntnu.idatt2105.g6.backend.dto.users.UserDTO;
import edu.ntnu.idatt2105.g6.backend.security.AuthenticationRequest;
import edu.ntnu.idatt2105.g6.backend.security.AuthenticationResponse;
import edu.ntnu.idatt2105.g6.backend.model.users.Role;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import edu.ntnu.idatt2105.g6.backend.repo.users.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(UserDTO userDTO) {
        User user = User
                .builder()
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .role(Role.USER)
                .fullName(userDTO.getFullName())
                .email(userDTO.getEmail())
                .build();
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent())
            throw new IllegalStateException("Username already exists");
        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
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

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}