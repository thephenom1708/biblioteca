package com.tw.pathashala69.biblioteca.menu;

public class BaseMenuItem implements MenuItem {
    private String name;
    private String symbol;

    public BaseMenuItem(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
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
    public void onSelect() {}
}
