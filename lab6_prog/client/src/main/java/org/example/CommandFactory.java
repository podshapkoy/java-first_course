package org.example;

import org.example.command.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private Map<String, Command> commandMap;

    public CommandFactory(String scriptFilename) {
        commandMap = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // регистрация команд
        commandMap.put("help", new Help());
        commandMap.put("info", new Info());
        commandMap.put("show", new Show());
        commandMap.put("add", new Add());
        commandMap.put("update_id", new UpdateId());
        commandMap.put("remove_by_id", new RemoveById());
        commandMap.put("clear", new Clear());
        commandMap.put("execute_script", new ExecuteScriptFileName(scriptFilename));
        commandMap.put("exit", new Exit());
        commandMap.put("head", new Head());
        commandMap.put("remove_greater", new RemoveGreater(reader));
        commandMap.put("remove_lower", new RemoveLower(reader));
        commandMap.put("remove_any_by_chapter", new RemoveAnyByChapter(reader));
        commandMap.put("count_greater_than_heart_count", new CountGreaterThanHeartCount(reader));
        commandMap.put("filter_contains_name", new FilterContainsName(reader));
    }

    public Command createCommand(String commandPart, Object o) throws IllegalArgumentException {
        Command command = commandMap.get(commandPart);
        if (command == null) {
            throw new IllegalArgumentException("Неизвестная команда: " + commandPart);
        }
        return command;
    }
}
