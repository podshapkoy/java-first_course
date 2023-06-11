package org.example;

import org.example.command.CollectionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ConsoleManager {
    private final CommandManager commandManager;
    private final CollectionManager collectionManager;
    private final BufferedReader reader;
    private final PrintWriter writer;

    public ConsoleManager(CommandManager commandManager, CollectionManager collectionManager,
                          BufferedReader reader, PrintWriter writer) {
        this.commandManager = commandManager;
        this.collectionManager = collectionManager;
        this.reader = reader;
        this.writer = writer;
    }

    public void interactiveMode() {
        try {
            writer.println("Добро пожаловать в программу сервера! Введите \"help\" для списка доступных команд.");
            writer.flush();

            while (true) {
                String input = reader.readLine();

                if (input == null || input.trim().isEmpty()) {
                    continue;
                }

                String[] parts = input.trim().split("\\s+", 2);
                String commandName = parts[0];

                try {
                    commandManager.executeCommand(input, writer);
                    reader.readLine();
                } catch (CollectionException e) {
                    writer.println(e.getMessage());
                }
                writer.flush();
            }
        } catch (IOException e) {
        }
    }

}