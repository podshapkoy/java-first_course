package org.example;

import org.example.command.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleManager {
    private final CommandManager commandManager;
    private final BufferedReader reader;
    private final CollectionManager collectionManager;

    public ConsoleManager(CommandManager commandManager, CollectionManager collectionManager) {
        this.commandManager = commandManager;
        this.collectionManager = collectionManager;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Запускает интерактивный режим работы с программой.
     */
    public void interactiveMode() {
        System.out.println("Добро пожаловать в программу! Введите \"help\" для списка доступных команд.");
        while (true) {
            System.out.print("> ");
            String input;
            try {
                input = reader.readLine().trim();
            } catch (IOException e) {
                System.out.println("Ошибка при чтении ввода: " + e.getMessage());
                continue;
            }

            if (input.isEmpty()) {
                continue;
            }

            String[] parts = input.split("\\s+");
            String commandName = parts[0];

            if (commandName.equals("add")) {
                try {
                    Command command = commandManager.getCommand(commandName);
                    String[] arguments = new String[parts.length - 1];
                    System.arraycopy(parts, 1, arguments, 0, arguments.length);
                    command.execute(arguments, collectionManager);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    break;
                }
            } else {
                try {
                    Command command = commandManager.getCommand(commandName);
                    String[] arguments = new String[parts.length - 1];
                    System.arraycopy(parts, 1, arguments, 0, arguments.length);
                    command.execute(arguments, collectionManager);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
