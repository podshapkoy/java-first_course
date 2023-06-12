package org.example.command;

import org.example.CollectionManager;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Help extends Command {
    private final Map<String, Command> commands;
    private final CollectionManager collectionManager;

    public Help(CollectionManager collectionManager, Head headCommand, String scriptFilename) {
        super("help", "вывести справку по доступным командам");
        this.collectionManager = collectionManager;
        this.commands = new HashMap<>();
        commands.put("help", this);
        commands.put("info", new Info(collectionManager));
        commands.put("show", new Show(collectionManager));
        commands.put("add", new Add(collectionManager));
        commands.put("update", new UpdateId(collectionManager));
        commands.put("remove_by_id", new RemoveById(collectionManager, headCommand));
        commands.put("clear", new Clear(collectionManager));
        commands.put("execute_script", new ExecuteScriptFileName(collectionManager));
        commands.put("exit", new Exit(collectionManager));
        commands.put("head", headCommand);
        commands.put("remove_greater", new RemoveGreater(collectionManager, null));
        commands.put("remove_lower", new RemoveLower(collectionManager,null));
        commands.put("remove_any_by_chapter", new RemoveAnyByChapter(collectionManager, null));
        commands.put("count_greater_than_heart_count", new CountGreaterThanHeartCount(collectionManager));
        commands.put("filter_contains_name", new FilterContainsName(collectionManager, null));
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager) throws CollectionException {
        StringBuilder result = new StringBuilder();
        for (Command command : commands.values()) {
            if (command != null) {
                result.append(command.getName()).append(" - ").append(command.getDescription()).append("\n");
            }
        }
        return result.toString().trim();
    }

    @Override
    public String execute(String[] args) throws CollectionException {
        return execute(args, null);
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager, PrintWriter writer) throws CollectionException {
        return execute(args, collectionManager);
    }
}
