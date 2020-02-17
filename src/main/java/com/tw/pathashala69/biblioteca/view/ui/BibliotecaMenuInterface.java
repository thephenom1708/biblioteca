package com.tw.pathashala69.biblioteca.view.ui;

import com.tw.pathashala69.biblioteca.core.menu.*;
import com.tw.pathashala69.biblioteca.core.models.Book;
import com.tw.pathashala69.biblioteca.core.models.Movie;
import com.tw.pathashala69.biblioteca.core.ui.MenuInterface;

import java.util.List;

public class BibliotecaMenuInterface implements MenuInterface {
    private final BorrowableListItem<Book> bookListItem;
    private final BorrowableListItem<Movie> movieListItem;
    private final CheckedOutBorrowablesItem<Book> checkedOutBooksItem;
    private final CheckedOutBorrowablesItem<Movie> checkedOutMoviesItem;
    private final CheckoutBorrowableItem<Book> checkoutBookItem;
    private final CheckoutBorrowableItem<Movie> checkoutMovieItem;
    private final ReturnBorrowableItem<Book> returnBookItem;
    private final ReturnBorrowableItem<Movie> returnMovieItem;
    private final LoginItem loginItem;
    private final LogoutItem logoutItem;
    private final QuitItem quitItem;

    public BibliotecaMenuInterface
            (
                    BorrowableListItem<Book> bookListItem,
                    BorrowableListItem<Movie> movieListItem,
                    CheckedOutBorrowablesItem<Book> checkedOutBooksItem,
                    CheckedOutBorrowablesItem<Movie> checkedOutMoviesItem,
                    CheckoutBorrowableItem<Book> checkoutBookItem,
                    CheckoutBorrowableItem<Movie> checkoutMovieItem,
                    ReturnBorrowableItem<Book> returnBookItem,
                    ReturnBorrowableItem<Movie> returnMovieItem,
                    LoginItem loginItem,
                    LogoutItem logoutItem,
                    QuitItem quitItem
            ) {
        this.bookListItem = bookListItem;
        this.movieListItem = movieListItem;
        this.checkedOutBooksItem = checkedOutBooksItem;
        this.checkedOutMoviesItem = checkedOutMoviesItem;
        this.checkoutBookItem = checkoutBookItem;
        this.checkoutMovieItem = checkoutMovieItem;
        this.returnBookItem = returnBookItem;
        this.returnMovieItem = returnMovieItem;
        this.loginItem = loginItem;
        this.logoutItem = logoutItem;
        this.quitItem = quitItem;
    }

    @Override
    public MainMenu guestMenu() {
        return new MainMenu(List.of(bookListItem, movieListItem, loginItem, quitItem));
    }

    @Override
    public MainMenu customerMenu() {
        return new MainMenu(
                List.of(
                        bookListItem, movieListItem, checkoutBookItem, checkoutMovieItem,
                        returnBookItem, returnMovieItem, logoutItem, quitItem
                )
        );
    }

    @Override
    public MainMenu librarianMenu() {
        return new MainMenu(
                List.of(
                        bookListItem, movieListItem,
                        checkedOutBooksItem, checkedOutMoviesItem,
                        logoutItem, quitItem
                )
        );
    }
}
