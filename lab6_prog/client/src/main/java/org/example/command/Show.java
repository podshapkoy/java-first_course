package org.example.command;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Show extends Command {
    public Show() {
        super("show", "вывести все элементы коллекции");
    }

    @Override
    public void execute(String[] args, ObjectInputStream in, ObjectOutputStream out) throws CollectionException, IOException {
        // Отправляем запрос на сервер с указанием команды "show"
        out.writeObject("show");
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
