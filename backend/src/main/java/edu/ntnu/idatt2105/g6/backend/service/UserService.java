package edu.ntnu.idatt2105.g6.backend.service;

import edu.ntnu.idatt2105.g6.backend.dto.users.UserDTO;
import edu.ntnu.idatt2105.g6.backend.dto.users.UserUpdateDTO;
import edu.ntnu.idatt2105.g6.backend.exception.UserExistsException;
import edu.ntnu.idatt2105.g6.backend.mapper.users.UserMapper;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import edu.ntnu.idatt2105.g6.backend.repo.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void updateUser(UserUpdateDTO userUpdateDTO){

            User user = userRepository.findByUsername(userUpdateDTO.username()).orElseThrow();
            Optional<User> newUser = userRepository.findByUsername(userUpdateDTO.newUsername());
            if (newUser.isPresent()) throw new UserExistsException(userUpdateDTO.newUsername());
            //TODO: NewUsername unique?
//            user = User
//                    .builder()
//                    .username(userUpdateDTO.newUsername() != null ? userUpdateDTO.username() : user.getUsername())
//                    .email()
//                    .fullName()
//                    .role()
//                    .picture()
//                    .build();

            userRepository.save(user);

    }
}
