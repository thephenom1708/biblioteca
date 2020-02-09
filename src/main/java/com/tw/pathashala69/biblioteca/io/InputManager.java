package com.tw.pathashala69.biblioteca.io;

import com.tw.pathashala69.biblioteca.constants.Message;

import java.io.InputStream;
import java.util.Scanner;

public class InputManager {

    public static String input(InputStream inputStream, String inputMessage) {
        Scanner scanner = new Scanner(inputStream);
        System.out.print(Message.ENTER_INPUT_MESSAGE + " " + inputMessage + ": ");
        return scanner.nextLine().trim();
    }
}
