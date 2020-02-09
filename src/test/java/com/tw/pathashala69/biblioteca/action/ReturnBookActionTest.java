package com.tw.pathashala69.biblioteca.action;

import com.tw.pathashala69.biblioteca.constants.Message;
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

class ReturnBookActionTest {

    String data;
    private Book book;
    private Library library;
    private ReturnBookAction returnBookAction;
    private ByteArrayOutputStream outStream;

    @BeforeEach
    void setUp() throws BookNotFoundException {
        data = "Harry Potter";
        ByteArrayInputStream inStream = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
        outStream = new ByteArrayOutputStream();
        System.setIn(inStream);
        System.setOut(new PrintStream(outStream));

        book = mock(Book.class);
        Books books = mock(Books.class);

        when(books.add(book)).thenReturn(true);
        when(books.searchByName(data)).thenReturn(book);

        books.add(book);
        library = mock(Library.class);
        when(library.books()).thenReturn(books);

        returnBookAction = new ReturnBookAction(library);
    }

    @AfterEach
    void tearDown() {
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    public void shouldReturnSelectedBook() {
        returnBookAction.perform();

        verify(library, times(1)).returnBook(book);
    }

    @Test
    public void shouldReturnTrueIfSuccessMessageIsPrintedWhenReturnIsSuccessful() {
        returnBookAction.perform();

        assertTrue(new String(outStream.toByteArray()).contains(Message.RETURN_BOOK_SUCCESS_MESSAGE));
    }
}
