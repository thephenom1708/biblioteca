package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.Biblioteca;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ReturnBookItemTest {
    private Biblioteca biblioteca;
    private ReturnBookItem returnBookItem;

    @BeforeEach
    void setUp() {
        biblioteca = mock(Biblioteca.class);
        returnBookItem = new ReturnBookItem(biblioteca);
    }

    @Test
    public void shouldReturnSelectedBookOnSelection() {
        returnBookItem.onSelect();

        verify(biblioteca, times(1)).returnBook();
    }
}