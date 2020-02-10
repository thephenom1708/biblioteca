package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.exception.BookNotFoundException;
import com.tw.pathashala69.biblioteca.models.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

class QuitItemTest {

    private QuitItem quitItem;
    private Library library;

    @BeforeEach
    void setUp() {
        library = mock(Library.class);
        quitItem = new QuitItem(library);
    }

    @Test
    public void shouldReturnName() {
        assertThat(quitItem.present(), is(equalTo("Quit")));
    }

    @Test
    public void shouldReturnSymbol() {
        assertThat(quitItem.symbol(), is(equalTo("Q")));
    }

    @Test
    public void shouldPerformQuitViewWhenSelected() throws BookNotFoundException {
        quitItem.onSelect();
    }
}
