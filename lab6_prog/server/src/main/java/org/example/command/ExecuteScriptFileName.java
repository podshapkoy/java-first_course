package org.example.command;

import org.example.CollectionManager;
import org.example.CommandFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ExecuteScriptFileName extends Command {
    private final CollectionManager collectionManager;
    private final String scriptFilename;

    public ExecuteScriptFileName(CollectionManager collectionManager) {
        super("execute_script", "считать и исполнить скрипт из указанного файла");
        this.collectionManager = collectionManager;
        this.scriptFilename = "C:\\Users\\User\\Desktop\\prog\\lab5_fixed\\lab6\\server\\script.txt";
    }

    @Override
    public String execute(String[] args) throws CollectionException {
        if (scriptFilename == null) {
            throw new CollectionException("Не задано имя файла со скриптом");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(scriptFilename))) {
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] commandParts = line.trim().split("\\s+");
                if (commandParts.length > 0) {
                    Command command = CommandFactory.createCommand(commandParts[0], collectionManager, null, scriptFilename);
                    if (command != null) {
                        String[] commandArgs = new String[commandParts.length - 1];
                        System.arraycopy(commandParts, 1, commandArgs, 0, commandArgs.length);
                        String commandOutput = command.execute(commandArgs, collectionManager);
                        output.append(commandOutput).append("\n");
                    } else {
                        output.append("Неизвестная команда: ").append(commandParts[0]).append("\n");
                    }
                }
            }
            return output.toString();
        } catch (IOException e) {
            throw new CollectionException("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager, PrintWriter writer) throws CollectionException {
        return execute(args);
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager) throws CollectionException {
        return execute(args);
    }
}
