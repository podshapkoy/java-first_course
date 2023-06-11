package org.example.command;

import org.example.CollectionManager;
import org.example.CommandFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteScriptFileName extends Command {
    private final CollectionManager collectionManager;
    private final String scriptFilename;

    public ExecuteScriptFileName(CollectionManager collectionManager) {
        super("execute_script", "считать и исполнить скрипт из указанного файла");
        this.collectionManager = collectionManager;
        this.scriptFilename = "C:\\Users\\User\\Desktop\\prog\\lab5_fixed\\lab5_prog\\script.txt";
    }

    @Override
    public void execute(String[] args) throws CollectionException {
        if (scriptFilename == null) {
            throw new CollectionException("Не задано имя файла со скриптом");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(scriptFilename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] commandParts = line.trim().split("\\s+");
                if (commandParts.length > 0) {
                    Command command = CommandFactory.createCommand(commandParts[0], collectionManager, null, scriptFilename);
                    if (command != null) {
                        String[] commandArgs = new String[commandParts.length - 1];
                        System.arraycopy(commandParts, 1, commandArgs, 0, commandArgs.length);
                        command.execute(commandArgs, collectionManager);
                    } else {
                        System.err.println("Неизвестная команда: " + commandParts[0]);
                    }
                }
            }
        } catch (IOException e) {
            throw new CollectionException("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    @Override
    public void execute(String[] args, CollectionManager collectionManager) throws CollectionException {
        execute(args);
    }
}
