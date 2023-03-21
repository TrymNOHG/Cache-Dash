package edu.ntnu.idatt2105.g6.backend.mapper.users;

import edu.ntnu.idatt2105.g6.backend.dto.users.UserDTO;
import edu.ntnu.idatt2105.g6.backend.model.users.User;

public class UserMapper {

    public static User toUser(UserDTO userDTO) { //TODO: check with AuthenticationService
        return User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .fullName(userDTO.getFullName())
                .email(userDTO.getEmail())
                .role(userDTO.getRole())
                .birthDate(userDTO.getBirthDate())
                .phone(userDTO.getPhone())
                .picture(userDTO.getPicture())
                .build();
    }




}
