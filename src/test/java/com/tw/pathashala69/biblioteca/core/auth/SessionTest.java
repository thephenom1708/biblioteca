package com.tw.pathashala69.biblioteca.core.auth;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

class SessionTest {

    @Test
    public void shouldStartSessionForUser() {
        User user = mock(User.class);
        Session session = new Session(user);

        assertThat(session.user(), is(equalTo(user)));
    }

    @Test
    public void shouldDestroySessionForUser() {
        Session session = new Session(mock(User.class));

        session.destroy();

        assertThat(session.user(), is(equalTo(null)));
    }
}