package com.tw.pathashala69.biblioteca.core.menu;

import com.tw.pathashala69.biblioteca.core.ui.AppInterface;

public class QuitItem extends BaseMenuItem {

    private final AppInterface appInterface;

    public QuitItem(String title, String symbol, AppInterface appInterface) {
        super(title, symbol);
        this.appInterface = appInterface;
    }

    @Override
    public void onSelect() {
        appInterface.exit();
    }
}
