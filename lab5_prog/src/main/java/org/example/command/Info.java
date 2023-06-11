package org.example.command;

import org.example.CollectionManager;

public class Info extends Command {
    private CollectionManager collectionManager;

    public Info(CollectionManager collectionManager) {
        super("info", "вывести информацию о коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args, CollectionManager collectionManager) {
        System.out.println("Тип коллекции: " + collectionManager.getCollection().getClass().getName());
        System.out.println("Количество элементов в коллекции: " + collectionManager.getCollection().size());
        System.out.println("Дата инициализации коллекции: " + collectionManager.getInitializationDate());
    }

    @Override
    public void execute(String[] args) {
        execute(args, collectionManager);
    }
}
