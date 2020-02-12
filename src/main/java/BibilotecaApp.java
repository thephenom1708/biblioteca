import com.tw.pathashala69.biblioteca.Biblioteca;
import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.core.exception.InvalidMenuOptionException;
import com.tw.pathashala69.biblioteca.core.models.Book;
import com.tw.pathashala69.biblioteca.core.models.Borrowables;
import com.tw.pathashala69.biblioteca.core.models.Library;
import com.tw.pathashala69.biblioteca.io.BookParser;
import com.tw.pathashala69.biblioteca.menu.*;

import java.io.File;
import java.io.IOException;
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
        Library library = new Library(new Borrowables(getBooks()));
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
        BorrowableListItem borrowableListItem = new BorrowableListItem(biblioteca, library);
        CheckoutBorrowableItem checkoutBorrowableItem = new CheckoutBorrowableItem(biblioteca, library);
        ReturnBorrowableItem returnBorrowableItem = new ReturnBorrowableItem(biblioteca, library);
        QuitItem quitItem = new QuitItem(biblioteca);
        return new MainMenu(List.of(borrowableListItem, checkoutBorrowableItem, returnBorrowableItem, quitItem));
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
        return BookParser.parseFile(file);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
