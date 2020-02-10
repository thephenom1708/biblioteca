package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.constants.Symbol;
import com.tw.pathashala69.biblioteca.models.Library;

public class QuitItem extends BaseMenuItem {

    private final Library library;

    public QuitItem(Library library) {
        super(Message.QUIT_OPTION, Symbol.Q);
        this.library = library;
    }

    @Override
    public void onSelect() {

    }
}
