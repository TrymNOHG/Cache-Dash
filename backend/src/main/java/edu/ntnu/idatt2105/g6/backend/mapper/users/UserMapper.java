package edu.ntnu.idatt2105.g6.backend.mapper.users;

import edu.ntnu.idatt2105.g6.backend.dto.users.UserCreateDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.UserLoadDTO;
import edu.ntnu.idatt2105.g6.backend.model.users.User;

/**
 This mapper class is responsible for converting User objects to UserLoadDTO objects and vice versa.
 */
public class UserMapper {

    /**
     * Convert a UserCreateDTO object to a User object.
     *
     * @param userCreateDTO the UserCreateDTO object to be converted
     * @return a User object
     */
    public static User toUser(UserCreateDTO userCreateDTO) { //TODO: check with AuthenticationService
        return User.builder()
                .username(userCreateDTO.username())
                .password(userCreateDTO.password())
                .fullName(userCreateDTO.fullName())
                .email(userCreateDTO.email())
                .role(userCreateDTO.role())
                .birthDate(userCreateDTO.birthDate())
                .phone(userCreateDTO.phone())
                .picture(userCreateDTO.picture())
                .build();
    }

    /**
     * Convert a User object to a UserLoadDTO object.
     *
     * @param user the User object to be converted
     * @return a UserLoadDTO object
     */
    public static UserLoadDTO userLoadDTO(User user) {
        UserLoadDTO userLoadDTO = UserLoadDTO
                .builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .birthDate(user.getBirthDate())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .phone(user.getPhone())
                .picture(user.getPicture())
                .role(user.getRole())
                .build();

        return userLoadDTO;
    }



}
