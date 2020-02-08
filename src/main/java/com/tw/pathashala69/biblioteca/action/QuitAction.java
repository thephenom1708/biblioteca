package com.tw.pathashala69.biblioteca.action;

//Job: Quit the application
public class QuitAction implements Action{
    @Override
    public void perform() {
        System.exit(0);
    }
}
