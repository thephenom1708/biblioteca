package com.tw.pathashala69.biblioteca.core.models;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


class CustomerTest {

    @Test
    public void shouldReturnLibraryNumberOfCustomer() {
        Customer customer = new Customer("123-4567", "password");

        assertThat(customer.libraryNumber(), is(equalTo("123-4567")));
    }

}