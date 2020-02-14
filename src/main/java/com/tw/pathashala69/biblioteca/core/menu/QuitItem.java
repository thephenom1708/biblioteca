package com.tw.pathashala69.biblioteca.core.menu;

import com.tw.pathashala69.biblioteca.core.models.Borrowable;
import com.tw.pathashala69.biblioteca.core.ui.BorrowableInterface;

public class QuitItem<T extends Borrowable> extends BaseMenuItem {

    private final BorrowableInterface<T> borrowableInterface;

    public QuitItem(String title, String symbol, BorrowableInterface<T> borrowableInterface) {
        super(title, symbol);
        this.borrowableInterface = borrowableInterface;
    }

    @Override
    public void onSelect() {
        borrowableInterface.exit();
    }
}
