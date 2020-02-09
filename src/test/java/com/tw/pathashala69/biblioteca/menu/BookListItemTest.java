package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.action.BookListViewAction;
import com.tw.pathashala69.biblioteca.exception.BookNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class BookListItemTest {

    BookListViewAction bookListViewAction;
    BookListItem bookListItem;

    @BeforeEach
    void setUp() {
        bookListViewAction = mock(BookListViewAction.class);
        bookListItem = new BookListItem(bookListViewAction);
    }

    @Test
    public void shouldReturnName() {
        assertThat(bookListItem.present(), is(equalTo("List of books")));
    }

    @Test
    public void shouldReturnSymbol() {
        assertThat(bookListItem.symbol(), is(equalTo("B")));
    }

    @Test
    public void shouldListAvailableBooksOnSelected() throws BookNotFoundException {
        bookListItem.onSelect();

        verify(bookListViewAction, times(1)).perform();
    }
}
