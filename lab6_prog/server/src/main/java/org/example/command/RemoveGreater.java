package org.example.command;

import org.example.CollectionManager;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class RemoveGreater extends Command {
    private final CollectionManager collectionManager;
    private final BufferedReader reader;

    public RemoveGreater(CollectionManager collectionManager, BufferedReader reader) {
        super("remove_greater", "удалить из коллекции все элементы, превышающие заданный");
        this.collectionManager = collectionManager;
        this.reader = reader;
    }

    @Override
    public String execute(String[] args) throws CollectionException {
        try {
            if (args.length == 0) {
                throw new CollectionException("Не указано значение heartCount");
            }

            int heartCount = Integer.parseInt(args[0]);

            int removedCount = collectionManager.removeGreater(heartCount);

            if (removedCount > 0) {
                System.out.println("Из коллекции удалено " + removedCount + " элементов, больших, чем заданный heartCount.");
                return "Из коллекции удалено " + removedCount + " элементов, больших, чем заданный heartCount.";
            } else {
                System.out.println("Нет элементов, больших, чем заданный heartCount.");
                return "Нет элементов, больших, чем заданный heartCount.";
            }
        } catch (NumberFormatException e) {
            throw new CollectionException("Неверный формат heartCount: " + e.getMessage());
        }
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager, PrintWriter writer) throws CollectionException {
        try {
            if (args.length == 0) {
                throw new CollectionException("Не указано значение heartCount");
            }

            int heartCount = Integer.parseInt(args[0]);

            int removedCount = collectionManager.removeGreater(heartCount);

            if (removedCount > 0) {
                writer.println("Из коллекции удалено " + removedCount + " элементов, больших, чем заданный heartCount.");
                return "Из коллекции удалено " + removedCount + " элементов, больших, чем заданный heartCount.";
            } else {
                writer.println("Нет элементов, больших, чем заданный heartCount.");
                return "Нет элементов, больших, чем заданный heartCount.";
            }
        } catch (NumberFormatException e) {
            throw new CollectionException("Неверный формат heartCount: " + e.getMessage());
        }
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager) throws CollectionException {
        return execute(args);
    }
}
