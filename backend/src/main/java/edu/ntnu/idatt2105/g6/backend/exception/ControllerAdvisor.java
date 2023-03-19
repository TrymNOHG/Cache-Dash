package edu.ntnu.idatt2105.g6.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> unauthorizedAction(UnauthorizedException e, WebRequest webRequest) {
         /*
            Handle the logic around the exception. Do I do something to the database?
         */
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("Time of error: ", LocalDateTime.now());
        body.put("Message:", e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
