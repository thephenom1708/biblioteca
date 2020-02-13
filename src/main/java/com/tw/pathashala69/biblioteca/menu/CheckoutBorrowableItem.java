package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.UserInterface;
import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotAvailableException;
import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotFoundException;
import com.tw.pathashala69.biblioteca.core.models.Borrowable;
import com.tw.pathashala69.biblioteca.core.models.Library;

//Job: Represents checkout book item
public class CheckoutBorrowableItem extends BaseMenuItem {
    private final UserInterface userInterface;
    private final Library library;
    private final BorrowableListItem listItem;

    public CheckoutBorrowableItem(String title, String symbol,
                                  UserInterface userInterface, Library library, BorrowableListItem listItem) {
        super(title, symbol);
        this.userInterface = userInterface;
        this.library = library;
        this.listItem = listItem;
    }

    @Override
    public void onSelect() {
        String borrowableName = userInterface.promptForCheckoutBook();

        Borrowable borrowableToCheckout;
        try {
            borrowableToCheckout = listItem.borrowables().searchByName(borrowableName);
            library.checkout(borrowableToCheckout);
        } catch (BorrowableNotFoundException | BorrowableNotAvailableException e) {
            userInterface.onCheckoutBookUnsuccessful();
            return;
        }
        userInterface.onCheckoutBookSuccess();
    }
}
