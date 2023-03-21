package edu.ntnu.idatt2105.g6.backend.exception.exists;

public class UserExistsException extends ExistsException{

    public UserExistsException(String username) {
        super("User", username);
    }

}
