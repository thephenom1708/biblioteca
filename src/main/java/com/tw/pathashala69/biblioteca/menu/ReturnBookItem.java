package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.constants.Symbol;
import com.tw.pathashala69.biblioteca.exception.BookNotFoundException;
import com.tw.pathashala69.biblioteca.exception.IllegalBookException;
import com.tw.pathashala69.biblioteca.io.InputManager;
import com.tw.pathashala69.biblioteca.io.OutputManager;
import com.tw.pathashala69.biblioteca.models.Book;
import com.tw.pathashala69.biblioteca.models.Library;

//Job: represents Return Book option menu item
public class ReturnBookItem extends BaseMenuItem {
    private final Library library;

    public ReturnBookItem(Library library) {
        super(Message.RETURN_BOOK_OPTION, Symbol.RB);
        this.library = library;
    }

    @Override
    public void onSelect() {
        String outputMessage = Message.ENTER_INPUT_MESSAGE + " " + "Book Name to Return: ";
        OutputManager.output(outputMessage, System.out);
        String bookName = InputManager.input(System.in);

        Book bookToReturn;
        try {
            bookToReturn = library.books().searchByName(bookName);
            library.returnBook(bookToReturn);
        } catch (BookNotFoundException | IllegalBookException e) {
            returnUnsuccessful();
            return;
        }

        OutputManager.output(Message.RETURN_BOOK_SUCCESS_MESSAGE, System.out);
    }

    private void returnUnsuccessful() {
        OutputManager.output(Message.RETURN_BOOK_UNSUCCESSFUL_MESSAGE, System.out);
    }
}
