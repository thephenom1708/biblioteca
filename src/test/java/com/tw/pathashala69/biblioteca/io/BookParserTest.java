package com.tw.pathashala69.biblioteca.io;

import com.tw.pathashala69.biblioteca.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class BookParserTest {

    BookParser parser;

    @BeforeEach
    public void setup() {
        parser = new BookParser();
    }

    @Test
    public void shouldReturnBookByParsingName() {
        Book expectedBook = new Book("Harry Potter" );

        Book actualBook = parser.parse("Harry Potter" );

        assertThat(actualBook, is(equalTo(expectedBook)));
    }
}
