package com.tw.pathashala69.biblioteca.view;

import com.tw.pathashala69.biblioteca.core.auth.Customer;
import com.tw.pathashala69.biblioteca.core.auth.Librarian;
import com.tw.pathashala69.biblioteca.core.auth.UserAuthentication;
import com.tw.pathashala69.biblioteca.core.auth.UserPrivilege;
import com.tw.pathashala69.biblioteca.core.ui.AppInterface;
import com.tw.pathashala69.biblioteca.core.ui.AuthInterface;
import com.tw.pathashala69.biblioteca.core.ui.BorrowableInterface;
import com.tw.pathashala69.biblioteca.core.ui.MenuInterface;
import com.tw.pathashala69.biblioteca.view.constants.Message;
import com.tw.pathashala69.biblioteca.view.constants.Symbol;
import com.tw.pathashala69.biblioteca.core.exception.InvalidMenuOptionException;
import com.tw.pathashala69.biblioteca.core.models.*;
import com.tw.pathashala69.biblioteca.core.menu.*;
import com.tw.pathashala69.biblioteca.view.io.BookParser;
import com.tw.pathashala69.biblioteca.view.ui.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibilotecaApp {

    public static void main(String[] args) throws IOException {
        startApplication();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private static void startApplication() throws IOException {
        System.out.println(BibliotecaBookInterface.welcome());

        AppInterface appInterface = new BibliotecaAppInterface();
        BorrowableInterface<Book> bibliotecaBookInterface = new BibliotecaBookInterface();
        BorrowableInterface<Movie> bibliotecaMovieInterface = new BibliotecaMovieInterface();
        AuthInterface authInterface = new BibliotecaAuthInterface();

        Library<Book> booksLibrary = new Library<>(new Borrowables<>(getBooks()));
        Library<Movie> moviesLibrary = new Library<>(new Borrowables<>(getMovies()));

        UserAuthentication userAuth = new UserAuthentication();
        registerUsers();

        BorrowableListItem<Book> bookListItem =
                new BorrowableListItem<>(Message.BOOKS_LIST_OPTION, Symbol.B, bibliotecaBookInterface, booksLibrary);

        BorrowableListItem<Movie> movieListItem =
                new BorrowableListItem<>(Message.MOVIE_LIST_OPTION, Symbol.M, bibliotecaMovieInterface, moviesLibrary);

        CheckedOutBorrowablesItem<Book> checkedOutBooksItem =
                new CheckedOutBorrowablesItem<>(Message.CHECKED_OUT_BOOKS_LIST_OPTION, Symbol.COB, bibliotecaBookInterface, booksLibrary);

        CheckedOutBorrowablesItem<Movie> checkedOutMoviesItem =
                new CheckedOutBorrowablesItem<>(Message.CHECKED_OUT_MOVIE_LIST_OPTION, Symbol.COM, bibliotecaMovieInterface, moviesLibrary);

        CheckoutBorrowableItem<Book> checkoutBookItem =
                new CheckoutBorrowableItem<>(Message.CHECKOUT_BOOK_OPTION, Symbol.CB, bibliotecaBookInterface,
                        booksLibrary, userAuth);

        CheckoutBorrowableItem<Movie> checkoutMovieItem =
                new CheckoutBorrowableItem<>(Message.CHECKOUT_MOVIE_OPTION, Symbol.CM, bibliotecaMovieInterface,
                        moviesLibrary, userAuth);

        ReturnBorrowableItem<Book> returnBookItem =
                new ReturnBorrowableItem<>(Message.RETURN_BOOK_OPTION, Symbol.RB, bibliotecaBookInterface, booksLibrary);

        ReturnBorrowableItem<Movie> returnMovieItem =
                new ReturnBorrowableItem<>(Message.RETURN_MOVIE_OPTION, Symbol.RM, bibliotecaMovieInterface, moviesLibrary);

        LoginItem loginItem = new LoginItem(Message.LOGIN_OPTION, Symbol.L, authInterface, userAuth);

        LogoutItem logoutItem = new LogoutItem(Message.LOGOUT_OPTION, Symbol.LO, authInterface, userAuth);

        QuitItem quitItem = new QuitItem(Message.QUIT_OPTION, Symbol.Q, appInterface);

        MenuInterface menuInterface = new BibliotecaMenuInterface
                (
                        bookListItem, movieListItem,
                        checkedOutBooksItem, checkedOutMoviesItem,
                        checkoutBookItem, checkoutMovieItem,
                        returnBookItem, returnMovieItem,
                        loginItem, logoutItem, quitItem
                );

        MainMenu mainMenu;
        do {
            clearScreen();
            menuTitle();
            mainMenu = userAuth.userPrivilege().menu(menuInterface);
            printMenu(mainMenu);
            String input = userInput();
            try {
                mainMenu.execute(input);
            } catch (InvalidMenuOptionException e) {
                System.out.println(Message.INVALID_OPTION_MESSAGE);
            }
        } while (true);
    }

    private static void menuTitle() {
        System.out.println("\n##################################################################");
        System.out.println(Message.MAIN_MENU_MESSAGE);
        System.out.println("##################################################################\n");
    }

    private static void printMenu(MainMenu menu) {
        int counter = 1;
        List<MenuItem> menuItems = menu.menuItems();
        for (MenuItem item : menuItems) {
            System.out.println(counter + ". " + item.present() + " [" + item.symbol() + "]");
            counter++;
        }
        System.out.println(Message.ENTER_COMMAND_MESSAGE);
    }

    private static String userInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toUpperCase();
    }

    private static List<Book> getBooks() throws IOException {
        ClassLoader classLoader = BibilotecaApp.class.getClassLoader();
        @SuppressWarnings("ConstantConditions") File file = new File(classLoader.getResource("io/books.csv").getFile());
        return new ArrayList<>(BookParser.parseFile(file));
    }

    private static List<Movie> getMovies() {
        return List.of(
                new Movie("Bahubali", 2015, "S. S. Rajamouli", 9.5),
                new Movie("Singham", 2014, "Rohit Shetty", 8.3),
                new Movie("Interstellar", 2007, "Christopher Nolan", 10.0)
            );
    }

    private static void registerUsers() {
        Customer customer1 = new Customer("222-4567", "password1", UserPrivilege.CUSTOMER);
        Customer customer2 = new Customer("333-4567", "password2", UserPrivilege.CUSTOMER);
        Customer customer3 = new Customer("444-4567", "password3", UserPrivilege.CUSTOMER);

        Librarian librarian1 = new Librarian("100-2222", "pass123", UserPrivilege.LIBRARIAN);
        Librarian librarian2 = new Librarian("100-3333", "pass456", UserPrivilege.LIBRARIAN);
        Librarian librarian3 = new Librarian("100-4444", "pass789", UserPrivilege.LIBRARIAN);

        UserAuthentication.register(customer1);
        UserAuthentication.register(customer2);
        UserAuthentication.register(customer3);

        UserAuthentication.register(librarian1);
        UserAuthentication.register(librarian2);
        UserAuthentication.register(librarian3);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
