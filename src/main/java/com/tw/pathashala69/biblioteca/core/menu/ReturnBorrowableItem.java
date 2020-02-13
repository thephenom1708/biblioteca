package com.tw.pathashala69.biblioteca.core.menu;

import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotFoundException;
import com.tw.pathashala69.biblioteca.core.exception.IllegalBorrowableException;
import com.tw.pathashala69.biblioteca.core.models.Borrowable;
import com.tw.pathashala69.biblioteca.core.models.Library;
import com.tw.pathashala69.biblioteca.core.ui.UserInterface;

//Job: represents Return Book option menu item
public class ReturnBorrowableItem<T extends Borrowable> extends BaseMenuItem {
    private final UserInterface<T> userInterface;
    private final Library<T> library;

    public ReturnBorrowableItem(String title, String symbol, UserInterface<T> userInterface, Library<T> library) {
        super(title, symbol);
        this.userInterface = userInterface;
        this.library = library;
    }

    @Override
    public void onSelect() {
        String borrowableName = userInterface.promptForReturnBorrowable();

        Borrowable borrowableToReturn;
        try {
            borrowableToReturn = library.borrowables().searchByName(borrowableName);
            library.returnBorrowable(borrowableToReturn);
        } catch (BorrowableNotFoundException | IllegalBorrowableException e) {
            userInterface.onReturnBorrowableUnsuccessful();
            return;
        }
        userInterface.onReturnBorrowableSuccess();
    }
}
