package com.tw.pathashala69.biblioteca.core.menu;

import com.tw.pathashala69.biblioteca.core.auth.UserAuthentication;
import com.tw.pathashala69.biblioteca.core.exception.InvalidCredentialsException;
import com.tw.pathashala69.biblioteca.core.exception.UserAlreadyLoggedInException;
import com.tw.pathashala69.biblioteca.core.ui.AuthInterface;

public class LoginItem extends BaseMenuItem {
    private final AuthInterface authInterface;

    public LoginItem(String title, String symbol, AuthInterface authInterface) {
        super(title, symbol);
        this.authInterface = authInterface;
    }

    @Override
    public void onSelect() {
        String[] credentials = authInterface.promptForLoginCredentials();

        try {
            UserAuthentication.login(credentials[0], credentials[1]);
        } catch (InvalidCredentialsException e) {
            authInterface.onInvalidLoginCredentials();
        } catch (UserAlreadyLoggedInException e) {
            authInterface.onUserAlreadyLoggedIn();
        }
    }
}
