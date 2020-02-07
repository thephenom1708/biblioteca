import com.tw.pathashala69.biblioteca.Biblioteca;
import com.tw.pathashala69.biblioteca.Book;
import com.tw.pathashala69.biblioteca.Library;
import com.tw.pathashala69.biblioteca.io.BookParser;

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
        List<Book> books = library.books();

        System.out.println("\nList of All Books: ");
        books.forEach(book -> System.out.println(book));
    }

    private static List<Book> getBooks() throws IOException {
        ClassLoader classLoader = BibilotecaApp.class.getClassLoader();
        File file = new File(classLoader.getResource("io/books.csv").getFile());
        return BookParser.parseFile(file);
    }
}
