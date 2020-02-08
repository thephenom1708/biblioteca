import com.tw.pathashala69.biblioteca.Biblioteca;
import com.tw.pathashala69.biblioteca.Book;
import com.tw.pathashala69.biblioteca.Library;
import com.tw.pathashala69.biblioteca.action.BookListView;
import com.tw.pathashala69.biblioteca.io.BookParser;
import com.tw.pathashala69.biblioteca.menu.BookListItem;
import com.tw.pathashala69.biblioteca.menu.MainMenu;
import com.tw.pathashala69.biblioteca.menu.MenuItem;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BibilotecaApp {
    public static void main(String[] args) throws IOException {
        startApplication();
    }

    private static void startApplication() throws IOException {
        System.out.println(Biblioteca.welcome());

        Library library = new Library(getBooks());

        MainMenu mainMenu = new MainMenu(makeMenuItems(library));
        mainMenu.printMenu(System.out);
    }

    private static List<MenuItem> makeMenuItems(Library library) {
        BookListItem bookListItem = new BookListItem(new BookListView(library.books()));
        return List.of(bookListItem);
    }

    private static List<Book> getBooks() throws IOException {
        ClassLoader classLoader = BibilotecaApp.class.getClassLoader();
        File file = new File(classLoader.getResource("io/books.csv").getFile());
        return BookParser.parseFile(file);
    }
}
