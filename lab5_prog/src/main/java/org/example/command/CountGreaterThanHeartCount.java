package org.example.command;

import org.example.CollectionManager;
import org.example.Description.SpaceMarine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

public class CountGreaterThanHeartCount extends Command {
    private CollectionManager collectionManager;

    public CountGreaterThanHeartCount(CollectionManager collectionManager) {
        super("count_greater_than_heart_count", "вывести количество элементов, значение поля heartCount которых больше заданного");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args, CollectionManager collectionManager) throws CollectionException {
        if (args.length == 0) {
            System.out.print("Введите значение поля heartCount: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String input = reader.readLine().trim();
                args = new String[]{input};
            } catch (IOException e) {
                throw new CollectionException("Ошибка чтения ввода: " + e.getMessage());
            }
        }

        int heartCount = Integer.parseInt(args[0]);
        Collection<SpaceMarine> collection = collectionManager.getSpaceMarineCollection();
        long count = collection.stream().filter(marine -> marine.getHeartCount() > heartCount).count();
        System.out.println("Количество элементов коллекции, у которых значение поля heartCount больше " + heartCount + ": " + count);
    }


    @Override
    public void execute(String[] args) throws CollectionException {
        execute(args, collectionManager);
    }
}
