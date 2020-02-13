package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.Biblioteca;
import com.tw.pathashala69.biblioteca.UserInterface;
import com.tw.pathashala69.biblioteca.core.models.Borrowables;
import com.tw.pathashala69.biblioteca.core.models.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class BookListItemTest {

    private Borrowables books;
    private UserInterface userInterface;
    private BorrowableListItem bookListItem;

    @BeforeEach
    void setUp() {
        userInterface = mock(Biblioteca.class);
        books = mock(Borrowables.class);
        Library library = mock(Library.class);

        when(library.books()).thenReturn(books);
        when(books.available()).thenReturn(books);

        bookListItem = new BookListItem("List of Books", "B", userInterface, library);
    }

    @Test
    public void shouldReturnName() {
        assertThat(bookListItem.present(), is(equalTo("List of Books")));
    }

    @Test
    public void shouldReturnSymbol() {
        assertThat(bookListItem.symbol(), is(equalTo("B")));
    }

    @Test
    public void shouldReturnListOfBooksFromLibrary() {
        assertThat(bookListItem.borrowables(), is(equalTo(books)));
    }

    @Test
    public void shouldPrintAvailableBooksOnSelection() {
        bookListItem.onSelect();

        verify(userInterface, times(1)).listBorrowables(books.available());
    }
}