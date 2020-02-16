package com.tw.pathashala69.biblioteca.core.auth;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LibrarianTest {

    @Test
    public void shouldReturnLibraryNumberOfLibrarian() {
        Librarian librarian = new Librarian("123-4567", "password", UserPrivilege.LIBRARIAN);

        assertThat(librarian.libraryNumber(), is(equalTo("123-4567")));
    }

    @Test
    public void shouldEquateTwoLibrarianWithSameLibraryNumber() {
        Librarian librarian = new Librarian("123-4567", "password", UserPrivilege.LIBRARIAN);
        Librarian anotherLibrarian = new Librarian("123-4567", "password", UserPrivilege.LIBRARIAN);

        assertThat(librarian, is(equalTo(anotherLibrarian)));
    }

    @Test
    public void shouldNotEquateLibrarianHavingDifferentLibraryNumber() {
        Librarian librarian = new Librarian("123-4567", "password", UserPrivilege.LIBRARIAN);
        Librarian anotherLibrarian = new Librarian("123-4789", "password1", UserPrivilege.LIBRARIAN);

        assertThat(librarian, is(not(equalTo(anotherLibrarian))));
    }

    @Test
    public void shouldAuthenticateLibrarianWithLibraryNumberAndPassword() {
        Librarian librarian = new Librarian("123-4567", "password", UserPrivilege.LIBRARIAN);

        assertTrue(librarian.authenticate("123-4567", "password"));
    }

    @Test
    public void shouldNotAuthenticateLibrarianWithWrongLibraryNumberAndPassword() {
        Librarian librarian = new Librarian("123-4567", "password", UserPrivilege.LIBRARIAN);

        assertFalse(librarian.authenticate("679-1234", "password"));
    }

    @Test
    public void shouldReturnPrivilegeLevelAsLIBRARIAN() {
        Librarian librarian = new Librarian("123-4567", "password", UserPrivilege.LIBRARIAN);

        assertThat(librarian.privilege(), is(equalTo(UserPrivilege.LIBRARIAN)));
    }
}
