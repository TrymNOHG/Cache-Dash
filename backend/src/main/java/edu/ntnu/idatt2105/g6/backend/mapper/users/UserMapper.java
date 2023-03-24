package edu.ntnu.idatt2105.g6.backend.mapper.users;

import edu.ntnu.idatt2105.g6.backend.dto.users.UserDTO;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import org.springframework.stereotype.Service;

public class UserMapper {

    public static User toUser(UserDTO userDTO) { //TODO: check with AuthenticationService
        return User.builder()
                .username(userDTO.username())
                .password(userDTO.password())
                .fullName(userDTO.fullName())
                .email(userDTO.email())
                .role(userDTO.role())
                .birthDate(userDTO.birthDate())
                .phone(userDTO.phone())
                .picture(userDTO.picture())
                .build();
    }




}
