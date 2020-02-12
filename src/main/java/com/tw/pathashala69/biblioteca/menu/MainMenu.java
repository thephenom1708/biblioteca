package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.core.exception.InvalidMenuOptionException;

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

    public void execute(String input) throws InvalidMenuOptionException {
        for (MenuItem item : menuItems) {
            if (input.equalsIgnoreCase(item.symbol())) {
                item.onSelect();
                return;
            }
        }
        throw new InvalidMenuOptionException();
    }
}
