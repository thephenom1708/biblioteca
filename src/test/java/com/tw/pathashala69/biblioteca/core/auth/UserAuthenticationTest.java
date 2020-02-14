package com.tw.pathashala69.biblioteca.core.auth;

import com.tw.pathashala69.biblioteca.core.exception.InvalidCredentialsException;
import com.tw.pathashala69.biblioteca.core.exception.UserAlreadyLoggedInException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserAuthenticationTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = mock(User.class);
        UserAuthentication.register(user);
    }

    @Test
    public void shouldRegisterNewUser() {
        assertTrue(UserAuthentication.register(mock(User.class)));
    }

    @Test
    public void shouldLoginUserWithLibraryNumberAndPassword() throws InvalidCredentialsException, UserAlreadyLoggedInException {
        User newUser = mock(User.class);
        UserAuthentication.register(newUser);
        String libraryNumber = "456-4567";
        String password = "password";
        when(newUser.authenticate(libraryNumber, password)).thenReturn(true);

        User loggedInUser = UserAuthentication.login(libraryNumber, password);

        assertThat(loggedInUser, is(equalTo(newUser)));
    }

    @Test
    public void shouldThrowInvalidCredentialsExceptionWhenWrongCredentialsAreGiven() {
        String libraryNumber = "678-1234";
        String password = "password";
        when(user.authenticate(libraryNumber, password)).thenReturn(false);

        assertThrows(InvalidCredentialsException.class, () -> UserAuthentication.login(libraryNumber, password));
    }

    @Test
    public void shouldThrowUserAlreadyLoggedInExceptionWhenUserHasLoggedIn() throws InvalidCredentialsException, UserAlreadyLoggedInException {
        String libraryNumber = "123-4567";
        String password = "password";
        when(user.authenticate(libraryNumber, password)).thenReturn(true);
        when(user.isLoggedIn()).thenReturn(true);

        assertThrows(UserAlreadyLoggedInException.class, () -> UserAuthentication.login(libraryNumber, password));
    }
}