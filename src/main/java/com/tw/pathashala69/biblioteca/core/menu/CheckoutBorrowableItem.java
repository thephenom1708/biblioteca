package com.tw.pathashala69.biblioteca.core.menu;

import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotAvailableException;
import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotFoundException;
import com.tw.pathashala69.biblioteca.core.models.Borrowable;
import com.tw.pathashala69.biblioteca.core.models.Library;
import com.tw.pathashala69.biblioteca.core.ui.UserInterface;

//Job: Represents checkout book item
public class CheckoutBorrowableItem<T extends Borrowable> extends BaseMenuItem {
    private final UserInterface<T> userInterface;
    private final Library<T> library;

    public CheckoutBorrowableItem(String title, String symbol, UserInterface<T> userInterface, Library<T> library) {
        super(title, symbol);
        this.userInterface = userInterface;
        this.library = library;
    }

    @Override
    public void onSelect() {
        String borrowableName = userInterface.promptForCheckoutBorrowable();

        Borrowable borrowableToCheckout;
        try {
            borrowableToCheckout = library.borrowables().searchByName(borrowableName);
            library.checkout(borrowableToCheckout);
        } catch (BorrowableNotFoundException | BorrowableNotAvailableException e) {
            userInterface.onCheckoutBorrowableUnsuccessful();
            return;
        }
        userInterface.onCheckoutBorrowableSuccess();
    }
}
