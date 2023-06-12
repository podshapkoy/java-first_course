package org.example.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CountGreaterThanHeartCount extends Command {
    private final BufferedReader reader;

    public CountGreaterThanHeartCount(BufferedReader reader) {
        super("count_greater_than_heart_count", "подсчитать количество элементов, значение поля heartCount которых больше заданного");
        this.reader = reader;
    }

    @Override
    public void execute(String[] args, ObjectInputStream in, ObjectOutputStream out) throws CollectionException, IOException {

        if (args.length == 0) {
            throw new CollectionException("Не указано значение поля heartCount");
        }

        int heartCount = Integer.parseInt(args[0]);

        out.writeObject("count_greater_than_heart_count");
        out.writeInt(heartCount);
        out.flush();
        try {
            String response = (String) in.readObject();
            System.out.println(response);
        } catch (NumberFormatException e) {
            throw new CollectionException("Неверный формат heartCount: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new CollectionException("Ошибка при чтении ответа от сервера: " + e.getMessage());
        }
    }
}
