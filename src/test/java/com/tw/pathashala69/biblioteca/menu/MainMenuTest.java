package com.tw.pathashala69.biblioteca.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MainMenuTest {

    private MainMenu mainMenu;
    private List<MenuItem> menuItems;
    private BookList testBookList;
    private ByteArrayOutputStream outStream;

    @BeforeEach
    void setUp() {
        outStream = new ByteArrayOutputStream();
        testBookList = mock(BookList.class);
        menuItems = List.of(testBookList);
        when(testBookList.symbol()).thenReturn("B");
        when(testBookList.present()).thenReturn("View all Books");

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
        assertTrue(new String(outStream.toByteArray()).contains("[B]. View all Books"));
    }
}
