package com.tw.pathashala69.biblioteca.core.auth;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class GuestTest {

    @Test
    public void shouldReturnPrivilegeLevelAsGuest() {
        Guest guest = new Guest("123-4567", "password", UserPrivilege.GUEST);

        assertThat(guest.privilege(), is(equalTo(UserPrivilege.GUEST)));
    }
}