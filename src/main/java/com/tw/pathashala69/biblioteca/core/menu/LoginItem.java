package com.tw.pathashala69.biblioteca.core.menu;

import com.tw.pathashala69.biblioteca.core.auth.User;
import com.tw.pathashala69.biblioteca.core.ui.AuthInterface;

public class LoginItem extends BaseMenuItem {
    private final AuthInterface authInterface;

    public LoginItem(String title, String symbol, AuthInterface authInterface) {
        super(title, symbol);
        this.authInterface = authInterface;
    }

    @Override
    public void onSelect() {
        User credentials = authInterface.promptForLoginCredentials();
    }
}
