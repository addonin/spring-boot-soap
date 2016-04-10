package com.epam.springadvanced.service.exception;

public class UserNotRegisteredException extends RuntimeException {

    public UserNotRegisteredException() {
        super("Sorry, user not found!");
    }

}
