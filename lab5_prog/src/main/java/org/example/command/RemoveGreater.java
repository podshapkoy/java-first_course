package org.example.command;

import org.example.CollectionManager;
import org.example.Description.SpaceMarine;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayDeque;

public class RemoveGreater extends Command {
    private final CollectionManager collectionManager;
    private final BufferedReader reader;

    public RemoveGreater(CollectionManager collectionManager, BufferedReader reader) {
        super("remove_greater", "удалить из коллекции все элементы, превышающие заданный");
        this.collectionManager = collectionManager;
        this.reader = reader;
    }

    @Override
    public void execute(String[] args, CollectionManager collectionManager) throws CollectionException {
        try {
            if (args.length == 0) {
                System.out.print("Введите значение heartCount: ");
                String input = reader.readLine().trim();
                args = new String[]{input};
            }

            int heartCount = Integer.parseInt(args[0]);

            int removedCount = collectionManager.removeGreater(heartCount);

            if (removedCount > 0) {
                System.out.println("Из коллекции удалено " + removedCount + " элементов, больших, чем заданный heartCount.");
            } else {
                System.out.println("Нет элементов, больших, чем заданный heartCount.");
            }
        } catch (NumberFormatException e) {
            throw new CollectionException("Неверный формат heartCount: " + e.getMessage());
        } catch (IOException e) {
            throw new CollectionException("Ошибка чтения ввода: " + e.getMessage());
        }
    }

    @Override
    public void execute(String[] args) throws CollectionException {
        execute(args, collectionManager);
    }
}
