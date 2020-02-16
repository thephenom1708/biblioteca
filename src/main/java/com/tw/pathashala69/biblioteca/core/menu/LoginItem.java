package com.tw.pathashala69.biblioteca.core.menu;

import com.tw.pathashala69.biblioteca.core.auth.UserAuthentication;
import com.tw.pathashala69.biblioteca.core.exception.InvalidCredentialsException;
import com.tw.pathashala69.biblioteca.core.exception.UserAlreadyLoggedInException;
import com.tw.pathashala69.biblioteca.core.ui.AuthInterface;

public class LoginItem extends BaseMenuItem {
    private final AuthInterface authInterface;
    private final UserAuthentication userAuth;

    public LoginItem(String title, String symbol, AuthInterface authInterface, UserAuthentication userAuth) {
        super(title, symbol);
        this.authInterface = authInterface;
        this.userAuth = userAuth;
    }

    @Override
    public void onSelect() {
        String[] credentials = authInterface.promptForLoginCredentials();

        try {
            userAuth.login(credentials[0], credentials[1]);
        } catch (InvalidCredentialsException e) {

        } catch (UserAlreadyLoggedInException e) {

        }
    }
}
