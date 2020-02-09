package com.tw.pathashala69.biblioteca.io;

import com.tw.pathashala69.biblioteca.models.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Job: Parse the Books from File
public class BookParser {

    public static Book parse(String[] bookParameters) {
        return new Book(bookParameters[0], bookParameters[1], Integer.parseInt(bookParameters[2]));
    }

    public static String[] parseParameters(String line) {
        final String SEPARATOR = ",";
        return line.split(SEPARATOR);
    }

    public static List<Book> parseFile(File file) throws IOException {
        List<Book> books = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] parameters = parseParameters(line);
            Book book = parse(parameters);
            books.add(book);
        }
        return books;
    }
}
