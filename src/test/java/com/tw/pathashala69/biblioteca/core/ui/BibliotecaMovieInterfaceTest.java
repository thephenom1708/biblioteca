package com.tw.pathashala69.biblioteca.core.ui;

import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotFoundException;
import com.tw.pathashala69.biblioteca.core.models.Borrowable;
import com.tw.pathashala69.biblioteca.core.models.Borrowables;
import com.tw.pathashala69.biblioteca.core.models.Library;
import com.tw.pathashala69.biblioteca.core.models.Movie;
import com.tw.pathashala69.biblioteca.view.constants.Message;
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


class BibliotecaMovieInterfaceTest {
    private String data;
    private Movie movie, movie1;
    private Borrowables<Borrowable> borrowables;
    private BibliotecaMovieInterface bibliotecaMovieInterface;
    private ByteArrayOutputStream outStream;
    private PrintStream oldOutStream;
    private InputStream oldInputStream;

    @BeforeEach
    void setUp() throws BorrowableNotFoundException {
        data = "Movie";
        ByteArrayInputStream inStream = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
        outStream = new ByteArrayOutputStream();
        oldInputStream = System.in;
        System.setIn(inStream);
        oldOutStream = new PrintStream(System.out);
        System.setOut(new PrintStream(outStream));

        movie = mock(Movie.class);
        movie1 = mock(Movie.class);

        borrowables = mock(Borrowables.class);

        Library<Borrowable> library = mock(Library.class);

        when(borrowables.searchByName(data)).thenReturn(movie);
        when(borrowables.add(movie)).thenReturn(true);
        when(borrowables.add(movie1)).thenReturn(true);
        when(borrowables.available()).thenReturn(borrowables);
        when(library.borrowables()).thenReturn(borrowables);
        when(borrowables.available()).thenReturn(borrowables);

        bibliotecaMovieInterface = new BibliotecaMovieInterface();
    }

    @AfterEach
    void tearDown() {
        System.setIn(oldInputStream);
        System.setOut(oldOutStream);
    }


    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Nested
    class MovieList {
        @Test
        public void shouldPrintListOfBooksToConsole() {
            Borrowables anotherBorrowables = new Borrowables(List.of(movie, movie1));
            Library library = mock(Library.class);
            BibliotecaBookInterface bibliotecaBookInterface = new BibliotecaBookInterface();
            when(library.borrowables()).thenReturn(anotherBorrowables);

            bibliotecaBookInterface.listBorrowables(anotherBorrowables);

            verify(movie, times(1)).print(System.out);
            verify(movie1, times(1)).print(System.out);
        }
    }

    @SuppressWarnings({ "unchecked" })
    @Nested
    class CheckoutMovie {
        @Test
        public void shouldReturnHarryPotterAsCheckoutBookInput() {
            String input = bibliotecaMovieInterface.promptForCheckoutBorrowable();

            assertThat(input, is(equalTo("Movie")));
        }

        @Test
        public void shouldReturnTrueIfSuccessMessageIsPrintedWhenCheckoutIsSuccessful() {
            borrowables.add(movie);
            when(movie.title()).thenReturn(data);

            bibliotecaMovieInterface.onCheckoutBorrowableSuccess();

            assertTrue(new String(outStream.toByteArray()).contains(Message.CHECKOUT_MOVIE_SUCCESSFUL_MESSAGE));
        }

        @Test
        public void shouldPrintUnsuccessfulMessageWhenBookIsNotFound() throws BorrowableNotFoundException {
            when(borrowables.searchByName(data)).thenThrow(BorrowableNotFoundException.class);

            bibliotecaMovieInterface.onCheckoutBorrowableUnsuccessful();

            assertTrue(new String(outStream.toByteArray()).contains(Message.CHECKOUT_MOVIE_UNSUCCESSFUL_MESSAGE));
        }
    }

    @SuppressWarnings({ "unchecked" })
    @Nested
    class ReturnMovie {
        @Test
        public void shouldReturnHarryPotterAsReturnBookInput() {
            String input = bibliotecaMovieInterface.promptForReturnBorrowable();

            assertThat(input, is(equalTo("Movie")));
        }

        @Test
        public void shouldReturnTrueIfSuccessMessageIsPrintedWhenReturnIsSuccessful() {
            borrowables.add(movie);
            when(movie.title()).thenReturn(data);

            bibliotecaMovieInterface.onReturnBorrowableSuccess();

            assertTrue(new String(outStream.toByteArray()).contains(Message.RETURN_MOVIE_SUCCESSFUL_MESSAGE));
        }

        @Test
        public void shouldPrintUnsuccessfulMessageWhenBookDoesNotBelongToLibrary() throws BorrowableNotFoundException {
            when(borrowables.searchByName(data)).thenThrow(BorrowableNotFoundException.class);

            bibliotecaMovieInterface.onReturnBorrowableUnsuccessful();

            assertTrue(new String(outStream.toByteArray()).contains(Message.RETURN_MOVIE_UNSUCCESSFUL_MESSAGE));
        }

    }

}