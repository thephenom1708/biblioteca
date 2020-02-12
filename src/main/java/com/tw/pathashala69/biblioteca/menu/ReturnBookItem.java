package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.Biblioteca;
import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.constants.Symbol;
import com.tw.pathashala69.biblioteca.core.exception.BookNotFoundException;
import com.tw.pathashala69.biblioteca.core.exception.IllegalBookException;
import com.tw.pathashala69.biblioteca.core.models.Book;
import com.tw.pathashala69.biblioteca.core.models.Library;

//Job: represents Return Book option menu item
public class ReturnBookItem extends BaseMenuItem {
    private final Biblioteca biblioteca;

    public ReturnBookItem(Biblioteca biblioteca) {
        super(Message.RETURN_BOOK_OPTION, Symbol.RB);
        this.biblioteca = biblioteca;
    }

    @Override
    public void onSelect() {
        String bookName = biblioteca.returnBookInput();
        Library library = biblioteca.library();

        Book bookToReturn;
        try {
            bookToReturn = library.books().searchByName(bookName);
            library.returnBook(bookToReturn);
        } catch (BookNotFoundException | IllegalBookException e) {
            biblioteca.onReturnBookUnsuccessful();
            return;
        }
        biblioteca.onReturnBookSuccess();
    }
}
