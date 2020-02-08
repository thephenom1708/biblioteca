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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class MainMenuTest {

    private MainMenu mainMenu;
    private List<MenuItem> menuItems;
    private ByteArrayOutputStream outStream;
    BookListItem testBookListItem;

    @BeforeEach
    void setUp() {
        outStream = new ByteArrayOutputStream();
        testBookListItem = mock(BookListItem.class);
        menuItems = List.of(testBookListItem);
        when(testBookListItem.symbol()).thenReturn("B");
        when(testBookListItem.present()).thenReturn("View all Books");

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

        assertTrue(new String(outStream.toByteArray()).contains("1. View all Books [B]"));
    }

    @Test
    @DisplayName("Execute BookList Item when input string is B")
    public void shouldExecuteBookListItemWhenInputIsB() {
        mainMenu.execute();

        verify(testBookListItem, times(1)).onSelect();
    }
}
