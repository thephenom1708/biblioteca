package com.tw.pathashala69.biblioteca.action;

import com.tw.pathashala69.biblioteca.models.Book;
import com.tw.pathashala69.biblioteca.models.Books;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class BookListViewActionTest {

    Book book = mock(Book.class);
    Books books;
    BookListViewAction bookListViewAction;

    @BeforeEach
    void setUp() {
        List<Book> bookList = List.of(book, book, book);
        books = new Books(bookList);
        bookListViewAction = new BookListViewAction(books);
    }

    @Test
    public void shouldPrintAllBooksWhenPerformed() {
        bookListViewAction.perform();

        verify(book, times(3)).print(System.out);
    }
}
