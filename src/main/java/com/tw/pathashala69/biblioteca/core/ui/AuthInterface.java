package com.tw.pathashala69.biblioteca.core.ui;

import com.tw.pathashala69.biblioteca.core.auth.User;

public interface AuthInterface extends UserInterface {
    User promptForLoginCredentials();
}
