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

class MovieListItemTest {

    private Borrowables movies;
    private UserInterface userInterface;
    private BorrowableListItem movieListItem;

    @BeforeEach
    void setUp() {
        userInterface = mock(Biblioteca.class);
        movies = mock(Borrowables.class);
        Library library = mock(Library.class);

        when(library.movies()).thenReturn(movies);
        when(movies.available()).thenReturn(movies);

        movieListItem = new MovieListItem("List of Movies", "M", userInterface, library);
    }

    @Test
    public void shouldReturnName() {
        assertThat(movieListItem.present(), is(equalTo("List of Movies")));
    }

    @Test
    public void shouldReturnSymbol() {
        assertThat(movieListItem.symbol(), is(equalTo("M")));
    }


    @Test
    public void shouldReturnListOfMoviesFromLibrary() {
        assertThat(movieListItem.borrowables(), is(equalTo(movies)));
    }

    @Test
    public void shouldPrintAvailableBooksOnSelection() {
        movieListItem.onSelect();

        verify(userInterface, times(1)).listBorrowables(movies.available());
    }
}