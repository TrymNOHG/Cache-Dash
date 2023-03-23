package edu.ntnu.idatt2105.g6.backend.exception;

import edu.ntnu.idatt2105.g6.backend.exception.not_found.NotFoundException;
import edu.ntnu.idatt2105.g6.backend.exception.not_found.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> unauthorizedAction(UnauthorizedException e, WebRequest webRequest) {
         /*
            Handle the logic around the exception. Do I do something to the database?
         */
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("Time of error: ", LocalDateTime.now());
        body.put("Message:", e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> notFoundAction(UserNotFoundException e, WebRequest webRequest){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("Time of error: ", LocalDateTime.now());
        body.put("Message:", e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    };

}
