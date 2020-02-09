package com.tw.pathashala69.biblioteca.action;

import com.tw.pathashala69.biblioteca.models.Book;
import com.tw.pathashala69.biblioteca.models.Books;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class BookListViewActionTest {

    private Book book = mock(Book.class);
    private Books books;
    private BookListViewAction bookListViewAction;

    @BeforeEach
    void setUp() {
        books = new Books(List.of(book));
        bookListViewAction = new BookListViewAction(books);
    }

    @Test
    public void shouldPrintAllBooksWhenPerformed() {
        bookListViewAction.perform();

        verify(book, times(1)).print(System.out);
    }
}
