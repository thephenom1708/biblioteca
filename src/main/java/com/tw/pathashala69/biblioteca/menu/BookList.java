package com.tw.pathashala69.biblioteca.menu;

//Job: Represents view all books menu item
public class BookList implements MenuItem {
    private final String name = "View all Books";
    private String symbol = "B";

    @Override
    public String present() {
        return name;
    }

    @Override
    public String symbol() {
        return symbol;
    }
}
