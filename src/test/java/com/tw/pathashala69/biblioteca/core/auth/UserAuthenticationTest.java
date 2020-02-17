package com.tw.pathashala69.biblioteca.core.auth;

import com.tw.pathashala69.biblioteca.core.exception.InvalidCredentialsException;
import com.tw.pathashala69.biblioteca.core.exception.UserAlreadyLoggedInException;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserAuthenticationTest {

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
        UserAuthentication userAuthentication = new UserAuthentication();

        Session session = userAuthentication.login(libraryNumber, password);

        assertThat(user, is(equalTo(session.user())));
    }

    @Test
    public void shouldThrowInvalidCredentialsExceptionWhenWrongCredentialsAreGiven() {
        User user = mock(User.class);
        UserAuthentication.register(user);
        String libraryNumber = "678-1234";
        String password = "password";
        when(user.authenticate(libraryNumber, password)).thenReturn(false);
        UserAuthentication userAuthentication = new UserAuthentication();

        assertThrows(InvalidCredentialsException.class, () -> userAuthentication.login(libraryNumber, password));
    }

    @Test
    public void shouldThrowUserAlreadyLoggedInExceptionWhenUserHasLoggedIn() throws InvalidCredentialsException, UserAlreadyLoggedInException {
        User user = mock(User.class);
        UserAuthentication.register(user);
        String libraryNumber = "123-4567";
        String password = "password";
        when(user.authenticate(libraryNumber, password)).thenReturn(true);
        UserAuthentication userAuthentication = new UserAuthentication();
        userAuthentication.login(libraryNumber, password);

        assertThrows(UserAlreadyLoggedInException.class, () -> userAuthentication.login(libraryNumber, password));
    }

    @Test
    public void shouldDestroySessionAndLogoutUser() throws InvalidCredentialsException, UserAlreadyLoggedInException {
        User user = mock(User.class);
        UserAuthentication.register(user);
        when(user.authenticate("789-0123", "password")).thenReturn(true);
        UserAuthentication userAuthentication = new UserAuthentication();
        Session session = userAuthentication.login("789-0123", "password");

        userAuthentication.logout();

        assertFalse(userAuthentication.isLoggedIn(session.user()));
    }

    @Test
    public void shouldReturnUserPrivilegeLevelForActiveSession() throws InvalidCredentialsException, UserAlreadyLoggedInException {
        User user = mock(User.class);
        UserAuthentication.register(user);
        when(user.authenticate("790-0123", "password")).thenReturn(true);
        UserAuthentication userAuthentication = new UserAuthentication();
        userAuthentication.login("790-0123", "password");

        userAuthentication.userPrivilege();

        verify(user, times(1)).privilege();
    }

    @Test
    public void shouldReturnActiveSession() throws InvalidCredentialsException, UserAlreadyLoggedInException {
        User user = mock(User.class);
        UserAuthentication.register(user);
        when(user.authenticate("799-0123", "password")).thenReturn(true);
        UserAuthentication userAuthentication = new UserAuthentication();
        Session expectedSession = userAuthentication.login("799-0123", "password");

        userAuthentication.userPrivilege();

        assertThat(userAuthentication.activeSession(), is(equalTo(expectedSession)));
    }
}