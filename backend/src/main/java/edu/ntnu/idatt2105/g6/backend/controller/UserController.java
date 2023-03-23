package edu.ntnu.idatt2105.g6.backend.controller;

import edu.ntnu.idatt2105.g6.backend.dto.users.UserUpdateDTO;
import edu.ntnu.idatt2105.g6.backend.service.security.AuthenticationService;
import edu.ntnu.idatt2105.g6.backend.service.users.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService service;
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("/update")
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> update(@RequestBody UserUpdateDTO user) {
        userService.updateUser(user);
        logger.info(String.format("User %s has been updated!", user.username()));
        return ResponseEntity.ok().build();
    }

    // delete

    //



//}catch (NoSuchElementException e){
//        return ResponseEntity.noContent().build();
//        }
//
//        return ResponseEntity.accepted().body("The user was updated");


}
