package com.tw.pathashala69.biblioteca.core.auth;

import com.tw.pathashala69.biblioteca.core.exception.InvalidCredentialsException;
import com.tw.pathashala69.biblioteca.core.exception.UserAlreadyLoggedInException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserAuthentication {
    private static final List<User> users = new ArrayList<>();
    private static final HashMap<User, Boolean> logInStatus = new HashMap<>();
    private Session activeSession;

    public static boolean register(User user) {
        logInStatus.put(user, false);
        return users.add(user);
    }

    public Session login(String libraryNumber, String password) throws InvalidCredentialsException, UserAlreadyLoggedInException {
        for (User user : users) {
            if (user.authenticate(libraryNumber, password)) {
                if (isLoggedIn(user))
                    throw new UserAlreadyLoggedInException();
                logInStatus.put(user, true);
                activeSession = new Session(user);
                return activeSession;
            }
        }
        throw new InvalidCredentialsException();
    }

    public void logout() {
        activeSession.destroy();
        logInStatus.put(activeSession.user(), false);
    }

    public Boolean isLoggedIn(User user) {
        return logInStatus.get(user);
    }

    public UserPrivilege userPrivilege() {
        return UserPrivilege.GUEST;
    }
}
