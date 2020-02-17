package com.tw.pathashala69.biblioteca.core.menu;

import com.tw.pathashala69.biblioteca.core.models.Borrowable;
import com.tw.pathashala69.biblioteca.core.models.Library;
import com.tw.pathashala69.biblioteca.core.ui.BorrowableInterface;

public class CheckedOutBorrowablesItem<T extends Borrowable> extends BorrowableListItem<T> {

    public CheckedOutBorrowablesItem(String title, String symbol, BorrowableInterface<T> borrowableInterface, Library<T> library) {
        super(title, symbol, borrowableInterface, library);
    }

    @Override
    public void onSelect() {
        borrowableInterface.listCheckedOutBorrowables(library.borrowables().checkedOut());
    }
}
