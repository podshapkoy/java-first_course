package org.example.command;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Exit extends Command {

    public Exit() {
        super("exit", "завершить программу");
    }

    @Override
    public void execute(String[] args, ObjectInputStream in, ObjectOutputStream out) throws CollectionException, IOException {
        out.writeObject("exit");
        out.flush();

        try {
            String result = (String) in.readObject();
            System.out.println(result);
        } catch (ClassNotFoundException e) {
            throw new CollectionException("Ошибка при получении ответа от сервера");
        }
    }
}

