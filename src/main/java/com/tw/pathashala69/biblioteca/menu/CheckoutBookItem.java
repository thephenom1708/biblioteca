package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.action.CheckoutBookAction;
import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.constants.Symbol;

//Job: Represents checkout book item
public class CheckoutBookItem extends BaseMenuItem {
    public CheckoutBookItem(CheckoutBookAction checkoutBookAction) {
        super(Message.CHECKOUT_BOOK_OPTION, Symbol.CB, checkoutBookAction);
    }
}
