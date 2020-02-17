package com.tw.pathashala69.biblioteca.view.ui;

import com.tw.pathashala69.biblioteca.core.ui.AuthInterface;
import com.tw.pathashala69.biblioteca.view.constants.Message;

import java.util.Scanner;

public class BibliotecaAuthInterface implements AuthInterface {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public String[] promptForLoginCredentials() {
        System.out.println(Message.LIBRARY_NUMBER_PROMPT_MESSAGE);
        String libraryNumber = scanner.nextLine().trim();

        System.out.println(Message.PASSWORD_PROMPT_MESSAGE);
        String password = scanner.nextLine().trim();

        return new String[]{libraryNumber, password};
    }

    @Override
    public void onInvalidLoginCredentials() {
        System.out.println(Message.INVALID_CREDENTIALS_MESSAGE);
    }

    @Override
    public void onUserAlreadyLoggedIn() {
        System.out.println(Message.ALREADY_LOGGED_IN_MESSAGE);
    }

    @Override
    public void onLogout() {

    }
}
