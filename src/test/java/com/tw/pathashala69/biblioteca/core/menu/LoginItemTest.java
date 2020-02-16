package com.tw.pathashala69.biblioteca.core.menu;

import com.tw.pathashala69.biblioteca.core.auth.Session;
import com.tw.pathashala69.biblioteca.core.auth.UserAuthentication;
import com.tw.pathashala69.biblioteca.core.exception.InvalidCredentialsException;
import com.tw.pathashala69.biblioteca.core.exception.UserAlreadyLoggedInException;
import com.tw.pathashala69.biblioteca.core.ui.AuthInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class LoginItemTest {

    private String[] credentials;
    private AuthInterface authInterface;
    private UserAuthentication userAuth;
    private LoginItem loginItem;

    @BeforeEach
    void setUp() throws InvalidCredentialsException, UserAlreadyLoggedInException {
        Session session = mock(Session.class);
        authInterface = mock(AuthInterface.class);
        userAuth = mock(UserAuthentication.class);
        loginItem = new LoginItem("Login", "L", authInterface, userAuth);

        credentials = new String[]{ "123-4567", "password" };
        when(authInterface.promptForLoginCredentials()).thenReturn(credentials);
        when(userAuth.login(credentials[0], credentials[1])).thenReturn(session);
    }

    @Test
    public void shouldPromptForCredentialsToUser() {
        loginItem.onSelect();

        verify(authInterface, times(1)).promptForLoginCredentials();
    }

    @Test
    public void shouldLoginUserWithInputCredentials() throws InvalidCredentialsException, UserAlreadyLoggedInException {
        loginItem.onSelect();

        verify(userAuth, times(1)).login(credentials[0], credentials[1]);
    }
}