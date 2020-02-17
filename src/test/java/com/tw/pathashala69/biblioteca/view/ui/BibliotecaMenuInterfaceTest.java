package com.tw.pathashala69.biblioteca.view.ui;

import com.tw.pathashala69.biblioteca.core.menu.*;
import com.tw.pathashala69.biblioteca.core.models.Book;
import com.tw.pathashala69.biblioteca.core.models.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

@SuppressWarnings("unchecked")
class BibliotecaMenuInterfaceTest {

    private BorrowableListItem<Book> bookListItem;
    private BorrowableListItem<Movie> movieListItem;
    private CheckedOutBorrowablesItem<Book> checkedOutBooksItem;
    private CheckedOutBorrowablesItem<Movie> checkedOutMoviesItem;
    private CheckoutBorrowableItem<Book> checkoutBookItem;
    private CheckoutBorrowableItem<Movie> checkoutMovieItem;
    private ReturnBorrowableItem<Book> returnBookItem;
    private ReturnBorrowableItem<Movie> returnMovieItem;
    private LoginItem loginItem;
    private LogoutItem logoutItem;
    private QuitItem quitItem;

    private BibliotecaMenuInterface bibliotecaMenuInterface;

    @BeforeEach
    void setUp() {
        bookListItem = mock(BorrowableListItem.class);
        movieListItem = mock(BorrowableListItem.class);
        checkedOutBooksItem = mock(CheckedOutBorrowablesItem.class);
        checkedOutMoviesItem = mock(CheckedOutBorrowablesItem.class);
        checkoutBookItem = mock(CheckoutBorrowableItem.class);
        checkoutMovieItem = mock(CheckoutBorrowableItem.class);
        returnBookItem = mock(ReturnBorrowableItem.class);
        returnMovieItem = mock(ReturnBorrowableItem.class);
        loginItem = mock(LoginItem.class);
        logoutItem = mock(LogoutItem.class);
        quitItem = mock(QuitItem.class);

        bibliotecaMenuInterface = new BibliotecaMenuInterface
                (
                        bookListItem, movieListItem,
                        checkedOutBooksItem, checkedOutMoviesItem,
                        checkoutBookItem, checkoutMovieItem,
                        returnBookItem, returnMovieItem,
                        loginItem, logoutItem, quitItem
                );
    }

    @Test
    public void shouldReturnGuestMainMenu() {
        MainMenu expectedMenu = new MainMenu(List.of(bookListItem, movieListItem, loginItem, quitItem));

        assertThat(bibliotecaMenuInterface.guestMenu().menuItems(), is(equalTo(expectedMenu.menuItems())));
    }

    @Test
    public void shouldReturnCustomerMainMenu() {
        MainMenu expectedMenu = new MainMenu(
                List.of(
                        bookListItem, movieListItem, checkoutBookItem, checkoutMovieItem,
                        returnBookItem, returnMovieItem, logoutItem, quitItem
                ));

        assertThat(bibliotecaMenuInterface.customerMenu().menuItems(), is(equalTo(expectedMenu.menuItems())));
    }

    @Test
    public void shouldReturnLibrarianMainMenu() {
        MainMenu expectedMenu = new MainMenu(
                List.of(
                        bookListItem, movieListItem,
                        checkedOutBooksItem, checkedOutMoviesItem,
                        logoutItem, quitItem
                ));

        assertThat(bibliotecaMenuInterface.librarianMenu().menuItems(), is(equalTo(expectedMenu.menuItems())));
    }
}