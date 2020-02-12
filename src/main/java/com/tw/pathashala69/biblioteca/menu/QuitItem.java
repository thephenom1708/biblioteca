package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.UserInterface;
import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.constants.Symbol;

public class QuitItem extends BaseMenuItem {

    private final UserInterface userInterface;

    public QuitItem(UserInterface userInterface) {
        super(Message.QUIT_OPTION, Symbol.Q);
        this.userInterface = userInterface;
    }

    @Override
    public void onSelect() {
        userInterface.exit();
    }
}
