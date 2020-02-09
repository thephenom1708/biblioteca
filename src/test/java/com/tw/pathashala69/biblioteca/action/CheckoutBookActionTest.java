package com.tw.pathashala69.biblioteca.action;

import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.exception.BookNotFoundException;
import com.tw.pathashala69.biblioteca.models.Book;
import com.tw.pathashala69.biblioteca.models.Books;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class CheckoutBookActionTest {

    private Book book;
    private Books books;
    private CheckoutBookAction checkoutBookAction;
    private ByteArrayInputStream inStream;
    private ByteArrayOutputStream outStream;

    @BeforeEach
    void setUp() throws BookNotFoundException {
        String data = "Harry Potter";
        inStream = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
        outStream = new ByteArrayOutputStream();
        System.setIn(inStream);
        System.setOut(new PrintStream(outStream));

        book = mock(Book.class);
        books = mock(Books.class);

        when(books.add(book)).thenReturn(true);
        when(books.searchByName(data)).thenReturn(book);

        checkoutBookAction = new CheckoutBookAction(books);
    }

    @Test
    public void shouldCheckoutSelectedBook() {
        books.add(book);

        checkoutBookAction.perform();

        verify(books, times(1)).checkout(book);
    }

    @Test
    public void shouldReturnTrueIfSuccessMessageIsPrintedWhenCheckoutIsSuccessful() {
        books.add(book);

        checkoutBookAction.perform();

        assertTrue(new String(outStream.toByteArray()).contains(Message.CHECKOUT_BOOK_SUCCESS_MESSAGE));
    }

    @AfterEach
    public void tearDown() {
        System.setIn(System.in);
        System.setOut(System.out);
    }
}
