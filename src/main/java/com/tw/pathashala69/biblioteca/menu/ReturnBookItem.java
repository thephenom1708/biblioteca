package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.action.ReturnBookAction;
import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.constants.Symbol;

//Job: represents Return Book option menu item
public class ReturnBookItem extends BaseMenuItem {
    public ReturnBookItem(ReturnBookAction returnBookAction) {
        super(Message.RETURN_BOOK_OPTION, Symbol.RB, returnBookAction);
    }
}
