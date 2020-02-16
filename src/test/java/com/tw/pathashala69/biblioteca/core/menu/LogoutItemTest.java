package com.tw.pathashala69.biblioteca.core.menu;

import com.tw.pathashala69.biblioteca.core.ui.AuthInterface;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

class LogoutItemTest {

    private LogoutItem logoutItem;
    private AuthInterface authInterface;

    @BeforeEach
    void setUp() {
        authInterface = mock(AuthInterface.class);
        logoutItem = new LogoutItem("Logout", "LO", authInterface);
    }

}