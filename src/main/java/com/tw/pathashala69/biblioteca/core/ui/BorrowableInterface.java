package com.tw.pathashala69.biblioteca.core.ui;

import com.tw.pathashala69.biblioteca.core.models.Borrowable;
import com.tw.pathashala69.biblioteca.core.models.Borrowables;

public interface BorrowableInterface<T extends Borrowable> extends UserInterface {
    void listBorrowables(Borrowables<T> borrowables);

    String promptForCheckoutBorrowable();

    String promptForReturnBorrowable();

    void onCheckoutBorrowableUnsuccessful();

    void onCheckoutBorrowableSuccess();

    void onReturnBorrowableUnsuccessful();

    void onReturnBorrowableSuccess();
}
