package com.tw.pathashala69.biblioteca.core.ui;

import com.tw.pathashala69.biblioteca.core.auth.User;
import com.tw.pathashala69.biblioteca.core.models.Borrowable;
import com.tw.pathashala69.biblioteca.core.models.Borrowables;

import java.util.HashMap;

public interface BorrowableInterface<T extends Borrowable> extends UserInterface {
    void listBorrowables(Borrowables<T> borrowables);

    void listCheckedOutBorrowables(HashMap<Borrowable, User> borrowableUserHashMap);

    String promptForCheckoutBorrowable();

    String promptForReturnBorrowable();

    void onCheckoutBorrowableUnsuccessful();

    void onCheckoutBorrowableSuccess();

    void onReturnBorrowableUnsuccessful();

    void onReturnBorrowableSuccess();
}
