package com.tw.pathashala69.biblioteca.core.auth;

import com.tw.pathashala69.biblioteca.core.menu.MainMenu;

public enum UserPrivilege {
    GUEST {
        @Override
        public MainMenu menu() {
            return null;
        }
    },

    CUSTOMER {
        @Override
        public MainMenu menu() {
            return null;
        }
    },

    LIBRARIAN {
        @Override
        public MainMenu menu() {
            return null;
        }
    };

    public abstract MainMenu menu();
}
