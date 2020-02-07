package com.tw.pathashala69.biblioteca.io;

import com.tw.pathashala69.biblioteca.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Job: Parse the Books from File
public class BookParser {
    private final String SEPARATOR = ",";

    public Book parse(String[] bookParameters) {
        return new Book(bookParameters[0]);
    }

    public String[] parseParameters(String line) {
        return line.split(SEPARATOR);
    }

    public List<Book> parseFile(File file) throws IOException {
        List<Book> books = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            String[] parameters = parseParameters(line);
            Book book = parse(parameters);
            books.add(book);
        }
        return books;
    }
}
