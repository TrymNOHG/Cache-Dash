package edu.ntnu.idatt2105.g6.backend.exception.not_found;

public class UserNotFoundException extends NotFoundException{
    public UserNotFoundException(String value) {
        super("User", value);
    }
    public UserNotFoundException(Long id) {
        super("User", id);
    }
}
