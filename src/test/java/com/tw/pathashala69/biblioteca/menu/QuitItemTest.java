package com.tw.pathashala69.biblioteca.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class QuitItemTest {

    QuitItem quitItem;

    @BeforeEach
    void setUp() {
        quitItem = new QuitItem();
    }

    @Test
    public void shouldReturnName() {
        assertThat(quitItem.present(), is(equalTo("Quit")));
    }

    @Test
    public void shouldReturnSymbol() {
        assertThat(quitItem.symbol(), is(equalTo("Q")));
    }
}