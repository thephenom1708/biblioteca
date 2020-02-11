package com.tw.pathashala69.biblioteca.menu;

public class BaseMenuItem implements MenuItem {
    private String title;
    private String symbol;

    public BaseMenuItem(String title, String symbol) {
        this.title = title;
        this.symbol = symbol;
    }

    @Override
    public String present() {
        return title;
    }

    @Override
    public String symbol() {
        return symbol;
    }

    @Override
    public void onSelect() {}
}
