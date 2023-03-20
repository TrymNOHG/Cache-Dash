package edu.ntnu.idatt2105.g6.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationDTO {

    private String username;
    private String password;
    private String email;


}
