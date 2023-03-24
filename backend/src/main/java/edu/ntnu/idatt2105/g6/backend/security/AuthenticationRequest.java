package edu.ntnu.idatt2105.g6.backend.security;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    //TODO: Maybe move to dto
    @NonNull
    private String username;

    @NonNull
    private String password;
}