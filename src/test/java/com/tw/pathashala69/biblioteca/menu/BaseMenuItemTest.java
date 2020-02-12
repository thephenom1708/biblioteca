package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.core.exception.BookNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class BaseMenuItemTest {

    private BaseMenuItem menuItem;

    @BeforeEach
    public void setUp() {
        menuItem = new BaseMenuItem("Test Menu", "T");
    }

    @Test
    void shouldReturnNameOfMenuItem() {
        assertThat(menuItem.present(), is(equalTo("Test Menu")));
    }

    @Test
    void symbolReturnSymbolOfMenuItem() {
        assertThat(menuItem.symbol(), is(equalTo("T")));
    }

    @Test
    void shouldPerformSelectionTaskOnSelected() throws BookNotFoundException {
        menuItem.onSelect();
    }
}