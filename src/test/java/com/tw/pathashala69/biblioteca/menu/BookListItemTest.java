package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.action.BookListView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class BookListItemTest {

    BookListView bookListView;
    BookListItem bookListItem;

    @BeforeEach
    void setUp() {
        bookListView = mock(BookListView.class);
        bookListItem = new BookListItem(bookListView);
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
    public void shouldListAvailableBooksOnSelected() {
        bookListItem.onSelect();

        verify(bookListView, times(1)).perform();
    }
}
