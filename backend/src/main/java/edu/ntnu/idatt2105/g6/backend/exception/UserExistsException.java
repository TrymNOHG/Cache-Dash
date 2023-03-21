package edu.ntnu.idatt2105.g6.backend.exception;

public class UserExistsException extends RuntimeException{

    public UserExistsException(String username) {
        super(String.format("User, %s, already exists", username));
    }


}
