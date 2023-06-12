package org.example;

import org.example.command.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Менеджер команд - управляет выполнением команд и взаимодействием с пользователем.
 */
public class CommandManager implements Runnable {
    private final Map<String, Command> commands = new HashMap<>();
    private final CollectionManager collectionManager;
    private final Head headCommand;

    /**
     * Создает новый объект менеджера команд.
     *
     * @param collectionManager менеджер коллекции
     */
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
        commands.put("save", new Save(collectionManager));
        commands.put("execute_script", new ExecuteScriptFileName(collectionManager));
        commands.put("exit", new Exit());
        commands.put("head", headCommand);
        commands.put("remove_greater", new RemoveGreater(collectionManager, new BufferedReader(new InputStreamReader(System.in))));
        commands.put("remove_lower", new RemoveLower(collectionManager, new BufferedReader(new InputStreamReader(System.in))));
        commands.put("remove_any_by_chapter", new RemoveAnyByChapter(collectionManager, new BufferedReader(new InputStreamReader(System.in))));
        commands.put("count_greater_than_heart_count", new CountGreaterThanHeartCount(collectionManager));
        commands.put("filter_contains_name", new FilterContainsName(collectionManager, new BufferedReader(new InputStreamReader(System.in))));
    }

    /**
     * Возвращает объект команды по заданному имени.
     *
     * @param name имя команды
     * @return объект команды
     * @throws CommandNotFoundException если команда не найдена
     */
    public Command getCommand(String name) throws CommandNotFoundException {
        Command command = commands.get(name);
        if (command == null) {
            throw new CommandNotFoundException("Команда не найдена, попробуйте снова");
        }
        return command;
    }

    /**
     * Парсит командную строку и вызывает соответствующую команду.
     *
     * @param commandLine командная строка
     * @throws CollectionException при ошибке во время выполнения команды
     */
    public void executeCommand(String commandLine) throws CollectionException {
        String[] parts = commandLine.trim().split("\\s+", 2);
        String commandName = parts[0];

        Command command = commands.get(commandName);
        if (command == null) {
            throw new CollectionException("Команда \"" + commandName + "\" не найдена. Введите \"help\" для списка доступных команд.");
        }

        String[] args = parts.length > 1 ? parts[1].trim().split("\\s+") : new String[0];
        command.execute(args, collectionManager);
    }

    @Override
    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("> ");
                String commandLine = scanner.nextLine();
                try {
                    executeCommand(commandLine);
                } catch (CollectionException e) {
                    System.err.println("Ошибка выполнения команды: " + e.getMessage());
                }
            }
        }
    }
}
