package com.tw.pathashala69.biblioteca.core.models;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


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
}