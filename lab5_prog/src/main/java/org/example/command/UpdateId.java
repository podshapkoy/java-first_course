package org.example.command;

import org.example.CollectionManager;
import org.example.Description.SpaceMarine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UpdateId extends Command {
    private final CollectionManager collectionManager;

    public UpdateId(CollectionManager collectionManager) {
        super("update_id", "обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws CollectionException {
        try {
            Integer oldId;
            if (args.length < 1 || args[0].equalsIgnoreCase("input")) {
                System.out.print("Введите текущий id элемента: ");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                oldId = Integer.parseInt(reader.readLine());
            } else {
                oldId = Integer.parseInt(args[0]);
            }

            SpaceMarine spaceMarine = collectionManager.getSpaceMarineById(Math.toIntExact(oldId));
            if (spaceMarine == null) {
                throw new CollectionException("Элемент с id " + oldId + " не найден в коллекции");
            }

            System.out.print("Введите новый id элемента: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            Integer newId = Integer.parseInt(reader.readLine());

            if (collectionManager.containsSpaceMarineId(Math.toIntExact(newId))) {
                throw new CollectionException("Элемент с id " + newId + " уже существует в коллекции");
            }

            collectionManager.updateSpaceMarineId(spaceMarine, Math.toIntExact(newId));

            System.out.println("Значение элемента коллекции с id " + oldId + " успешно обновлено на id " + newId);
        } catch (NumberFormatException e) {
            throw new CollectionException("Неверный формат id элемента: " + args[0]);
        } catch (IOException e) {
            throw new CollectionException("Ошибка чтения id элемента: " + e.getMessage());
        }
    }

    @Override
    public void execute(String[] args, CollectionManager collectionManager) throws CollectionException {
        execute(args);
    }
}
