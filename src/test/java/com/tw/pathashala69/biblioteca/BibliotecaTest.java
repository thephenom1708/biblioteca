package com.tw.pathashala69.biblioteca;

import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.exception.BookNotAvailable;
import com.tw.pathashala69.biblioteca.exception.BookNotFoundException;
import com.tw.pathashala69.biblioteca.exception.IllegalBookException;
import com.tw.pathashala69.biblioteca.models.Book;
import com.tw.pathashala69.biblioteca.models.Books;
import com.tw.pathashala69.biblioteca.models.Library;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class BibliotecaTest {

    private String data;
    private Book book, book1;
    private Books books;
    private Library library;
    private Biblioteca biblioteca;
    private ByteArrayOutputStream outStream;
    private PrintStream oldOutStream;
    private InputStream oldInputStream;

    @BeforeEach
    void setUp() throws BookNotFoundException {
        data = "Harry Potter";
        ByteArrayInputStream inStream = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
        outStream = new ByteArrayOutputStream();
        oldInputStream = System.in;
        System.setIn(inStream);
        oldOutStream = new PrintStream(System.out);
        System.setOut(new PrintStream(outStream));

        book = mock(Book.class);
        book1 = mock(Book.class);

        books = mock(Books.class);

        library = mock(Library.class);
        biblioteca = new Biblioteca(library, System.out);

        when(books.searchByName(data)).thenReturn(book);
        when(books.add(book)).thenReturn(true);
        when(books.add(book1)).thenReturn(true);
        when(books.available()).thenReturn(books);
        when(library.books()).thenReturn(books);
        when(books.available()).thenReturn(books);

        biblioteca = new Biblioteca(library, System.out);
    }

    @AfterEach
    void tearDown() {
        System.setIn(oldInputStream);
        System.setOut(oldOutStream);
    }

    @Test
    public void shouldReturnWelcomeMessageWhenCustomerStartsApplication() {
        String expectedWelcomeMessage = "Welcome to Biblioteca. Your one-stop-shop for great book " +
                "titles in Bangalore!";

        String actualWelcomeMessage = Biblioteca.welcome();

        assertThat(actualWelcomeMessage, is(equalTo(expectedWelcomeMessage)));
    }

    @Test
    public void shouldReturnLibrary() {
        Library actualLibrary = biblioteca.library();

        assertThat(actualLibrary, is(equalTo(library)));
    }

    @Nested
    class BookList {
        @Test
        public void shouldPrintListOfBooksToConsole() {
            Books anotherBooks = new Books(List.of(book, book1));
            Library library = mock(Library.class);
            Biblioteca  biblioteca = new Biblioteca(library, System.out);
            when(library.books()).thenReturn(anotherBooks);

            biblioteca.printAvailableBooks();

            verify(book, times(1)).print(System.out);
            verify(book1, times(1)).print(System.out);
        }
    }

    @Nested
    class CheckoutBook {
        @Test
        public void shouldCheckoutSelectedBook() throws BookNotAvailable {
            books.add(book);
            when(book.title()).thenReturn(data);

            biblioteca.checkoutBook();

            verify(library, times(1)).checkout(book);
        }

        @Test
        public void shouldReturnTrueIfSuccessMessageIsPrintedWhenCheckoutIsSuccessful() {
            books.add(book);
            when(book.title()).thenReturn(data);

            biblioteca.checkoutBook();

            assertTrue(new String(outStream.toByteArray()).contains(Message.CHECKOUT_BOOK_SUCCESS_MESSAGE));
        }

        @Test
        public void shouldPrintUnsuccessfulMessageWhenBookIsNotFound() throws BookNotFoundException {
            when(books.searchByName(data)).thenThrow(BookNotFoundException.class);

            biblioteca.checkoutBook();

            assertTrue(new String(outStream.toByteArray()).contains(Message.CHECKOUT_BOOK_UNSUCCESSFUL_MESSAGE));
        }

        @Test
        public void shouldPrintUnsuccessfulMessageWhenBookIsAlreadyCheckedOut() throws BookNotAvailable {
            books.add(book);
            doThrow(BookNotAvailable.class).when(library).checkout(book);

            biblioteca.checkoutBook();

            assertTrue(new String(outStream.toByteArray()).contains(Message.CHECKOUT_BOOK_UNSUCCESSFUL_MESSAGE));
        }
    }

    @Nested
    class ReturnBook {
        @Test
        public void shouldReturnSelectedBook() throws IllegalBookException {
            books.add(book);
            when(book.title()).thenReturn(data);

            biblioteca.returnBook();

            verify(library, times(1)).returnBook(book);
        }

        @Test
        public void shouldReturnTrueIfSuccessMessageIsPrintedWhenReturnIsSuccessful() {
            books.add(book);
            when(book.title()).thenReturn(data);

            biblioteca.returnBook();

            assertTrue(new String(outStream.toByteArray()).contains(Message.RETURN_BOOK_SUCCESS_MESSAGE));
        }

        @Test
        public void shouldPrintUnsuccessfulMessageWhenBookDoesNotBelongToLibrary() throws BookNotFoundException {
            when(books.searchByName(data)).thenThrow(BookNotFoundException.class);

            biblioteca.returnBook();

            assertTrue(new String(outStream.toByteArray()).contains(Message.RETURN_BOOK_UNSUCCESSFUL_MESSAGE));
        }

        @Test
        public void shouldPrintUnsuccessfulMessageWhenBookWasNotCheckedOutBefore() throws IllegalBookException {
            books.add(book);
            doThrow(IllegalBookException.class).when(library).returnBook(book);

            biblioteca.returnBook();

            assertTrue(new String(outStream.toByteArray()).contains(Message.RETURN_BOOK_UNSUCCESSFUL_MESSAGE));
        }
    }
}
