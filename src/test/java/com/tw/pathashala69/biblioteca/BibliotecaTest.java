package com.tw.pathashala69.biblioteca;

import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotFoundException;
import com.tw.pathashala69.biblioteca.core.models.Book;
import com.tw.pathashala69.biblioteca.core.models.Borrowables;
import com.tw.pathashala69.biblioteca.core.models.Library;
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
    private Borrowables borrowables;
    private Library library;
    private Biblioteca biblioteca;
    private ByteArrayOutputStream outStream;
    private PrintStream oldOutStream;
    private InputStream oldInputStream;

    @BeforeEach
    void setUp() throws BorrowableNotFoundException {
        data = "Harry Potter";
        ByteArrayInputStream inStream = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
        outStream = new ByteArrayOutputStream();
        oldInputStream = System.in;
        System.setIn(inStream);
        oldOutStream = new PrintStream(System.out);
        System.setOut(new PrintStream(outStream));

        book = mock(Book.class);
        book1 = mock(Book.class);

        borrowables = mock(Borrowables.class);

        library = mock(Library.class);
        biblioteca = new Biblioteca(System.out);

        when(borrowables.searchByName(data)).thenReturn(book);
        when(borrowables.add(book)).thenReturn(true);
        when(borrowables.add(book1)).thenReturn(true);
        when(borrowables.available()).thenReturn(borrowables);
        when(library.books()).thenReturn(borrowables);
        when(borrowables.available()).thenReturn(borrowables);

        biblioteca = new Biblioteca(System.out);
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

    @Nested
    class BookList {
        @Test
        public void shouldPrintListOfBooksToConsole() {
            Borrowables anotherBorrowables = new Borrowables(List.of(book, book1));
            Library library = mock(Library.class);
            Biblioteca biblioteca = new Biblioteca(System.out);
            when(library.books()).thenReturn(anotherBorrowables);

            biblioteca.listBorrowables(anotherBorrowables);

            verify(book, times(1)).print(System.out);
            verify(book1, times(1)).print(System.out);
        }
    }

    @Nested
    class CheckoutBook {
        @Test
        public void shouldReturnHarryPotterAsCheckoutBookInput() {
            String input = biblioteca.promptForCheckoutBook();

            assertThat(input, is(equalTo("Harry Potter")));
        }

        @Test
        public void shouldReturnTrueIfSuccessMessageIsPrintedWhenCheckoutIsSuccessful() {
            borrowables.add(book);
            when(book.title()).thenReturn(data);

            biblioteca.onCheckoutBookSuccess();

            assertTrue(new String(outStream.toByteArray()).contains(Message.CHECKOUT_BOOK_SUCCESS_MESSAGE));
        }

        @SuppressWarnings("unchecked")
        @Test
        public void shouldPrintUnsuccessfulMessageWhenBookIsNotFound() throws BorrowableNotFoundException {
            when(borrowables.searchByName(data)).thenThrow(BorrowableNotFoundException.class);

            biblioteca.onCheckoutBookUnsuccessful();

            assertTrue(new String(outStream.toByteArray()).contains(Message.CHECKOUT_BOOK_UNSUCCESSFUL_MESSAGE));
        }
    }

    @Nested
    class ReturnBook {
        @Test
        public void shouldReturnHarryPotterAsReturnBookInput() {
            String input = biblioteca.promptForReturnBook();

            assertThat(input, is(equalTo("Harry Potter")));
        }

        @Test
        public void shouldReturnTrueIfSuccessMessageIsPrintedWhenReturnIsSuccessful() {
            borrowables.add(book);
            when(book.title()).thenReturn(data);

            biblioteca.onReturnBookSuccess();

            assertTrue(new String(outStream.toByteArray()).contains(Message.RETURN_BOOK_SUCCESS_MESSAGE));
        }

        @SuppressWarnings("unchecked")
        @Test
        public void shouldPrintUnsuccessfulMessageWhenBookDoesNotBelongToLibrary() throws BorrowableNotFoundException {
            when(borrowables.searchByName(data)).thenThrow(BorrowableNotFoundException.class);

            biblioteca.onReturnBookUnsuccessful();

            assertTrue(new String(outStream.toByteArray()).contains(Message.RETURN_BOOK_UNSUCCESSFUL_MESSAGE));
        }

    }
}
