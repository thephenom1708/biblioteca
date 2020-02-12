package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.Biblioteca;
import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.core.exception.BookNotFoundException;
import com.tw.pathashala69.biblioteca.core.exception.IllegalBookException;
import com.tw.pathashala69.biblioteca.core.models.Book;
import com.tw.pathashala69.biblioteca.core.models.Books;
import com.tw.pathashala69.biblioteca.core.models.Library;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ReturnBookItemTest {

    private Book book;
    private Books books;
    private Library library;
    private Biblioteca biblioteca;
    private ReturnBookItem returnBookItem;

    @BeforeEach
    void setUp() {
        book = mock(Book.class);
        books = mock(Books.class);
        library = mock(Library.class);
        biblioteca = mock(Biblioteca.class);

        when(books.add(book)).thenReturn(true);
        when(biblioteca.returnBookInput()).thenReturn("Harry Potter");
        when(biblioteca.library()).thenReturn(library);
        when(library.books()).thenReturn(books);

        returnBookItem = new ReturnBookItem(biblioteca);
    }

    @AfterEach
    void tearDown() {
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    public void shouldReturnSelectedBook() throws IllegalBookException, BookNotFoundException {
        books.add(book);
        when(books.searchByName("Harry Potter")).thenReturn(book);

        returnBookItem.onSelect();

        verify(library, times(1)).returnBook(book);
    }

    @Test
    public void shouldPerformOnReturnSuccessfulWhenReturnIsSuccessful() throws BookNotFoundException {
        books.add(book);
        when(books.searchByName("Harry Potter")).thenReturn(book);

        returnBookItem.onSelect();

        verify(biblioteca, times(1)).onReturnBookSuccess();
    }

    @Test
    public void shouldPerformOnReturnUnsuccessfulWhenBookDoesNotBelongToLibrary() throws BookNotFoundException {
        when(books.searchByName("Harry Potter")).thenThrow(BookNotFoundException.class);

        returnBookItem.onSelect();

        verify(biblioteca, times(1)).onReturnBookUnsuccessful();
    }

    @Test
    public void shouldPerformOnReturnUnsuccessfulWhenBookWasNotCheckedOutBefore() throws IllegalBookException, BookNotFoundException {
        books.add(book);
        when(books.searchByName("Harry Potter")).thenReturn(book);
        doThrow(IllegalBookException.class).when(library).returnBook(book);

        returnBookItem.onSelect();

        verify(biblioteca, times(1)).onReturnBookUnsuccessful();
    }
}