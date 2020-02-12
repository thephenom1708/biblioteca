package com.tw.pathashala69.biblioteca.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class InputManagerTest {

    private ByteArrayInputStream inStream;

    @BeforeEach
    void setUp() {
        String data = "ABCD";
        inStream = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
    }

    @Test
    public void ShouldReturnUserInputAsString() {
        System.setIn(inStream);

        String actualInput = InputManager.input(System.in);
        System.setIn(System.in);

        assertTrue(actualInput.contains("ABCD"));
    }
}