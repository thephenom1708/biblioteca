package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.UserInterface;
import com.tw.pathashala69.biblioteca.core.models.Borrowable;
import com.tw.pathashala69.biblioteca.core.models.Borrowables;
import com.tw.pathashala69.biblioteca.core.models.Library;

//Job: Represents view all books menu item
public class BorrowableListItem<T extends Borrowable> extends BaseMenuItem {
    private final UserInterface userInterface;
    private final Library<T> library;

    public BorrowableListItem(String title, String symbol, UserInterface userInterface, Library<T> library) {
        super(title, symbol);
        this.userInterface = userInterface;
        this.library = library;
    }

    @Override
    public void onSelect() {
        userInterface.listBorrowables(library.borrowables().available());
    }
}
