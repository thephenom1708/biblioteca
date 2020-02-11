package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.Biblioteca;
import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.constants.Symbol;

public class QuitItem extends BaseMenuItem {

    private final Biblioteca biblioteca;

    public QuitItem(Biblioteca biblioteca) {
        super(Message.QUIT_OPTION, Symbol.Q);
        this.biblioteca = biblioteca;
    }

    @Override
    public void onSelect() {
        biblioteca.exit();
    }
}
