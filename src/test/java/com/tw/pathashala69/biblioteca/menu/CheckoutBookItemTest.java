package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.exception.BookNotAvailable;
import com.tw.pathashala69.biblioteca.exception.BookNotFoundException;
import com.tw.pathashala69.biblioteca.models.Book;
import com.tw.pathashala69.biblioteca.models.Books;
import com.tw.pathashala69.biblioteca.models.Library;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class CheckoutBookItemTest {

    private String data;
    private Book book;
    private Books books;
    private Library library;
    private ByteArrayOutputStream outStream;
    private CheckoutBookItem checkoutBookItem;

    @BeforeEach
    void setUp() throws BookNotFoundException {
        data = "Harry Potter";
        ByteArrayInputStream inStream = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
        outStream = new ByteArrayOutputStream();
        System.setIn(inStream);
        System.setOut(new PrintStream(outStream));

        book = mock(Book.class);
        books = mock(Books.class);

        when(books.add(book)).thenReturn(true);
        when(books.searchByName(data)).thenReturn(book);

        library = mock(Library.class);
        when(library.books()).thenReturn(books);

        checkoutBookItem = new CheckoutBookItem(library);
    }

    @AfterEach
    void tearDown() {
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    public void shouldCheckoutSelectedBook() throws BookNotAvailable {
        books.add(book);

        checkoutBookItem.onSelect();

        verify(library, times(1)).checkout(book);
    }

    @Test
    public void shouldReturnTrueIfSuccessMessageIsPrintedWhenCheckoutIsSuccessful() {
        books.add(book);

        checkoutBookItem.onSelect();

        assertTrue(new String(outStream.toByteArray()).contains(Message.CHECKOUT_BOOK_SUCCESS_MESSAGE));
    }

    @Test
    public void shouldPrintUnsuccessfulMessageWhenBookIsNotFound() throws BookNotFoundException {
        when(books.searchByName(data)).thenThrow(BookNotFoundException.class);

        checkoutBookItem.onSelect();

        assertTrue(new String(outStream.toByteArray()).contains(Message.CHECKOUT_BOOK_UNSUCCESSFUL_MESSAGE));
    }

    @Test
    public void shouldPrintUnsuccessfulMessageWhenBookIsAlreadyCheckedOut() throws BookNotAvailable {
        books.add(book);
        doThrow(BookNotAvailable.class).when(library).checkout(book);

        checkoutBookItem.onSelect();

        assertTrue(new String(outStream.toByteArray()).contains(Message.CHECKOUT_BOOK_UNSUCCESSFUL_MESSAGE));
    }
}