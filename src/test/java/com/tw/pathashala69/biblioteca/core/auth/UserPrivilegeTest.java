package com.tw.pathashala69.biblioteca.core.auth;

import com.tw.pathashala69.biblioteca.core.ui.MenuInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class UserPrivilegeTest {

    private MenuInterface menuInterface;

    @BeforeEach
    void setUp() {
        menuInterface = mock(MenuInterface.class);
    }

    @Test
    public void shouldReturnMenuForGUEST() {
        UserPrivilege guestPrivilege = UserPrivilege.GUEST;

        guestPrivilege.menu(menuInterface);

        verify(menuInterface, times(1)).guestMenu();
    }
}