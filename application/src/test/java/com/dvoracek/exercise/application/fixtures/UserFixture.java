package com.dvoracek.exercise.application.fixtures;

import com.dvoracek.exercise.domain.user.User;

public class UserFixture {
    public static final Long id1 = 1L;
    public static final String email1 = "email1@company.com";
    public static final Long id2 = 2L;
    public static final String email2 = "email2@company.com";
    public static final Long id3 = 3L;
    public static final String email3 = "email3@company.com";

    public static User user1() {
        return new User()
                .setId(id1)
                .setEmail(email1);
    }

    public static User user2() {
        return new User()
                .setId(id2)
                .setEmail(email2);
    }

    public static User user3() {
        return new User()
                .setId(id3)
                .setEmail(email3);
    }
}
