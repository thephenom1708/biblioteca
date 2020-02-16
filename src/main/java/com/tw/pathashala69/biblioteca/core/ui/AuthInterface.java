package com.tw.pathashala69.biblioteca.core.ui;

public interface AuthInterface extends UserInterface {
    String[] promptForLoginCredentials();

    void onInvalidLoginCredentials();

    void onUserAlreadyLoggedIn();

    void onLogout();
}
