package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.action.Action;

public class BaseMenuItem implements MenuItem {
    private String name;
    private String symbol;
    private Action action;

    public BaseMenuItem(String name, String symbol, Action action) {
        this.name = name;
        this.symbol = symbol;
        this.action = action;
    }

    @Override
    public String present() {
        return name;
    }

    @Override
    public String symbol() {
        return symbol;
    }

    @Override
    public void onSelect() {
        action.perform();
    }
}
