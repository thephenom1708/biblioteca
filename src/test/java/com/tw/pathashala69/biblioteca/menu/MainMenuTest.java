package com.tw.pathashala69.biblioteca.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class MainMenuTest {

    private MainMenu mainMenu;
    private List<MenuItem> menuItems;
    private ByteArrayOutputStream outStream;
    private BookListItem testBookListItem;
    private QuitItem testQuitItem;

    @BeforeEach
    void setUp() {
        outStream = new ByteArrayOutputStream();
        testBookListItem = mock(BookListItem.class);
        testQuitItem = mock(QuitItem.class);

        menuItems = List.of(testBookListItem, testQuitItem);

        when(testBookListItem.symbol()).thenReturn("B");
        when(testBookListItem.present()).thenReturn("List of books");
        when(testQuitItem.symbol()).thenReturn("Q");
        when(testQuitItem.present()).thenReturn("Quit");

        mainMenu = new MainMenu(menuItems);
    }

    @Test
    public void shouldReturnListOfAllMenuItems() {
        assertThat(mainMenu.menuItems(), is(equalTo(menuItems)));
    }

    @Test
    public void shouldPrintListOfAllMenuItems() {
        System.setOut(new PrintStream(outStream));

        mainMenu.printMenu(System.out);
        System.setOut(new PrintStream(System.out));

        assertTrue(new String(outStream.toByteArray()).contains("1. List of books [B]"));
    }

    @Test
    @DisplayName("View list of books when input string is B")
    public void shouldExecuteBookListItemWhenInputIsB() throws InvalidMenuOptionException {
        mainMenu.execute("B");

        verify(testBookListItem, times(1)).onSelect();
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
