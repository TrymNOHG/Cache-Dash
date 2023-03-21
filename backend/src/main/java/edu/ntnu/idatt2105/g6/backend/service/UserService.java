package edu.ntnu.idatt2105.g6.backend.service;

import edu.ntnu.idatt2105.g6.backend.dto.users.UserUpdateDTO;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import edu.ntnu.idatt2105.g6.backend.repo.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Object> updateUser(UserUpdateDTO userUpdateDTO){

        try {
            User user = userRepository.findByUsername(userUpdateDTO.username()).orElseThrow();


            userRepository.save(user);
        }catch (NoSuchElementException e){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.accepted().body("The user was updated");
    }
}
