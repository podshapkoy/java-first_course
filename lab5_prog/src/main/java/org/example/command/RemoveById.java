package org.example.command;

import org.example.CollectionManager;
import org.example.Description.SpaceMarine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RemoveById extends Command {
    private final CollectionManager collectionManager;
    private final Head headCommand;

    public RemoveById(CollectionManager collectionManager, Head headCommand) {
        super("remove_by_id", "удалить элемент из коллекции по его id");
        this.collectionManager = collectionManager;
        this.headCommand = headCommand;
    }

    @Override
    public void execute(String[] args, CollectionManager collectionManager) throws CollectionException {
        try {
            if (args.length == 0) {
                System.out.print("Введите id элемента для удаления: ");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String input = reader.readLine().trim();
                args = input.split("\\s+");
            }

            long id = Long.parseLong(args[0]);
            Integer idAsInteger = (int) id; // Преобразование Long в Integer
            SpaceMarine removedSpaceMarine = collectionManager.removeById(idAsInteger);
            if (removedSpaceMarine != null) {
                headCommand.updateFirstElement(); // Обновление headCommand после удаления элемента
                System.out.println("Элемент с id " + id + " был успешно удален из коллекции.");
            } else {
                System.out.println("Элемент с id " + id + " не найден в коллекции.");
            }
        } catch (NumberFormatException e) {
            throw new CollectionException("Неверный формат id: " + e.getMessage());
        } catch (IOException e) {
            throw new CollectionException("Ошибка чтения ввода: " + e.getMessage());
        }
    }

    @Override
    public void execute(String[] args) throws CollectionException {
        execute(args, collectionManager);
    }
}
