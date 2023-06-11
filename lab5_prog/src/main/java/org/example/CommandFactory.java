package org.example;

import org.example.command.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<String, Command> commandMap;
    private Head headCommand;

    public CommandFactory(CollectionManager collectionManager, String scriptFilename) {
        commandMap = new HashMap<>();
        headCommand = new Head(collectionManager); // создание экземпляра Head после инициализации в конструкторе
        commandMap.put("help", new Help(collectionManager, headCommand, scriptFilename));
        commandMap.put("info", new Info(collectionManager));
        commandMap.put("show", new Show(collectionManager));
        commandMap.put("add", new Add(collectionManager));
        commandMap.put("update_id", new UpdateId(collectionManager));
        commandMap.put("remove_by_id", new RemoveById(collectionManager, headCommand));
        commandMap.put("clear", new Clear(collectionManager));
        commandMap.put("save", new Save(collectionManager));
        commandMap.put("execute_script", new ExecuteScriptFileName(collectionManager));
        commandMap.put("exit", new Exit());
        commandMap.put("head", headCommand);
        commandMap.put("remove_greater", new RemoveGreater(collectionManager, new BufferedReader(new InputStreamReader(System.in))));
        commandMap.put("remove_lower", new RemoveLower(collectionManager, new BufferedReader(new InputStreamReader(System.in))));
        commandMap.put("remove_any_by_chapter", new RemoveAnyByChapter(collectionManager, new BufferedReader(new InputStreamReader(System.in))));
        commandMap.put("count_greater_than_heart_count", new CountGreaterThanHeartCount(collectionManager));
        commandMap.put("filter_contains_name", new FilterContainsName(collectionManager, new BufferedReader(new InputStreamReader(System.in))));
    }

    public static Command createCommand(String commandPart, CollectionManager collectionManager, Head headCommand, String scriptFilename) {
        CommandFactory commandFactory = new CommandFactory(collectionManager, scriptFilename);
        return commandFactory.commandMap.getOrDefault(commandPart, new Unknown());
    }
}
