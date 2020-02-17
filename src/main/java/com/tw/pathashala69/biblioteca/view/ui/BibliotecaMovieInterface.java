package com.tw.pathashala69.biblioteca.view.ui;

import com.tw.pathashala69.biblioteca.core.auth.User;
import com.tw.pathashala69.biblioteca.core.models.Borrowable;
import com.tw.pathashala69.biblioteca.core.models.Borrowables;
import com.tw.pathashala69.biblioteca.core.models.Movie;
import com.tw.pathashala69.biblioteca.core.ui.BorrowableInterface;
import com.tw.pathashala69.biblioteca.view.constants.Message;

import java.util.HashMap;
import java.util.Scanner;

public class BibliotecaMovieInterface implements BorrowableInterface<Movie> {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void listBorrowables(Borrowables<Movie> borrowables) {
        borrowables.forEach(borrowable -> borrowable.print(System.out));
    }

    @Override
    public void listCheckedOutBorrowables(HashMap<Borrowable, User> borrowableUserHashMap) {

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
}
