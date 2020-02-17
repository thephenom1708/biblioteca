package com.tw.pathashala69.biblioteca.core.menu;

import com.tw.pathashala69.biblioteca.core.models.Borrowable;
import com.tw.pathashala69.biblioteca.core.models.Library;
import com.tw.pathashala69.biblioteca.core.ui.BorrowableInterface;

//Job: Represents view all books menu item
public class BorrowableListItem<T extends Borrowable> extends BaseMenuItem {
    protected final BorrowableInterface<T> borrowableInterface;
    protected final Library<T> library;

    public BorrowableListItem(String title, String symbol, BorrowableInterface<T> borrowableInterface, Library<T> library) {
        super(title, symbol);
        this.borrowableInterface = borrowableInterface;
        this.library = library;
    }

    @Override
    public void onSelect() {
        borrowableInterface.listBorrowables(library.borrowables().available());
    }
}
