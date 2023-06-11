package org.example.command;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Info extends Command {
    public Info() {
        super("info", "вывести информацию о коллекции");
    }

    @Override
    public void execute(String[] args, ObjectInputStream in, ObjectOutputStream out) throws CollectionException, IOException {
        // Отправляем запрос на сервер с указанием команды "info"
        out.writeObject("info");
        out.flush();

        try {
            // Получаем ответ от сервера
            String result = (String) in.readObject();
            System.out.println(result);
        } catch (ClassNotFoundException e) {
            throw new CollectionException("Ошибка при получении ответа от сервера");
        }
    }
}
