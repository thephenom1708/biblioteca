package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.action.QuitAction;
import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.constants.Symbol;

public class QuitItem extends BaseMenuItem {
    public QuitItem(QuitAction quitAction) {
        super(Message.QUIT_OPTION, Symbol.Q, quitAction);
    }
}
