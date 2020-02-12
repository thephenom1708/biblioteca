package com.tw.pathashala69.biblioteca;

import com.tw.pathashala69.biblioteca.core.models.Books;

public interface UserInterface {
    String checkoutBookInput();

    String returnBookInput();

    void printBooks(Books books);

    void onCheckoutBookUnsuccessful();

    void onCheckoutBookSuccess();

    void onReturnBookUnsuccessful();

    void onReturnBookSuccess();

    void exit();
}
