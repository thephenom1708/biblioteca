package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.UserInterface;
import com.tw.pathashala69.biblioteca.core.models.Borrowables;
import com.tw.pathashala69.biblioteca.core.models.Library;

public class MovieListItem extends BorrowableListItem {
    private final Library library;

    public MovieListItem(String title, String symbol, UserInterface userInterface, Library library) {
        super(title, symbol, userInterface);
        this.library = library;
    }

    @Override
    protected Borrowables borrowables() {
        return library.movies();
    }
}
