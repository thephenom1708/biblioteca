package com.tw.pathashala69.biblioteca.view.io;

import com.tw.pathashala69.biblioteca.core.models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class BookParserTest {

    Book book1, book2, book3;
    List<Book> books;

    @BeforeEach
    public void setup() {
        book1 = new Book("Harry Potter", "J. K. Rowling", 2019);
        book2 = new Book("Alchemist", "Paulo Coelho", 2015);
        book3 = new Book("The Secret", "Rhonda Byrne", 2004);
        books = List.of(book1, book2, book3);
    }

    @Test
    public void shouldReturnBookByParsingParameters() {
        Book expectedBook = book1;
        String[] bookParameters = { "Harry Potter", "J. K. Rowling", "2019" };

        Book actualBook = BookParser.parse(bookParameters);

        assertThat(actualBook, is(equalTo(expectedBook)));
    }

    @Test
    public void shouldReturnBookParametersByParsingCommaSeparatedLineIsGiven() {
        String line = "Harry Potter,J. K. Rowling,2019";
        String[] expectedBookParameters = { "Harry Potter", "J. K. Rowling", "2019" };

        String[] actualBookParameters = BookParser.parseParameters(line);

        assertThat(actualBookParameters, is(equalTo(expectedBookParameters)));
    }

    @Test
    public void shouldReturnListOfBooksByParsingLinesFromCSVFile() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File inputFile = new File(classLoader.getResource("io/books.csv").getFile());

        List<Book> actualBooks = BookParser.parseFile(inputFile);

        assertThat(actualBooks, is(equalTo(books)));
    }
}
