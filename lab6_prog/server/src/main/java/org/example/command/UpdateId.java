package org.example.command;

import org.example.CollectionManager;
import org.example.Description.SpaceMarine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class UpdateId extends Command {
    private final CollectionManager collectionManager;

    public UpdateId(CollectionManager collectionManager) {
        super("update_id", "обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute(String[] args) throws CollectionException {
        try {
            long oldId;
            if (args.length < 1 || args[0].equalsIgnoreCase("input")) {
                // Запрос текущего id элемента у клиента
                sendRequest("Введите текущий id элемента: ");
                oldId = Long.parseLong(receiveResponse());
            } else {
                oldId = Long.parseLong(args[0]);
            }

            SpaceMarine spaceMarine = collectionManager.getSpaceMarineById(Math.toIntExact(oldId));
            if (spaceMarine == null) {
                throw new CollectionException("Элемент с id " + oldId + " не найден в коллекции");
            }

            sendRequest("Введите новый id элемента: ");
            long newId = Long.parseLong(receiveResponse());

            if (collectionManager.containsSpaceMarineId(Math.toIntExact(newId))) {
                throw new CollectionException("Элемент с id " + newId + " уже существует в коллекции");
            }

            collectionManager.updateSpaceMarineId(spaceMarine, Math.toIntExact(newId));

            sendMessageToClient("Значение элемента коллекции с id " + oldId + " успешно обновлено на id " + newId);

            return "Значение элемента коллекции успешно обновлено";
        } catch (NumberFormatException e) {
            throw new CollectionException("Неверный формат id элемента: " + args[0]);
        } catch (IOException e) {
            throw new CollectionException("Ошибка чтения id элемента: " + e.getMessage());
        }
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager, PrintWriter writer) throws CollectionException {
        return execute(args);
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager) throws CollectionException {
        return execute(args);
    }

    private void sendRequest(String message) throws IOException {
        System.out.print(message);
    }

    private String receiveResponse() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    private void sendMessageToClient(String message) {
        System.out.println(message);
    }
}
