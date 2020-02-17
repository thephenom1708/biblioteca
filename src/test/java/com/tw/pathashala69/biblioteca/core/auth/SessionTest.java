package com.tw.pathashala69.biblioteca.core.auth;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SessionTest {

    @Test
    public void shouldStartSessionForUser() {
        User user = mock(User.class);
        Session session = new Session(user);

        assertThat(session.user(), is(equalTo(user)));
    }

    @Test
    public void shouldSetUserAsGUESTWhenSessionIsDestroyed() {
        User expectedUser = mock(User.class);
        when(expectedUser.privilege()).thenReturn(UserPrivilege.GUEST);
        Session session = new Session(mock(User.class));

        session.destroy();

        assertThat(session.user().privilege(), is(equalTo(expectedUser.privilege())));
    }
}