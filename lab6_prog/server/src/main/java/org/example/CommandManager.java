package org.example;

import org.example.command.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();
    private final CollectionManager collectionManager;
    private final Head headCommand;

    public CommandManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        this.headCommand = new Head(collectionManager);
        // регистрация команд
        commands.put("help", new Help(collectionManager, headCommand, null));
        commands.put("info", new Info(collectionManager));
        commands.put("show", new Show(collectionManager));
        commands.put("add", new Add(collectionManager));
        commands.put("update_id", new UpdateId(collectionManager));
        commands.put("remove_by_id", new RemoveById(collectionManager, headCommand));
        commands.put("clear", new Clear(collectionManager));
        commands.put("execute_script", new ExecuteScriptFileName(collectionManager));
        commands.put("exit", new Exit(collectionManager));
        commands.put("head", headCommand);
        commands.put("remove_greater", new RemoveGreater(collectionManager, new BufferedReader(new InputStreamReader(System.in))));
        commands.put("remove_lower", new RemoveLower(collectionManager, new BufferedReader(new InputStreamReader(System.in))));
        commands.put("remove_any_by_chapter", new RemoveAnyByChapter(collectionManager, new BufferedReader(new InputStreamReader(System.in))));
        commands.put("count_greater_than_heart_count", new CountGreaterThanHeartCount(collectionManager));
        commands.put("filter_contains_name", new FilterContainsName(collectionManager, new BufferedReader(new InputStreamReader(System.in))));
    }

    public void executeCommand(String commandLine, PrintWriter writer) throws CollectionException {
        String[] parts = commandLine.trim().split("\\s+", 2);
        String commandName = parts[0];

        Command command = commands.get(commandName);
        if (command == null) {
            throw new CollectionException("Команда \"" + commandName + "\" не найдена. Введите \"help\" для списка доступных команд.");
        }

        String[] args = parts.length > 1 ? parts[1].trim().split("\\s+") : new String[0];

        String result = command.execute(args, collectionManager);
        if (writer != null) {
            writer.println(result);
            writer.println("Введите следующую команду:");
            writer.flush();
        }
    }

}