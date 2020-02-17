package com.tw.pathashala69.biblioteca.view.ui;

import com.tw.pathashala69.biblioteca.core.ui.AuthInterface;

public class BibliotecaAuthInterface implements AuthInterface {
    @Override
    public String[] promptForLoginCredentials() {
        return new String[0];
    }

    @Override
    public void onInvalidLoginCredentials() {

    }

    @Override
    public void onUserAlreadyLoggedIn() {

    }

    @Override
    public void onLogout() {

    }
}
