package com.tw.pathashala69.biblioteca.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class BookListTest {

    BookList bookList;

    @BeforeEach
    void setUp() {
        bookList = new BookList();
    }

    @Test
    public void shouldReturnName() {
        assertThat(bookList.present(), is(equalTo("View all Books")));
    }

    @Test
    public void shouldReturnSymbol() {
        assertThat(bookList.symbol(), is(equalTo("B")));
    }
}
