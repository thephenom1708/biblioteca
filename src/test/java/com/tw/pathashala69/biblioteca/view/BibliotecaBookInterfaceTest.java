package com.tw.pathashala69.biblioteca.view;

import com.tw.pathashala69.biblioteca.view.constants.Message;
import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotFoundException;
import com.tw.pathashala69.biblioteca.core.models.Book;
import com.tw.pathashala69.biblioteca.core.models.Borrowables;
import com.tw.pathashala69.biblioteca.core.models.Library;
import com.tw.pathashala69.biblioteca.view.io.BibliotecaBookInterface;
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

class BibliotecaBookInterfaceTest {

    private String data;
    private Book book, book1;
    private Borrowables borrowables;
    private Library library;
    private BibliotecaBookInterface bibliotecaBookInterface;
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
        bibliotecaBookInterface = new BibliotecaBookInterface();

        when(borrowables.searchByName(data)).thenReturn(book);
        when(borrowables.add(book)).thenReturn(true);
        when(borrowables.add(book1)).thenReturn(true);
        when(borrowables.available()).thenReturn(borrowables);
        when(library.borrowables()).thenReturn(borrowables);
        when(borrowables.available()).thenReturn(borrowables);

        bibliotecaBookInterface = new BibliotecaBookInterface();
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

        String actualWelcomeMessage = BibliotecaBookInterface.welcome();

        assertThat(actualWelcomeMessage, is(equalTo(expectedWelcomeMessage)));
    }

    @Nested
    class BookList {
        @Test
        public void shouldPrintListOfBooksToConsole() {
            Borrowables anotherBorrowables = new Borrowables(List.of(book, book1));
            Library library = mock(Library.class);
            BibliotecaBookInterface bibliotecaBookInterface = new BibliotecaBookInterface();
            when(library.borrowables()).thenReturn(anotherBorrowables);

            bibliotecaBookInterface.listBorrowables(anotherBorrowables);

            verify(book, times(1)).print(System.out);
            verify(book1, times(1)).print(System.out);
        }
    }

    @Nested
    class CheckoutBook {
        @Test
        public void shouldReturnHarryPotterAsCheckoutBookInput() {
            String input = bibliotecaBookInterface.promptForCheckoutBorrowable();

            assertThat(input, is(equalTo("Harry Potter")));
        }

        @Test
        public void shouldReturnTrueIfSuccessMessageIsPrintedWhenCheckoutIsSuccessful() {
            borrowables.add(book);
            when(book.title()).thenReturn(data);

            bibliotecaBookInterface.onCheckoutBorrowableSuccess();

            assertTrue(new String(outStream.toByteArray()).contains(Message.CHECKOUT_BOOK_SUCCESSFUL_MESSAGE));
        }

        @SuppressWarnings("unchecked")
        @Test
        public void shouldPrintUnsuccessfulMessageWhenBookIsNotFound() throws BorrowableNotFoundException {
            when(borrowables.searchByName(data)).thenThrow(BorrowableNotFoundException.class);

            bibliotecaBookInterface.onCheckoutBorrowableUnsuccessful();

            assertTrue(new String(outStream.toByteArray()).contains(Message.CHECKOUT_BOOK_UNSUCCESSFUL_MESSAGE));
        }
    }

    @Nested
    class ReturnBook {
        @Test
        public void shouldReturnHarryPotterAsReturnBookInput() {
            String input = bibliotecaBookInterface.promptForReturnBorrowable();

            assertThat(input, is(equalTo("Harry Potter")));
        }

        @Test
        public void shouldReturnTrueIfSuccessMessageIsPrintedWhenReturnIsSuccessful() {
            borrowables.add(book);
            when(book.title()).thenReturn(data);

            bibliotecaBookInterface.onReturnBorrowableSuccess();

            assertTrue(new String(outStream.toByteArray()).contains(Message.RETURN_BOOK_SUCCESSFUL_MESSAGE));
        }

        @SuppressWarnings("unchecked")
        @Test
        public void shouldPrintUnsuccessfulMessageWhenBookDoesNotBelongToLibrary() throws BorrowableNotFoundException {
            when(borrowables.searchByName(data)).thenThrow(BorrowableNotFoundException.class);

            bibliotecaBookInterface.onReturnBorrowableUnsuccessful();

            assertTrue(new String(outStream.toByteArray()).contains(Message.RETURN_BOOK_UNSUCCESSFUL_MESSAGE));
        }

    }
}
