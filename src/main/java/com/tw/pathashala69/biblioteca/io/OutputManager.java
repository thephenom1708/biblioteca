package com.tw.pathashala69.biblioteca.io;

import java.io.PrintStream;

//Job: Prints output to any output stream
public class OutputManager {

    public static void output(String outputMessage, PrintStream printStream) {
        printStream.println(outputMessage);
    }
}
