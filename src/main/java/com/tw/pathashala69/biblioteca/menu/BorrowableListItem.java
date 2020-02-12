package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.UserInterface;
import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.constants.Symbol;
import com.tw.pathashala69.biblioteca.core.models.Library;

//Job: Represents view all books menu item
public class BorrowableListItem extends BaseMenuItem {
    private final UserInterface userInterface;
    private final Library library;

    public BorrowableListItem(UserInterface userInterface, Library library) {
        super(Message.BOOKS_LIST_OPTION, Symbol.B);
        this.userInterface = userInterface;
        this.library = library;
    }

    @Override
    public void onSelect() {
        userInterface.printBorrowable(library.books().available());
    }
}
