import com.tw.pathashala69.biblioteca.Biblioteca;
import com.tw.pathashala69.biblioteca.Book;
import com.tw.pathashala69.biblioteca.Library;
import com.tw.pathashala69.biblioteca.Message;
import com.tw.pathashala69.biblioteca.action.BookListViewAction;
import com.tw.pathashala69.biblioteca.action.QuitAction;
import com.tw.pathashala69.biblioteca.io.BookParser;
import com.tw.pathashala69.biblioteca.menu.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class BibilotecaApp {
    private static boolean applicationStatus = true;

    public static void main(String[] args) throws IOException {
        startApplication();
    }

    private static void startApplication() throws IOException {
        System.out.println(Biblioteca.welcome());

        Library library = new Library(getBooks());
        MainMenu mainMenu = new MainMenu(makeMenuItems(library));

        do {
            menuTitle();
            mainMenu.printMenu(System.out);
            String input = userInput();
            try {
                mainMenu.execute(input);
            } catch (InvalidMenuOptionException e) {
                System.out.println(Message.INVALID_OPTION_MESSAGE);
            }
        } while (isRunning());
    }

    private static void menuTitle() {
        System.out.println("\n##################################################################");
        System.out.println(Message.MAIN_MENU_MESSAGE);
        System.out.println("##################################################################\n");
    }

    private static boolean isRunning() {
        return applicationStatus;
    }

    private static String userInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toUpperCase();
    }

    private static List<MenuItem> makeMenuItems(Library library) {
        BookListItem bookListItem = new BookListItem(new BookListViewAction(library.books()));
        QuitItem quitItem = new QuitItem(new QuitAction());
        return List.of(bookListItem, quitItem);
    }

    private static List<Book> getBooks() throws IOException {
        ClassLoader classLoader = BibilotecaApp.class.getClassLoader();
        File file = new File(classLoader.getResource("io/books.csv").getFile());
        return BookParser.parseFile(file);
    }
}
