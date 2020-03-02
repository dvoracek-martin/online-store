package com.dvoracek.exercise.application.user;

public class CreateUserDto {

    private String email;

    public String getEmail() {
        return email;
    }

    public CreateUserDto setEmail(String email) {
        this.email = email;
        return this;
    }
}
