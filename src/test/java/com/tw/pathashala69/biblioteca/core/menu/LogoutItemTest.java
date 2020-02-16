package com.tw.pathashala69.biblioteca.core.menu;

import com.tw.pathashala69.biblioteca.core.auth.UserAuthentication;
import com.tw.pathashala69.biblioteca.core.ui.AuthInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class LogoutItemTest {

    private UserAuthentication userAuth;
    private AuthInterface authInterface;
    private LogoutItem logoutItem;

    @BeforeEach
    void setUp() {
        userAuth = mock(UserAuthentication.class);
        authInterface = mock(AuthInterface.class);
        logoutItem = new LogoutItem("Logout", "LO", authInterface, userAuth);
    }

    @Test
    public void shouldLogoutTheLoggedInUser() {
        logoutItem.onSelect();

        verify(userAuth, times(1)).logout();
    }

    @Test
    public void shouldPerformUIOperationOnLogout() {
        logoutItem.onSelect();

        verify(authInterface, times(1)).onLogout();
    }
}