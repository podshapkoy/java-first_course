package org.example.command;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RemoveById extends Command {
    public RemoveById() {
        super("remove_by_id", "удалить элемент из коллекции по его id");
    }

    @Override
    public void execute(String[] args, ObjectInputStream in, ObjectOutputStream out) throws CollectionException, IOException {
        // Проверка наличия всех необходимых аргументов команды
        if (args.length == 0) {
            throw new CollectionException("Не указан id элемента для удаления");
        }

        // Извлечение значения аргумента команды (id)
        long id = Long.parseLong(args[0]);

        // Отправка команды и аргумента на сервер
        out.writeObject("remove_by_id");
        out.writeObject(id);
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
