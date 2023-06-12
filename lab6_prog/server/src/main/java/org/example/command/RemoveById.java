package org.example.command;

import org.example.CollectionManager;
import org.example.Description.SpaceMarine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class RemoveById extends Command {
    private final CollectionManager collectionManager;
    private final Head headCommand;

    public RemoveById(CollectionManager collectionManager, Head headCommand) {
        super("remove_by_id", "удалить элемент из коллекции по его id");
        this.collectionManager = collectionManager;
        this.headCommand = headCommand;
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager) throws CollectionException {
        try {
            if (args.length == 0) {
                return "Введите id элемента для удаления";
            }
            long id = Long.parseLong(args[0]);
            Integer idAsInteger = (int) id; // Преобразование Long в Integer
            SpaceMarine removedSpaceMarine = collectionManager.removeById(idAsInteger);
            if (removedSpaceMarine != null) {
                headCommand.updateFirstElement(); // Обновление headCommand после удаления элемента
                System.out.println("Элемент с id " + id + " был успешно удален из коллекции.");
                return "Элемент успешно удален.";
            } else {
                System.out.println("Элемент с id " + id + " не найден в коллекции.");
                return "Элемент не найден.";
            }
        } catch (NumberFormatException e) {
            throw new CollectionException("Неверный формат id: " + e.getMessage());
        }
    }
    @Override
    public String execute(String[] args, CollectionManager collectionManager, PrintWriter writer) throws CollectionException {
        return execute(args);
    }
    @Override
    public String execute(String[] args) throws CollectionException {
        throw new UnsupportedOperationException("Метод execute(String[]) не поддерживается на сервере");
    }

}