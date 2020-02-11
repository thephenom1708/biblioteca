package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.Biblioteca;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CheckoutBookItemTest {

    private Biblioteca biblioteca;
    private CheckoutBookItem checkoutBookItem;

    @BeforeEach
    void setUp() {
        biblioteca = mock(Biblioteca.class);
        checkoutBookItem = new CheckoutBookItem(biblioteca);
    }

    @Test
    public void shouldCheckoutSelectedBookOnSelection() {
        checkoutBookItem.onSelect();

        verify(biblioteca, times(1)).checkoutBook();
    }
}
