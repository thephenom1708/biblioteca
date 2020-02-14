package com.tw.pathashala69.biblioteca.core.auth;

import com.tw.pathashala69.biblioteca.core.exception.InvalidCredentialsException;
import com.tw.pathashala69.biblioteca.core.exception.UserAlreadyLoggedInException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserAuthentication {
    private static final List<User> users = new ArrayList<>();
    private static final HashMap<User, Boolean> logInStatus = new HashMap<>();
    private static final HashMap<User, Session> userSessions = new HashMap<>();

    public static boolean register(User user) {
        logInStatus.put(user, false);
        userSessions.put(user, new Session());
        return users.add(user);
    }

    public static Session login(String libraryNumber, String password) throws InvalidCredentialsException, UserAlreadyLoggedInException {
        for (User user : users) {
            if (user.authenticate(libraryNumber, password)) {
                if (isLoggedIn(user))
                    throw new UserAlreadyLoggedInException();
                logInStatus.put(user, true);
                Session session = userSessions.get(user);
                session.start(user);
                return session;
            }
        }
        throw new InvalidCredentialsException();
    }

    public static void logout(User user) {
        userSessions.get(user).destroy();
        logInStatus.put(user, false);
    }

    public static Boolean isLoggedIn(User user) {
        return logInStatus.get(user);
    }
}
