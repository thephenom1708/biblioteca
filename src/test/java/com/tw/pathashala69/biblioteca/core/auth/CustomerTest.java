package com.tw.pathashala69.biblioteca.core.auth;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class CustomerTest {

    @Test
    public void shouldReturnLibraryNumberOfCustomer() {
        Customer customer = new Customer("123-4567", "password");

        assertThat(customer.libraryNumber(), is(equalTo("123-4567")));
    }

    @Test
    public void shouldEquateTwoCustomersWithSameLibraryNumber() {
        Customer customer = new Customer("123-4567", "password");
        Customer anotherCustomer = new Customer("123-4567", "password");

        assertThat(customer, is(equalTo(anotherCustomer)));
    }

    @Test
    public void shouldNotEquateCustomersHavingDifferentLibraryNumber() {
        Customer customer = new Customer("123-4567", "password");
        Customer anotherCustomer = new Customer("123-4789", "password1");

        assertThat(customer, is(not(equalTo(anotherCustomer))));
    }

    @Test
    public void shouldAuthenticateUserWithLibraryNumberAndPassword() {
        Customer customer = new Customer("123-4567", "password");

        assertTrue(customer.authenticate("123-4567", "password"));
    }

    @Test
    public void shouldNotAuthenticateUserWithWrongLibraryNumberAndPassword() {
        Customer customer = new Customer("123-4567", "password");

        assertFalse(customer.authenticate("679-1234", "password"));
    }

    @Test
    public void shouldCheckWhetherCustomerIsLoggedInOrNot() {
        Customer customer = new Customer("123-4567", "password");
        customer.loggedIn();

        assertTrue(customer.isLoggedIn());
    }
}