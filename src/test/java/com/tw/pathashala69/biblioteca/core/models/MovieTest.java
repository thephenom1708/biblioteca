package com.tw.pathashala69.biblioteca.core.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class MovieTest {

    private Movie movie;

    @BeforeEach
    void setUp() {
        movie = new Movie("Bahubali", 2015, "S. S. Rajamouli", 9.0);
    }

    @Test
    public void shouldEquateMovieWithAnotherMovieIfBothHaveSameName() {
       Movie anotherMovie = new Movie("Bahubali", 2015, "S. S. Rajamouli", 9.0);

       assertThat(movie, is(equalTo(anotherMovie)));
    }

    @Test
    public void shouldNotEquateMoviesWithDifferentNames() {
        Movie anotherMovie = new Movie("Singham", 2015, "S. S. Rajamouli", 9.0);

        assertThat(movie, is(not(equalTo(anotherMovie))));
    }

    @Test
    public void shouldReturnTitleOfMovie() {
        assertThat(movie.title(), is(equalTo("Bahubali")));
    }
}