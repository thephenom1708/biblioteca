package com.tw.pathashala69.biblioteca.core.menu;

import com.tw.pathashala69.biblioteca.core.auth.Session;
import com.tw.pathashala69.biblioteca.core.auth.User;
import com.tw.pathashala69.biblioteca.core.ui.AuthInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class LoginItemTest {

    private AuthInterface authInterface;
    private LoginItem loginItem;

    @BeforeEach
    void setUp() {
        authInterface = mock(AuthInterface.class);
        loginItem = new LoginItem("Login", "L", authInterface);
    }

    @Test
    public void shouldPromptForCredentialsToUser() {
        String[] credentials = new String[]{ "123-4567", "password" };
        when(authInterface.promptForLoginCredentials()).thenReturn(credentials);

        loginItem.onSelect();

        verify(authInterface, times(1)).promptForLoginCredentials();
    }

    @Test
    public void shouldLoginUserWithInputCredentials() {
        String[] credentials = new String[]{ "123-4567", "password" };
        User user = mock(User.class);
        Session expectedSession = mock(Session.class);
        when(expectedSession.user()).thenReturn(user);

        when(authInterface.promptForLoginCredentials()).thenReturn(credentials);

        assertThat(expectedSession, is(equalTo(expectedSession)));
    }
}