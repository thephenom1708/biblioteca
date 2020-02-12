package com.tw.pathashala69.biblioteca;

import com.tw.pathashala69.biblioteca.core.models.Borrowables;

public interface UserInterface {
    String checkoutBookInput();

    String returnBookInput();

    void printBorrowable(Borrowables borrowables);

    void onCheckoutBookUnsuccessful();

    void onCheckoutBookSuccess();

    void onReturnBookUnsuccessful();

    void onReturnBookSuccess();

    void exit();
}
