package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.exception.BookNotAvailable;
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

    private Library library;
    private Book book;
    private Books books;
    private BookListItem bookListItem;

    @BeforeEach
    void setUp() {
        book = mock(Book.class);
        books = new Books(List.of(book));
        library = mock(Library.class);

        when(library.books()).thenReturn(books);

        bookListItem = new BookListItem(library);
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
    public void shouldPrintAvailableBooksOnSelected() throws BookNotAvailable {
        Book newBook = mock(Book.class);
        books.add(newBook);
        books.checkout(newBook);

        bookListItem.onSelect();

        verify(book, times(1)).print(System.out);
        verify(newBook, times(0)).print(System.out);
    }
}
