package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.Biblioteca;
import com.tw.pathashala69.biblioteca.core.exception.BookNotAvailable;
import com.tw.pathashala69.biblioteca.core.exception.BookNotFoundException;
import com.tw.pathashala69.biblioteca.core.models.Book;
import com.tw.pathashala69.biblioteca.core.models.Books;
import com.tw.pathashala69.biblioteca.core.models.Library;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CheckoutBookItemTest {

    private Book book;
    private Books books;
    private Library library;
    private Biblioteca biblioteca;
    private CheckoutBookItem checkoutBookItem;

    @BeforeEach
    void setUp() {
        book = mock(Book.class);
        books = mock(Books.class);
        library = mock(Library.class);
        biblioteca = mock(Biblioteca.class);

        when(books.add(book)).thenReturn(true);
        when(biblioteca.checkoutBookInput()).thenReturn("Harry Potter");
        when(biblioteca.library()).thenReturn(library);
        when(library.books()).thenReturn(books);

        checkoutBookItem = new CheckoutBookItem(biblioteca);
    }

    @AfterEach
    void tearDown() {
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    public void shouldCheckoutSelectedBook() throws BookNotAvailable, BookNotFoundException {
        books.add(book);
        when(books.searchByName("Harry Potter")).thenReturn(book);

        checkoutBookItem.onSelect();

        verify(library, times(1)).checkout(book);
    }

    @Test
    public void shouldPerformOnCheckoutSuccessfulWhenCheckoutIsSuccessful() throws BookNotFoundException {
        books.add(book);
        when(books.searchByName("Harry Potter")).thenReturn(book);

        checkoutBookItem.onSelect();

        verify(biblioteca, times(1)).onCheckoutBookSuccess();
    }

    @Test
    public void shouldPerformOnCheckoutUnsuccessfulWhenBookIsNotFound() throws BookNotFoundException {
        when(books.searchByName("Harry Potter")).thenThrow(BookNotFoundException.class);

        checkoutBookItem.onSelect();

        verify(biblioteca, times(1)).onCheckoutBookUnsuccessful();
    }

    @Test
    public void shouldPerformOnCheckoutUnsuccessfulWhenBookIsAlreadyCheckedOut() throws BookNotAvailable, BookNotFoundException {
        books.add(book);
        when(books.searchByName("Harry Potter")).thenReturn(book);
        doThrow(BookNotAvailable.class).when(library).checkout(book);

        checkoutBookItem.onSelect();

        verify(biblioteca, times(1)).onCheckoutBookUnsuccessful();
    }

}
