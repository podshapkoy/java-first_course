package org.example.command;

import org.example.CommandFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ExecuteScriptFileName extends Command {
    private final String scriptFilename;

    public ExecuteScriptFileName(String scriptFilename) {
        super("execute_script", "считать и исполнить скрипт из указанного файла");
        this.scriptFilename = scriptFilename;
    }

    @Override
    public void execute(String[] args, ObjectInputStream in, ObjectOutputStream out) throws CollectionException, IOException {
        if (scriptFilename == null) {
            throw new CollectionException("Не задано имя файла со скриптом");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(scriptFilename))) {
            String line;
            CommandFactory commandFactory = new CommandFactory(scriptFilename); // Создание экземпляра CommandFactory
            while ((line = reader.readLine()) != null) {
                String[] commandParts = line.trim().split("\\s+");
                if (commandParts.length > 0) {
                    Command command = commandFactory.createCommand(commandParts[0], null); // Использование метода createCommand у экземпляра CommandFactory
                    if (command != null) {
                        String[] commandArgs = new String[commandParts.length - 1];
                        System.arraycopy(commandParts, 1, commandArgs, 0, commandArgs.length);
                        command.execute(commandArgs, in, out); // Передача необходимых параметров
                    } else {
                        System.out.println("Неизвестная команда: " + commandParts[0]);
                    }
                }
            }
        } catch (IOException e) {
            throw new CollectionException("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}
