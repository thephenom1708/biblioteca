package com.tw.pathashala69.biblioteca.core.menu;

import com.tw.pathashala69.biblioteca.core.ui.AuthInterface;

public class LogoutItem extends BaseMenuItem {
    private final AuthInterface authInterface;

    public LogoutItem(String title, String symbol, AuthInterface authInterface) {
        super(title, symbol);
        this.authInterface = authInterface;
    }

    @Override
    public void onSelect() {

    }
}
