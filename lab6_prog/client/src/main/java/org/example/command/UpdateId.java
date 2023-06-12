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
        if (args.length == 0) {
            throw new CollectionException("Не указано значение id");
        }

        long oldId = Long.parseLong(args[0]);

        out.writeObject("update_id");
        out.writeLong(oldId);
        out.flush();

        try {
            String result = (String) in.readObject();
            System.out.println(result);
        } catch (ClassNotFoundException e) {
            throw new CollectionException("Ошибка при получении ответа от сервера");
        }
    }
}
