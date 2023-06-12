package org.example.command;

import org.example.CollectionManager;

import java.io.PrintWriter;

public abstract class Command {
    private String name;
    private String description; // описание команды

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Абстрактный метод, который будет реализован в каждой команде
    public abstract String execute(String[] args, CollectionManager collectionManager) throws CollectionException;

    public abstract String execute(String[] args) throws CollectionException;

    // Дополнительный метод для серверной части
    public abstract String execute(String[] args, CollectionManager collectionManager, PrintWriter writer) throws CollectionException;

}
