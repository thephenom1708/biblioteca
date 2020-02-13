package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.UserInterface;
import com.tw.pathashala69.biblioteca.core.models.Borrowables;

//Job: Represents view all books menu item
public abstract class BorrowableListItem extends BaseMenuItem {
    private final UserInterface userInterface;

    public BorrowableListItem(String title, String symbol, UserInterface userInterface) {
        super(title, symbol);
        this.userInterface = userInterface;
    }

    @Override
    public void onSelect() {
        userInterface.listBorrowables(borrowables().available());
    }

    protected abstract Borrowables borrowables();
}
