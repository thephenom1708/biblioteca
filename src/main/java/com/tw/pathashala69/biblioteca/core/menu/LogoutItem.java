package com.tw.pathashala69.biblioteca.core.menu;

import com.tw.pathashala69.biblioteca.core.auth.UserAuthentication;
import com.tw.pathashala69.biblioteca.core.ui.AuthInterface;

public class LogoutItem extends BaseMenuItem {
    private final AuthInterface authInterface;
    private final UserAuthentication userAuth;

    public LogoutItem(String title, String symbol, AuthInterface authInterface, UserAuthentication userAuth) {
        super(title, symbol);
        this.authInterface = authInterface;
        this.userAuth = userAuth;
    }

    @Override
    public void onSelect() {
        userAuth.logout();
        authInterface.onLogout();
    }
}
