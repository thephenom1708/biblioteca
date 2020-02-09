package com.tw.pathashala69.biblioteca.action;

import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.exception.BookNotAvailable;
import com.tw.pathashala69.biblioteca.exception.BookNotFoundException;
import com.tw.pathashala69.biblioteca.io.InputManager;
import com.tw.pathashala69.biblioteca.io.OutputManager;
import com.tw.pathashala69.biblioteca.models.Book;
import com.tw.pathashala69.biblioteca.models.Library;

public class ReturnBookAction implements Action {
    private final Library library;

    public ReturnBookAction(Library library) {
        this.library = library;
    }

    @Override
    public void perform() {
        String outputMessage = Message.ENTER_INPUT_MESSAGE + " " + "Book Name to Return: ";
        OutputManager.output(outputMessage, System.out);
        String bookName = InputManager.input(System.in);

        Book bookToReturn;
        try {
            bookToReturn = library.books().searchByName(bookName);
            library.returnBook(bookToReturn);
        } catch (BookNotFoundException e) {
            System.out.println("no");
        }
    }
}
