package com.tw.pathashala69.biblioteca.core.menu;

import com.tw.pathashala69.biblioteca.core.models.Borrowable;
import com.tw.pathashala69.biblioteca.core.ui.BorrowableInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@SuppressWarnings("unchecked")
class QuitItemTest {

    private QuitItem<Borrowable> quitItem;
    private BorrowableInterface<Borrowable> borrowableInterface;

    @BeforeEach
    void setUp() {
        borrowableInterface = mock(BorrowableInterface.class);
        quitItem = new QuitItem<>("Quit", "Q", borrowableInterface);
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

        verify(borrowableInterface, times(1)).exit();
    }
}
