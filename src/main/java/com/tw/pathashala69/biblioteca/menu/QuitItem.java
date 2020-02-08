package com.tw.pathashala69.biblioteca.menu;

public class QuitItem implements MenuItem {
    @Override
    public String present() {
        return "Quit";
    }

    @Override
    public String symbol() {
        return "Q";
    }

    @Override
    public void onSelect() {

    }
}
