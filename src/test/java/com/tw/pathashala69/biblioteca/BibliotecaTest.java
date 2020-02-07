package com.tw.pathashala69.biblioteca;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class LibraryTest {

    @Test
    public void shouldReturnWelcomeMessageWhenCustomerStartsApplication() {
        String expectedWelcomeMessage = "Welcome to Biblioteca. Your one-stop-shop for great book " +
                "titles in Bangalore!";

        String actualWelcomeMessage = Library.welcome();

        assertThat(actualWelcomeMessage, is(equalTo(expectedWelcomeMessage)));
    }
}
