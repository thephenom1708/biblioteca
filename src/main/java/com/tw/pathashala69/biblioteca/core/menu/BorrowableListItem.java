package com.tw.pathashala69.biblioteca.core.menu;

import com.tw.pathashala69.biblioteca.core.models.Borrowable;
import com.tw.pathashala69.biblioteca.core.models.Library;
import com.tw.pathashala69.biblioteca.core.ui.UserInterface;

//Job: Represents view all books menu item
public class BorrowableListItem<T extends Borrowable> extends BaseMenuItem {
    private final UserInterface<T> userInterface;
    private final Library<T> library;

    public BorrowableListItem(String title, String symbol, UserInterface<T> userInterface, Library<T> library) {
        super(title, symbol);
        this.userInterface = userInterface;
        this.library = library;
    }

    @Override
    public void onSelect() {
        userInterface.listBorrowables(library.borrowables().available());
    }
}
