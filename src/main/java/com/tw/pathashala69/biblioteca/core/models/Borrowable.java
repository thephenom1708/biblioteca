package com.tw.pathashala69.biblioteca.core.models;

import java.io.PrintStream;

public interface Borrowable {
    String title();

    void print(PrintStream stream);
}
