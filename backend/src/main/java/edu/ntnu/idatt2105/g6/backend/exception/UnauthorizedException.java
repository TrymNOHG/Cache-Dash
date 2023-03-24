package edu.ntnu.idatt2105.g6.backend.exception;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String username) {
        super(String.format("User, %s, is not authorized", username));
    }


}
