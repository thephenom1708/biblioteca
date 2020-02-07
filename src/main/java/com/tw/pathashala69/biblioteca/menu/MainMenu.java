package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.Message;

import java.io.PrintStream;
import java.util.List;

// Job: Stores the list of all Menu Items
public class MainMenu {
    private final List<MenuItem> menuItems;

    public MainMenu(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public List<MenuItem> menuItems() {
        return menuItems;
    }

    public void printMenu(PrintStream stream) {
        stream.println("\n" + Message.MAIN_MENU_MESSAGE);
        menuItems.forEach(item -> {
            stream.println("[" + item.symbol() + "]. " + item.present());
        });
        stream.println();
    }
}
