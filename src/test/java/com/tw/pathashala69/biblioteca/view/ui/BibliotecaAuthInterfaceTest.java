package com.tw.pathashala69.biblioteca.view.ui;

import com.tw.pathashala69.biblioteca.view.constants.Message;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BibliotecaAuthInterfaceTest {

    private PrintStream oldOutStream;
    private InputStream oldInputStream;
    private ByteArrayInputStream inStream;
    private ByteArrayOutputStream outStream;
    private BibliotecaAuthInterface authInterface;

    BibliotecaAuthInterfaceTest() {}

    @BeforeEach
    void setUp() {
        oldOutStream = new PrintStream(System.out);
        oldInputStream = System.in;

        String data = "123-4567\npassword";
        inStream = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
        System.setIn(inStream);

        outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));


        authInterface = new BibliotecaAuthInterface();
    }

    @AfterEach
    void tearDown() {
        System.setIn(oldInputStream);
        System.setOut(oldOutStream);
    }

    @Test
    public void shouldPrintPromptMessageForLibraryNumber() {
        authInterface.promptForLoginCredentials();

        assertTrue(new String(outStream.toByteArray()).contains(Message.LIBRARY_NUMBER_PROMPT_MESSAGE));
    }

    @Test
    public void shouldPrintPromptMessageForPassword() {
        authInterface.promptForLoginCredentials();

        assertTrue(new String(outStream.toByteArray()).contains(Message.PASSWORD_PROMPT_MESSAGE));
    }

    @Test
    public void shouldReturnCredentialsInputFromConsole() {
        String[] expectedCredentials = {"123-4567", "password"};
        String[] actualCredentials = authInterface.promptForLoginCredentials();

        assertThat(expectedCredentials, is(equalTo(actualCredentials)));
    }

    @Test
    public void shouldDisplayMessageOnInvalidLoginCredentials() {
        authInterface.onInvalidLoginCredentials();

        assertTrue(new String(outStream.toByteArray()).contains(Message.INVALID_CREDENTIALS_MESSAGE));
    }

    @Test
    public void shouldDisplayMessageOnUserAlreadyLoggedIn() {
        authInterface.onUserAlreadyLoggedIn();

        assertTrue(new String(outStream.toByteArray()).contains(Message.ALREADY_LOGGED_IN_MESSAGE));
    }
}