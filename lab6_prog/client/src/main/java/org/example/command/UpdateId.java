package org.example.command;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UpdateId extends Command {
    public UpdateId() {
        super("update_id", "обновить значение элемента коллекции, id которого равен заданному");
    }

    @Override
    public void execute(String[] args, ObjectInputStream in, ObjectOutputStream out) throws CollectionException, IOException {
        // Проверка наличия всех необходимых аргументов команды
        if (args.length < 1) {
            throw new CollectionException("Недостаточно аргументов для выполнения команды");
        }

        // Извлечение значения аргумента команды
        long oldId = Long.parseLong(args[0]);

        // Отправка сообщения о запросе ввода нового id от клиента к серверу
        out.writeObject("update_id");
        out.flush();

        // Отправка текущего id от клиента к серверу
        out.writeLong(oldId);
        out.flush();

        try {
            // Получение ответа от сервера
            String result = (String) in.readObject();
            System.out.println(result);
        } catch (ClassNotFoundException e) {
            throw new CollectionException("Ошибка при получении ответа от сервера");
        }
    }
}
