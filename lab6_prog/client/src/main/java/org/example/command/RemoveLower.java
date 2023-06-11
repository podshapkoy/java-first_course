package org.example.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RemoveLower extends Command {
    private final BufferedReader reader;

    public RemoveLower(BufferedReader reader) {
        super("remove_lower", "удалить из коллекции все элементы, меньшие, чем заданный");
        this.reader = reader;
    }

    @Override
    public void execute(String[] args, ObjectInputStream in, ObjectOutputStream out) throws CollectionException, IOException {
        try {
            if (args.length == 0) {
                throw new CollectionException("Не указано значение heartCount");
            }

            int heartCount = Integer.parseInt(args[0]);

            out.writeObject("remove_lower");
            out.writeObject(args);
            out.flush();

            String response = (String) in.readObject();
            System.out.println(response);
        } catch (NumberFormatException e) {
            throw new CollectionException("Неверный формат heartCount: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new CollectionException("Ошибка при чтении ответа от сервера: " + e.getMessage());
        }
    }
}
