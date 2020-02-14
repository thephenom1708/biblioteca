package com.tw.pathashala69.biblioteca.core.menu;

import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotAvailableException;
import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotFoundException;
import com.tw.pathashala69.biblioteca.core.models.Borrowable;
import com.tw.pathashala69.biblioteca.core.models.Library;
import com.tw.pathashala69.biblioteca.core.ui.BorrowableInterface;

//Job: Represents checkout book item
public class CheckoutBorrowableItem<T extends Borrowable> extends BaseMenuItem {
    private final BorrowableInterface<T> borrowableInterface;
    private final Library<T> library;

    public CheckoutBorrowableItem(String title, String symbol, BorrowableInterface<T> borrowableInterface, Library<T> library) {
        super(title, symbol);
        this.borrowableInterface = borrowableInterface;
        this.library = library;
    }

    @Override
    public void onSelect() {
        String borrowableName = borrowableInterface.promptForCheckoutBorrowable();

        Borrowable borrowableToCheckout;
        try {
            borrowableToCheckout = library.borrowables().searchByName(borrowableName);
            library.checkout(borrowableToCheckout);
        } catch (BorrowableNotFoundException | BorrowableNotAvailableException e) {
            borrowableInterface.onCheckoutBorrowableUnsuccessful();
            return;
        }
        borrowableInterface.onCheckoutBorrowableSuccess();
    }
}
