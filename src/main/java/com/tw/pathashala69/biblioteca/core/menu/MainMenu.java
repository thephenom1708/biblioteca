package com.tw.pathashala69.biblioteca.core.menu;

import com.tw.pathashala69.biblioteca.core.exception.InvalidMenuOptionException;

import java.util.List;
import java.util.Optional;

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
        Optional<MenuItem> first = menuItems.stream().filter(menuItem -> menuItem.symbol().
                equalsIgnoreCase(input)).findFirst();
        first.ifPresent(MenuItem::onSelect);
        first.orElseThrow(InvalidMenuOptionException::new);
    }
}
