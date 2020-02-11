package com.tw.pathashala69.biblioteca;

import com.tw.pathashala69.biblioteca.models.Book;
import com.tw.pathashala69.biblioteca.models.Books;
import com.tw.pathashala69.biblioteca.models.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class BibliotecaTest {

    private Book book;
    private Books books;
    private Library library;
    private Biblioteca biblioteca;

    @BeforeEach
    void setUp() {
        book = mock(Book.class);
        books = new Books(List.of(book, book));

        library = mock(Library.class);
        biblioteca = new Biblioteca(library, System.out);

        when(library.books()).thenReturn(books);
    }

    @Test
    public void shouldReturnWelcomeMessageWhenCustomerStartsApplication() {
        String expectedWelcomeMessage = "Welcome to Biblioteca. Your one-stop-shop for great book " +
                "titles in Bangalore!";

        String actualWelcomeMessage = Biblioteca.welcome();

        assertThat(actualWelcomeMessage, is(equalTo(expectedWelcomeMessage)));
    }

    @Test
    public void shouldPrintListOfBooksToConsole() {
        biblioteca.printAvailableBooks();

        verify(book, times(2)).print(System.out);
    }
}
