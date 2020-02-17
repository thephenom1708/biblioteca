package com.tw.pathashala69.biblioteca.view.ui;

import com.tw.pathashala69.biblioteca.core.auth.UserAuthentication;
import com.tw.pathashala69.biblioteca.core.menu.*;
import com.tw.pathashala69.biblioteca.core.models.Book;
import com.tw.pathashala69.biblioteca.core.models.Library;
import com.tw.pathashala69.biblioteca.core.models.Movie;
import com.tw.pathashala69.biblioteca.core.ui.AppInterface;
import com.tw.pathashala69.biblioteca.core.ui.AuthInterface;
import com.tw.pathashala69.biblioteca.core.ui.BorrowableInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

@SuppressWarnings("unchecked")
class BibliotecaMenuInterfaceTest {

    private AppInterface appInterface;
    private BorrowableInterface<Book> bibliotecaBookInterface;
    private BorrowableInterface<Movie> bibliotecaMovieInterface;
    private AuthInterface authInterface;
    private Library<Book> booksLibrary;
    private Library<Movie> moviesLibrary;
    private UserAuthentication userAuth;

    private BorrowableListItem<Book> bookListItem;
    private BorrowableListItem<Movie> movieListItem;
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
        appInterface = mock(AppInterface.class);
        bibliotecaBookInterface = mock(BorrowableInterface.class);
        bibliotecaMovieInterface = mock(BorrowableInterface.class);
        authInterface = mock(AuthInterface.class);
        booksLibrary = mock(Library.class);
        moviesLibrary = mock(Library.class);
        userAuth = mock(UserAuthentication.class);

        bookListItem = mock(BorrowableListItem.class);
        movieListItem = mock(BorrowableListItem.class);
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
}