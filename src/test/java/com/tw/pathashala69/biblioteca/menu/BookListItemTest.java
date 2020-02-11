package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.Biblioteca;
import com.tw.pathashala69.biblioteca.models.Book;
import com.tw.pathashala69.biblioteca.models.Books;
import com.tw.pathashala69.biblioteca.models.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class BookListItemTest {

    private Biblioteca biblioteca;
    private BookListItem bookListItem;

    @BeforeEach
    void setUp() {
        Book book = mock(Book.class);
        Books books = new Books(List.of(book));
        Library library = mock(Library.class);
        biblioteca = mock(Biblioteca.class);

        when(library.books()).thenReturn(books);

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
