package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.UserInterface;
import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.constants.Symbol;
import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotAvailableException;
import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotFoundException;
import com.tw.pathashala69.biblioteca.core.models.Book;
import com.tw.pathashala69.biblioteca.core.models.Borrowable;
import com.tw.pathashala69.biblioteca.core.models.Library;

//Job: Represents checkout book item
public class CheckoutBorrowableItem extends BaseMenuItem {
    private final UserInterface userInterface;
    private final Library library;

    public CheckoutBorrowableItem(UserInterface userInterface, Library library) {
        super(Message.CHECKOUT_BOOK_OPTION, Symbol.CB);
        this.userInterface = userInterface;
        this.library = library;
    }

    @Override
    public void onSelect() {
        String borrowableName = userInterface.checkoutBookInput();

        Borrowable borrowableToCheckout;
        try {
            borrowableToCheckout = library.books().searchByName(borrowableName);
            library.checkout(borrowableToCheckout);
        } catch (BorrowableNotFoundException | BorrowableNotAvailableException e) {
            userInterface.onCheckoutBookUnsuccessful();
            return;
        }
        userInterface.onCheckoutBookSuccess();
    }
}
