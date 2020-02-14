package com.tw.pathashala69.biblioteca.core.menu;

import com.tw.pathashala69.biblioteca.core.ui.AuthInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        loginItem.onSelect();

        verify(authInterface, times(1)).promptForLoginCredentials();
    }
}