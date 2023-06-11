package org.example.command;

import org.example.CollectionManager;

public class Unknown extends Command {
    public Unknown() {
        super("unknown", "Неизвестная команда");
    }

    @Override
    public void execute(String[] args) throws CollectionException {
        System.out.println("Неизвестная команда. Используйте команду help для получения списка доступных команд.");
    }

    @Override
    public void execute(String[] args, CollectionManager collectionManager) throws CollectionException {
        execute(args);
    }
}

