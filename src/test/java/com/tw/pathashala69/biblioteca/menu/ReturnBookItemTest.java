package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.Biblioteca;
import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.exception.BookNotFoundException;
import com.tw.pathashala69.biblioteca.exception.IllegalBookException;
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

class ReturnBookItemTest {

    private String data;
    private Book book;
    private Books books;
    private Library library;
    private Biblioteca biblioteca;
    private ReturnBookItem returnBookItem;
    private ByteArrayOutputStream outStream;

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

        biblioteca = mock(Biblioteca.class);
        returnBookItem = new ReturnBookItem(biblioteca);
    }

    @AfterEach
    void tearDown() {
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    public void shouldReturnSelectedBook() throws IllegalBookException {
        books.add(book);

        returnBookItem.onSelect();

        verify(library, times(1)).returnBook(book);
    }

    @Test
    public void shouldReturnTrueIfSuccessMessageIsPrintedWhenReturnIsSuccessful() {
        books.add(book);

        returnBookItem.onSelect();

        assertTrue(new String(outStream.toByteArray()).contains(Message.RETURN_BOOK_SUCCESS_MESSAGE));
    }

    @Test
    public void shouldPrintUnsuccessfulMessageWhenBookDoesNotBelongToLibrary() throws BookNotFoundException {
        when(books.searchByName(data)).thenThrow(BookNotFoundException.class);

        returnBookItem.onSelect();

        assertTrue(new String(outStream.toByteArray()).contains(Message.RETURN_BOOK_UNSUCCESSFUL_MESSAGE));
    }

    @Test
    public void shouldPrintUnsuccessfulMessageWhenBookWasNotCheckedOutBefore() throws IllegalBookException {
        books.add(book);
        doThrow(IllegalBookException.class).when(library).returnBook(book);

        returnBookItem.onSelect();

        assertTrue(new String(outStream.toByteArray()).contains(Message.RETURN_BOOK_UNSUCCESSFUL_MESSAGE));
    }
}