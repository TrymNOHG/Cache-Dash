package edu.ntnu.idatt2105.g6.backend.service.security;

import edu.ntnu.idatt2105.g6.backend.dto.users.UserCreateDTO;
import edu.ntnu.idatt2105.g6.backend.security.AuthenticationRequest;
import edu.ntnu.idatt2105.g6.backend.security.AuthenticationResponse;

public interface IAuthenticationService {
    AuthenticationResponse register(UserCreateDTO userCreateDTO);
    AuthenticationResponse authenticate(AuthenticationRequest request);

}
