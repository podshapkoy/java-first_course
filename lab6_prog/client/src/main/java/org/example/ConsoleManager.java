package org.example;

import org.example.command.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ConsoleManager {
    private final CommandManager commandManager;
    private final BufferedReader reader;
    private final PrintWriter writer;

    public ConsoleManager(CommandManager commandManager, BufferedReader reader, PrintWriter writer) {
        this.commandManager = commandManager;
        this.reader = reader;
        this.writer = writer;
    }

    public void interactiveMode() throws IOException {
        String userInput;

        while ((userInput = reader.readLine()) != null) {
            writer.println(userInput);
            writer.flush();

            if (userInput.equals("exit")) {
                break;
            }
        }

    }
}