package com.tw.pathashala69.biblioteca.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class OutputManagerTest {

    private ByteArrayOutputStream outStream;

    @BeforeEach
    public void setUp() {
        outStream = new ByteArrayOutputStream();
    }

    @Test
    public void shouldPrintOutputMessageOnGivenPrintStream() {
        System.setOut(new PrintStream(outStream));

        OutputManager.output("Output Message", System.out);
        System.setOut(new PrintStream(System.out));

        assertTrue(new String(outStream.toByteArray()).contains("Output Message"));
    }
}
