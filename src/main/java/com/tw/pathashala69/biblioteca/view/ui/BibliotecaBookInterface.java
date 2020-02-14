package com.tw.pathashala69.biblioteca.view.ui;

import com.tw.pathashala69.biblioteca.core.models.Book;
import com.tw.pathashala69.biblioteca.core.models.Borrowables;
import com.tw.pathashala69.biblioteca.core.ui.BorrowableInterface;
import com.tw.pathashala69.biblioteca.view.constants.Message;

import java.util.Scanner;

//Job: Represents Menu for Biblioteca
public class BibliotecaBookInterface implements BorrowableInterface<Book> {
    public static String welcome() {
        return Message.WELCOME_MESSAGE;
    }

    private Scanner scanner = new Scanner(System.in);

    @Override
    public String promptForCheckoutBorrowable() {
        String outputMessage = Message.ENTER_INPUT_MESSAGE + " " + "Book Name to checkout: ";
        System.out.println(outputMessage);
        return scanner.nextLine().trim();
    }

    @Override
    public String promptForReturnBorrowable() {
        String outputMessage = Message.ENTER_INPUT_MESSAGE + " " + "Book Name to Return: ";
        System.out.println(outputMessage);
        return scanner.nextLine().trim();
    }

    @Override
    public void listBorrowables(Borrowables<Book> borrowables) {
        borrowables.forEach(borrowable -> borrowable.print(System.out));
    }

    @Override
    public void onCheckoutBorrowableUnsuccessful() {
        System.out.println(Message.CHECKOUT_BOOK_UNSUCCESSFUL_MESSAGE);
    }

    @Override
    public void onCheckoutBorrowableSuccess() {
        System.out.println(Message.CHECKOUT_BOOK_SUCCESSFUL_MESSAGE);
    }

    @Override
    public void onReturnBorrowableUnsuccessful() {
        System.out.println(Message.RETURN_BOOK_UNSUCCESSFUL_MESSAGE);
    }

    @Override
    public void onReturnBorrowableSuccess() {
        System.out.println(Message.RETURN_BOOK_SUCCESSFUL_MESSAGE);
    }

    @Override
    public void exit() {
        System.exit(0);
    }

}
