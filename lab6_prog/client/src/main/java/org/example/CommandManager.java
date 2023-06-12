package org.example;

import org.example.command.CollectionException;

import java.io.PrintWriter;
import java.util.ArrayList;

public class CommandManager {
    private ArrayList<String> commandsMap = new ArrayList<>();
    private ArrayList<String> commandsExtraData = new ArrayList<>();
    private ArrayList<String> commandsExtraModel = new ArrayList<>();
    private CommandFactory commandFactory;

    public CommandManager(String scriptFilename) {
        makeCollection(scriptFilename);
    }

    private void makeCollection(String scriptFilename) {
        commandFactory = new CommandFactory(scriptFilename);
        commandsMap.add("help");
        commandsMap.add("info");
        commandsMap.add("show");
        commandsMap.add("add");
        commandsMap.add("update_id");
        commandsMap.add("remove_by_id");
        commandsMap.add("clear");
        commandsMap.add("execute_script");
        commandsMap.add("exit");
        commandsMap.add("head");
        commandsMap.add("remove_greater");
        commandsMap.add("remove_lower");
        commandsMap.add("remove_any_by_chapter");
        commandsMap.add("count_greater_than_heart_count");
        commandsMap.add("filter_contains_name");

        commandsExtraModel.add("update_id");
        commandsExtraModel.add("add");
    }

    public void executeCommand(String commandLine, PrintWriter writer) throws CollectionException {
        String[] parts = commandLine.trim().split("\\s+", 2);
        String commandName = parts[0];
        // Обработка команд с дополнительными данными и моделями
        if (commandsExtraModel.contains(commandName)) {
            writer.println("Выполняется команда с моделью: " + commandName);
        } else {
            writer.println("Выполняется команда: " + commandName);
        }
    }
}
