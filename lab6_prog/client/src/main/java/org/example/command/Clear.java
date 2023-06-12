package org.example.command;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Команда "clear" - очищает коллекцию.
 */
public class Clear extends Command {

    public Clear() {
        super("clear", "очистить коллекцию");
    }

    @Override
    public void execute(String[] args, ObjectInputStream in, ObjectOutputStream out) throws CollectionException, IOException {
        out.writeObject("clear");
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
