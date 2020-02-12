package com.tw.pathashala69.biblioteca.io;

import java.io.InputStream;
import java.util.Scanner;

//Job: Takes input from any input stream
public class InputManager {

    public static String input(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        return scanner.nextLine().trim();
    }
}
