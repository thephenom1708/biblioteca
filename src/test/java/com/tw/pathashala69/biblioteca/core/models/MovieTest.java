package com.tw.pathashala69.biblioteca.core.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MovieTest {

    private Movie movie;
    private ByteArrayOutputStream outStream;

    @BeforeEach
    void setUp() {
        movie = new Movie("Bahubali", 2015, "S. S. Rajamouli", 9.0);
        outStream = new ByteArrayOutputStream();
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

    @Test
    public void shouldPrintMovieDetails() {
        System.setOut(new PrintStream(outStream));

        movie.print(System.out);
        System.setOut(new PrintStream(System.out));

        assertTrue(new String(outStream.toByteArray()).contains(movie.toString()));
    }
}