package com.app.exception;

/**
 * @author Shailesh
 **/

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
