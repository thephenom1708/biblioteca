package com.tw.pathashala69.biblioteca.core.models;

import java.util.Objects;

public class Movie {
    private final String name;
    private final int year;
    private final String director;
    private final double rating;

    public Movie(String name, int year, String director, double rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return name.equals(movie.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
