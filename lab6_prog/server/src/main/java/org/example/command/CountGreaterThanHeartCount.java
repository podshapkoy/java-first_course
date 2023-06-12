package org.example.command;

import org.example.CollectionManager;
import org.example.Description.SpaceMarine;

import java.io.PrintWriter;
import java.util.Collection;

public class CountGreaterThanHeartCount extends Command {
    private final CollectionManager collectionManager;

    public CountGreaterThanHeartCount(CollectionManager collectionManager) {
        super("count_greater_than_heart_count", "вывести количество элементов, значение поля heartCount которых больше заданного");
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute(String[] args) throws CollectionException {
        if (args.length == 0) {
            throw new CollectionException("Не указано значение поля heartCount");
        }

        int heartCount = Integer.parseInt(args[0]);
        Collection<SpaceMarine> collection = collectionManager.getSpaceMarineCollection();
        long count = collection.stream().filter(marine -> marine.getHeartCount() > heartCount).count();
        String result = "Количество элементов коллекции, у которых значение поля heartCount больше " + heartCount + ": " + count;
        System.out.println(result);
        return result;
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager, PrintWriter writer) throws CollectionException {
        if (args.length == 0) {
            throw new CollectionException("Не указано значение поля heartCount");
        }

        int heartCount = Integer.parseInt(args[0]);
        Collection<SpaceMarine> collection = collectionManager.getSpaceMarineCollection();
        long count = collection.stream().filter(marine -> marine.getHeartCount() > heartCount).count();
        String result = "Количество элементов коллекции, у которых значение поля heartCount больше " + heartCount + ": " + count;
        writer.println(result);
        return result;
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager) throws CollectionException {
        return execute(args);
    }
}
