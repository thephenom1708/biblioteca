import com.tw.pathashala69.biblioteca.Biblioteca;
import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.constants.Symbol;
import com.tw.pathashala69.biblioteca.core.exception.InvalidMenuOptionException;
import com.tw.pathashala69.biblioteca.core.models.*;
import com.tw.pathashala69.biblioteca.io.BookParser;
import com.tw.pathashala69.biblioteca.menu.*;

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
        System.out.println(Biblioteca.welcome());

        Biblioteca biblioteca = new Biblioteca(System.out);
        Library library = new Library(new Borrowables(getBooks()), new Borrowables(getMovies()));
        MainMenu mainMenu = mainMenu(biblioteca, library);

        do {
            clearScreen();
            menuTitle();
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

    private static MainMenu mainMenu(Biblioteca biblioteca, Library library) {
        BorrowableListItem bookListItem =
                new BookListItem(Message.BOOKS_LIST_OPTION, Symbol.B, biblioteca, library);

        BorrowableListItem movieListItem =
                new MovieListItem(Message.MOVIE_LIST_OPTION, Symbol.M, biblioteca, library);

        CheckoutBorrowableItem checkoutBookItem =
                new CheckoutBorrowableItem(Message.CHECKOUT_BOOK_OPTION, Symbol.CB, biblioteca, library, bookListItem);

        CheckoutBorrowableItem checkoutMovieItem =
                new CheckoutBorrowableItem(Message.CHECKOUT_MOVIE_OPTION, Symbol.CM, biblioteca, library, movieListItem);

        ReturnBorrowableItem returnBorrowableItem = new ReturnBorrowableItem(biblioteca, library);

        QuitItem quitItem = new QuitItem(biblioteca);
        return new MainMenu(
                List.of(bookListItem, movieListItem, checkoutBookItem, checkoutMovieItem, returnBorrowableItem, quitItem)
            );
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

    private static List<Borrowable> getBooks() throws IOException {
        ClassLoader classLoader = BibilotecaApp.class.getClassLoader();
        @SuppressWarnings("ConstantConditions") File file = new File(classLoader.getResource("io/books.csv").getFile());
        return new ArrayList<>(BookParser.parseFile(file));
    }

    private static List<Borrowable> getMovies() {
        return List.of(
                new Movie("Bahubali", 2015, "S. S. Rajamouli", 9.5),
                new Movie("Singham", 2014, "Rohit Shetty", 8.3),
                new Movie("Interstellar", 2007, "Christopher Nolan", 10.0)
            );
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
