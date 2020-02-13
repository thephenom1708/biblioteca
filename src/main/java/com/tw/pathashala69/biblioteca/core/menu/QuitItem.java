package com.tw.pathashala69.biblioteca.core.menu;

import com.tw.pathashala69.biblioteca.core.models.Borrowable;
import com.tw.pathashala69.biblioteca.core.ui.UserInterface;

public class QuitItem<T extends Borrowable> extends BaseMenuItem {

    private final UserInterface<T> userInterface;

    public QuitItem(String title, String symbol, UserInterface<T> userInterface) {
        super(title, symbol);
        this.userInterface = userInterface;
    }

    @Override
    public void onSelect() {
        userInterface.exit();
    }
}
