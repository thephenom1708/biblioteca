package com.tw.pathashala69.biblioteca.core.auth;

import com.tw.pathashala69.biblioteca.core.exception.InvalidCredentialsException;

import java.util.ArrayList;
import java.util.List;

public class UserAuthentication {
    private static final List<User> users = new ArrayList<>();

    public static boolean register(User user) {
        return users.add(user);
    }

    public static User login(String libraryNumber, String password) throws InvalidCredentialsException {
        for (User user : users) {
            if (user.authenticate(libraryNumber, password))
                return user;
        }
        throw new InvalidCredentialsException();
    }
}
