package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.Biblioteca;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class BookListItemTest {

    private Biblioteca biblioteca;
    private BookListItem bookListItem;

    @BeforeEach
    void setUp() {
        biblioteca = mock(Biblioteca.class);
        bookListItem = new BookListItem(biblioteca);
    }

    @Test
    public void shouldReturnName() {
        assertThat(bookListItem.present(), is(equalTo("List of books")));
    }

    @Test
    public void shouldReturnSymbol() {
        assertThat(bookListItem.symbol(), is(equalTo("B")));
    }

    @Test
    public void shouldPrintAvailableBooksOnSelection() {
        bookListItem.onSelect();

        verify(biblioteca, times(1)).printAvailableBooks();
    }
}
