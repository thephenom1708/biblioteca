package com.tw.pathashala69.biblioteca.core.auth;

import com.tw.pathashala69.biblioteca.core.menu.MainMenu;
import com.tw.pathashala69.biblioteca.core.ui.MenuInterface;

public enum UserPrivilege {
    GUEST {
        @Override
        public MainMenu menu(MenuInterface menuInterface) {
            return menuInterface.guestMenu();
        }
    },

    CUSTOMER {
        @Override
        public MainMenu menu(MenuInterface menuInterface) {
            return menuInterface.customerMenu();
        }
    },

    LIBRARIAN {
        @Override
        public MainMenu menu(MenuInterface menuInterface) {
            return menuInterface.librarianMenu();
        }
    };

    public abstract MainMenu menu(MenuInterface menuInterface);
}
