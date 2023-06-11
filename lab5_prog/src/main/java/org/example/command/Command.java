package org.example.command;

import org.example.CollectionManager;

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
    public abstract void execute(String[] args, CollectionManager collectionManager) throws CollectionException;

    public abstract void execute(String[] args) throws CollectionException;
}
