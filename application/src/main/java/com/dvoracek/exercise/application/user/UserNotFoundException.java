package com.dvoracek.exercise.application.user;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("User with id:" + id + " wasn't found.");
    }
}
