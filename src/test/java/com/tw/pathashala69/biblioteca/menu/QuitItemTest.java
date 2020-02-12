package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.Biblioteca;
import com.tw.pathashala69.biblioteca.UserInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class QuitItemTest {

    private QuitItem quitItem;
    private UserInterface userInterface;

    @BeforeEach
    void setUp() {
        userInterface = mock(Biblioteca.class);
        quitItem = new QuitItem(userInterface);
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
    public void shouldQuitApplicationWhenSelected() {
        quitItem.onSelect();

        verify(userInterface, times(1)).exit();
    }
}
