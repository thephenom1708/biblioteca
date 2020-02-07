package com.tw.pathashala69.biblioteca.io;

import com.tw.pathashala69.biblioteca.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class BookParserTest {

    BookParser parser;
    List<Book> books;

    @BeforeEach
    public void setup() {
        books = List.of(new Book("Harry Potter"), new Book("Alchemist"), new Book("The Secret"));
        parser = new BookParser();
    }

    @Test
    public void shouldReturnBookByParsingParameters() {
        Book expectedBook = new Book("Harry Potter" );
        String[] bookParameters = {"Harry Potter"};

        Book actualBook = parser.parse(bookParameters);

        assertThat(actualBook, is(equalTo(expectedBook)));
    }

    @Test
    public void shouldReturnBookParametersByParsingCommaSeparatedLineIsGiven() {
        String line = "Harry Potter";
        String[] expectedBookParameters = {"Harry Potter"};

        String[] actualBookParameters = parser.parseParameters(line);

        assertThat(actualBookParameters, is(equalTo(expectedBookParameters)));
    }

    @Test
    public void shouldReturnListOfBooksByParsingLinesFromCSVFile() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File inputFile = new File(classLoader.getResource("io/books.csv").getFile());

        List<Book> actualBooks = parser.parseFile(inputFile);

        assertThat(actualBooks, is(equalTo(books)));
    }
}
