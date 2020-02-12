package com.tw.pathashala69.biblioteca.core.models;

import java.util.Objects;

public class Movie {
    private final String title;
    private final int year;
    private final String director;
    private final double rating;

    public Movie(String title, int year, String director, double rating) {
        this.title = title;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public String title() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return title.equals(movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
