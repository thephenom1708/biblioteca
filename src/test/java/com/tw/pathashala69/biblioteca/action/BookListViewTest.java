package com.tw.pathashala69.biblioteca.action;

import com.tw.pathashala69.biblioteca.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class BookListViewTest {

    Book book = mock(Book.class);
    List<Book> books;
    BookListView bookListView;

    @BeforeEach
    void setUp() {
        books = List.of(book, book, book);
        bookListView = new BookListView(books);
    }

    @Test
    public void shouldPrintAllBooksWhenPerformed() {
        bookListView.perform();

        verify(book, times(3)).print(System.out);
    }
}
