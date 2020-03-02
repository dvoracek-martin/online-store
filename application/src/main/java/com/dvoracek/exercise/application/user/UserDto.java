package com.dvoracek.exercise.application.user;

import com.dvoracek.exercise.domain.user.User;

public class UserDto {

    private Long id;

    private String email;

    public Long getId() {
        return id;
    }

    public UserDto setId(Long id) {
        this.id = id;
        return this;
    }

    public UserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public static UserDto toUserDto(User user) {
        return new UserDto().setId(user.getId())
                .setEmail(user.getEmail());
    }
}
