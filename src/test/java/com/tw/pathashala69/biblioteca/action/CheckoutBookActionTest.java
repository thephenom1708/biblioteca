package com.tw.pathashala69.biblioteca.action;

import com.tw.pathashala69.biblioteca.exception.BookNotFoundException;
import com.tw.pathashala69.biblioteca.models.Book;
import com.tw.pathashala69.biblioteca.models.Books;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.*;

class CheckoutBookActionTest {

    private Book book;
    private Books books;
    private CheckoutBookAction checkoutBookAction;
    private ByteArrayInputStream inStream;

    @BeforeEach
    void setUp() throws BookNotFoundException {
        String data = "Harry Potter";
        inStream = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
        book = mock(Book.class);
        books = mock(Books.class);

        when(books.add(book)).thenReturn(true);
        when(books.searchByName(data)).thenReturn(book);

        checkoutBookAction = new CheckoutBookAction(books);
    }

    @Test
    public void shouldCheckoutSelectedBook() {
        System.setIn(inStream);
        books.add(book);

        checkoutBookAction.perform();
        System.setIn(System.in);

        verify(books, times(1)).checkout(book);
    }
}
