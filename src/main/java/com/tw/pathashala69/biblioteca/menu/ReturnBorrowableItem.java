package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.UserInterface;
import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.constants.Symbol;
import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotFoundException;
import com.tw.pathashala69.biblioteca.core.exception.IllegalBorrowableException;
import com.tw.pathashala69.biblioteca.core.models.Borrowable;
import com.tw.pathashala69.biblioteca.core.models.Library;

//Job: represents Return Book option menu item
public class ReturnBorrowableItem extends BaseMenuItem {
    private final UserInterface userInterface;
    private final Library library;

    public ReturnBorrowableItem(UserInterface userInterface, Library library) {
        super(Message.RETURN_BOOK_OPTION, Symbol.RB);
        this.userInterface = userInterface;
        this.library = library;
    }

    @Override
    public void onSelect() {
        String borrowableName = userInterface.returnBookInput();

        Borrowable borrowableToReturn;
        try {
            borrowableToReturn = library.books().searchByName(borrowableName);
            library.returnBorrowable(borrowableToReturn);
        } catch (BorrowableNotFoundException | IllegalBorrowableException e) {
            userInterface.onReturnBookUnsuccessful();
            return;
        }
        userInterface.onReturnBookSuccess();
    }
}
