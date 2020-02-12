package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.core.exception.InvalidMenuOptionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MainMenuTest {

    private MainMenu mainMenu;
    private List<MenuItem> menuItems;
    private BookListItem testBookListItem;
    private CheckoutBookItem testCheckoutBookItem;
    private ReturnBookItem testReturnBookItem;
    private QuitItem testQuitItem;

    @BeforeEach
    void setUp() {
        testBookListItem = mock(BookListItem.class);
        testCheckoutBookItem = mock(CheckoutBookItem.class);
        testReturnBookItem = mock(ReturnBookItem.class);
        testQuitItem = mock(QuitItem.class);

        menuItems = List.of(testBookListItem, testCheckoutBookItem, testReturnBookItem, testQuitItem);

        when(testBookListItem.symbol()).thenReturn("B");
        when(testBookListItem.present()).thenReturn("List of books");
        when(testCheckoutBookItem.present()).thenReturn("Checkout Book");
        when(testCheckoutBookItem.symbol()).thenReturn("CB");
        when(testReturnBookItem.present()).thenReturn("Return Book");
        when(testReturnBookItem.symbol()).thenReturn("RB");
        when(testQuitItem.symbol()).thenReturn("Q");
        when(testQuitItem.present()).thenReturn("Quit");

        mainMenu = new MainMenu(menuItems);
    }

    @Test
    public void shouldReturnListOfAllMenuItems() {
        assertThat(mainMenu.menuItems(), is(equalTo(menuItems)));
    }

    @Test
    @DisplayName("View list of books when input string is B")
    public void shouldExecuteBookListItemWhenInputIsB() throws InvalidMenuOptionException {
        mainMenu.execute("B");

        verify(testBookListItem, times(1)).onSelect();
    }

    @Test
    @DisplayName("Checkout book when input string is CB")
    public void shouldExecuteCheckoutBookItemWhenInputIsCB() throws InvalidMenuOptionException {
        mainMenu.execute("CB");

        verify(testCheckoutBookItem, times(1)).onSelect();
    }

    @Test
    @DisplayName("Return book when input string is RB")
    public void shouldExecuteReturnBookItemWhenInputIsRB() throws InvalidMenuOptionException {
        mainMenu.execute("RB");

        verify(testReturnBookItem, times(1)).onSelect();
    }

    @Test
    public void shouldThrowExceptionIfInvalidInputIsGiven() {
        assertThrows(InvalidMenuOptionException.class, () -> mainMenu.execute("A"));
    }

    @Test
    @DisplayName("Quit application when input string is Q")
    public void shouldExecuteQuitItemWhenInputIsQ() throws InvalidMenuOptionException {
        mainMenu.execute("Q");

        verify(testQuitItem, times(1)).onSelect();
    }
}
