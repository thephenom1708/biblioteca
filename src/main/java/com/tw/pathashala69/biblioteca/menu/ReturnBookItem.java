package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.action.Action;
import com.tw.pathashala69.biblioteca.action.ReturnBookAction;
import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.constants.Symbol;

public class ReturnBookItem extends BaseMenuItem {
    public ReturnBookItem(ReturnBookAction returnBookAction) {
        super(Message.RETURN_BOOK_OPTION, Symbol.RB, returnBookAction);
    }
}
