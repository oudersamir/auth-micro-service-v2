package com.auth.micro.responses;


public class UserException extends RuntimeException{

    private static final long serialVersionUID = -7134668461181559165L;

    public UserException(String message) {
        super(message);
    }

}
