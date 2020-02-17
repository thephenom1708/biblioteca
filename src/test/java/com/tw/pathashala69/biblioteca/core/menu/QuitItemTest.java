package com.tw.pathashala69.biblioteca.core.menu;

import com.tw.pathashala69.biblioteca.core.ui.AppInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@SuppressWarnings("unchecked")
class QuitItemTest {

    private QuitItem quitItem;
    private AppInterface appInterface;

    @BeforeEach
    void setUp() {
        appInterface = mock(AppInterface.class);
        quitItem = new QuitItem("Quit", "Q", appInterface);
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

        verify(appInterface, times(1)).exit();
    }
}
