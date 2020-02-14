package com.tw.pathashala69.biblioteca.core.menu;

import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotFoundException;
import com.tw.pathashala69.biblioteca.core.exception.IllegalBorrowableException;
import com.tw.pathashala69.biblioteca.core.models.Borrowable;
import com.tw.pathashala69.biblioteca.core.models.Library;
import com.tw.pathashala69.biblioteca.core.ui.BorrowableInterface;

//Job: represents Return Book option menu item
public class ReturnBorrowableItem<T extends Borrowable> extends BaseMenuItem {
    private final BorrowableInterface<T> borrowableInterface;
    private final Library<T> library;

    public ReturnBorrowableItem(String title, String symbol, BorrowableInterface<T> borrowableInterface, Library<T> library) {
        super(title, symbol);
        this.borrowableInterface = borrowableInterface;
        this.library = library;
    }

    @Override
    public void onSelect() {
        String borrowableName = borrowableInterface.promptForReturnBorrowable();

        Borrowable borrowableToReturn;
        try {
            borrowableToReturn = library.borrowables().searchByName(borrowableName);
            library.returnBorrowable(borrowableToReturn);
        } catch (BorrowableNotFoundException | IllegalBorrowableException e) {
            borrowableInterface.onReturnBorrowableUnsuccessful();
            return;
        }
        borrowableInterface.onReturnBorrowableSuccess();
    }
}
