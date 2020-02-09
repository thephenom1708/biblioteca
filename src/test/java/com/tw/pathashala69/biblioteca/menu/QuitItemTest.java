package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.action.QuitAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class QuitItemTest {

    private QuitItem quitItem;
    private QuitAction quitAction;

    @BeforeEach
    void setUp() {
        quitAction = mock(QuitAction.class);
        quitItem = new QuitItem(quitAction);
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
    public void shouldPerformQuitViewWhenSelected() {
        quitItem.onSelect();

        verify(quitAction, times(1)).perform();
    }
}
