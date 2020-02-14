package com.tw.pathashala69.biblioteca.core.auth;

import com.tw.pathashala69.biblioteca.core.exception.InvalidCredentialsException;
import com.tw.pathashala69.biblioteca.core.exception.UserAlreadyLoggedInException;

import java.util.ArrayList;
import java.util.List;

public class UserAuthentication {
    private static final List<User> users = new ArrayList<>();

    public static boolean register(User user) {
        return users.add(user);
    }

    public static Session login(String libraryNumber, String password) throws InvalidCredentialsException, UserAlreadyLoggedInException {
        for (User user : users) {
            if (user.authenticate(libraryNumber, password)) {
                if (user.isLoggedIn())
                    throw new UserAlreadyLoggedInException();
                user.loggedIn();
                Session session = new Session();
                session.start(user);
                return session;
            }
        }
        throw new InvalidCredentialsException();
    }
}
