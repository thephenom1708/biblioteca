package com.tw.pathashala69.biblioteca.core.auth;

import com.tw.pathashala69.biblioteca.core.exception.InvalidCredentialsException;
import com.tw.pathashala69.biblioteca.core.exception.UserAlreadyLoggedInException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserAuthenticationTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void shouldRegisterNewUser() {
        assertTrue(UserAuthentication.register(mock(User.class)));
    }

    @Test
    public void shouldLoginUserWithLibraryNumberAndPassword() throws InvalidCredentialsException, UserAlreadyLoggedInException {
        User user = mock(User.class);
        UserAuthentication.register(user);
        String libraryNumber = "321-4567";
        String password = "password";
        when(user.authenticate(libraryNumber, password)).thenReturn(true);
        Session session = UserAuthentication.login(libraryNumber, password);

        assertThat(user, is(equalTo(session.user())));
    }

    @Test
    public void shouldThrowInvalidCredentialsExceptionWhenWrongCredentialsAreGiven() {
        User user = mock(User.class);
        UserAuthentication.register(user);
        String libraryNumber = "678-1234";
        String password = "password";
        when(user.authenticate(libraryNumber, password)).thenReturn(false);

        assertThrows(InvalidCredentialsException.class, () -> UserAuthentication.login(libraryNumber, password));
    }

    @Test
    public void shouldThrowUserAlreadyLoggedInExceptionWhenUserHasLoggedIn() throws InvalidCredentialsException, UserAlreadyLoggedInException {
        User user = mock(User.class);
        UserAuthentication.register(user);
        String libraryNumber = "123-4567";
        String password = "password";
        when(user.authenticate(libraryNumber, password)).thenReturn(true);
        UserAuthentication.login(libraryNumber, password);

        assertThrows(UserAlreadyLoggedInException.class, () -> UserAuthentication.login(libraryNumber, password));
    }

    @Test
    public void shouldDestroySessionAndLogoutUser() {
        User user = mock(User.class);
        UserAuthentication.register(user);

        UserAuthentication.logout(user);

        assertFalse(UserAuthentication.isLoggedIn(user));
    }
}