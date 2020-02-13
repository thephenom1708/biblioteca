package com.tw.pathashala69.biblioteca;

import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.core.models.Borrowables;

import java.io.PrintStream;
import java.util.Scanner;

//Job: Represents Menu for Biblioteca
public class Biblioteca implements UserInterface {
    private final PrintStream stream;

    public Biblioteca(PrintStream stream) {
        this.stream = stream;
    }

    public static String welcome() {
        return Message.WELCOME_MESSAGE;
    }

    private Scanner scanner = new Scanner(System.in);

    @Override
    public String promptForCheckoutBook() {
        String outputMessage = Message.ENTER_INPUT_MESSAGE + " " + "Book Name to checkout: ";
        System.out.println(outputMessage);
        return scanner.nextLine().trim();
    }

    @Override
    public String promptForReturnBook() {
        String outputMessage = Message.ENTER_INPUT_MESSAGE + " " + "Book Name to Return: ";
        System.out.println(outputMessage);
        return scanner.nextLine().trim();
    }

    @Override
    public void listBorrowables(Borrowables borrowables) {
        borrowables.forEach(borrowable -> borrowable.print(stream));
    }

    @Override
    public void onCheckoutBookUnsuccessful() {
        System.out.println(Message.CHECKOUT_BOOK_UNSUCCESSFUL_MESSAGE);
    }

    @Override
    public void onCheckoutBookSuccess() {
        System.out.println(Message.CHECKOUT_BOOK_SUCCESS_MESSAGE);
    }

    @Override
    public void onReturnBookUnsuccessful() {
        System.out.println(Message.RETURN_BOOK_UNSUCCESSFUL_MESSAGE);
    }

    @Override
    public void onReturnBookSuccess() {
        System.out.println(Message.RETURN_BOOK_SUCCESS_MESSAGE);
    }

    @Override
    public void exit() {
        System.exit(0);
    }
}
