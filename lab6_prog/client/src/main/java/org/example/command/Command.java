package org.example.command;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class Command {
    private final String name;
    private final String description; // описание команды

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
    public abstract void execute(String[] args, ObjectInputStream in, ObjectOutputStream out) throws CollectionException, IOException;
}
