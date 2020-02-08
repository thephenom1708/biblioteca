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
        int counter = 1;
        for (MenuItem item : menuItems) {
            stream.println(counter + ". " + item.present() + " [" + item.symbol() + "]");
            counter++;
        }
        stream.println();
    }

    public void execute() {
        menuItems.get(0).onSelect();
    }
}
