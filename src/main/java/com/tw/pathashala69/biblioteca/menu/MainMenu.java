package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.exception.InvalidMenuOptionException;

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
        int counter = 1;
        for (MenuItem item : menuItems) {
            stream.println(counter + ". " + item.present() + " [" + item.symbol() + "]");
            counter++;
        }
        stream.println(Message.ENTER_COMMAND_MESSAGE);
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
