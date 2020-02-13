package com.tw.pathashala69.biblioteca.core.ui;

import com.tw.pathashala69.biblioteca.core.models.Borrowables;
import com.tw.pathashala69.biblioteca.core.models.Movie;
import com.tw.pathashala69.biblioteca.view.constants.Message;

import java.util.Scanner;

public class BibliotecaMovieInterface implements UserInterface<Movie> {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void listBorrowables(Borrowables<Movie> borrowables) {
        borrowables.forEach(borrowable -> borrowable.print(System.out));
    }

    @Override
    public String promptForCheckoutBorrowable() {
        String outputMessage = Message.ENTER_INPUT_MESSAGE + " " + "Movie Name to checkout: ";
        System.out.println(outputMessage);
        return scanner.nextLine().trim();
    }

    @Override
    public String promptForReturnBorrowable() {
        String outputMessage = Message.ENTER_INPUT_MESSAGE + " " + "Movie Name to Return: ";
        System.out.println(outputMessage);
        return scanner.nextLine().trim();
    }

    @Override
    public void onCheckoutBorrowableUnsuccessful() {
        System.out.println(Message.CHECKOUT_MOVIE_UNSUCCESSFUL_MESSAGE);
    }

    @Override
    public void onCheckoutBorrowableSuccess() {
        System.out.println(Message.CHECKOUT_MOVIE_SUCCESSFUL_MESSAGE);
    }

    @Override
    public void onReturnBorrowableUnsuccessful() {
        System.out.println(Message.RETURN_MOVIE_UNSUCCESSFUL_MESSAGE);
    }

    @Override
    public void onReturnBorrowableSuccess() {
        System.out.println(Message.RETURN_MOVIE_SUCCESSFUL_MESSAGE);
    }

    @Override
    public void exit() {
        System.exit(0);
    }
}
