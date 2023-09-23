package dev.steadypim;

import dev.steadypim.messageRestorer.MessageRestorer;

public class App {
    public static void main(String[] args) {
        MessageRestorer messageRestorer = new MessageRestorer();
        messageRestorer.restoreMessages();
    }
}