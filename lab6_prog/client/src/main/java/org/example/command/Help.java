package org.example.command;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Help extends Command {
    private final Map<String, Command> commands;

    public Help() {
        super("help", "вывести справку по доступным командам");
        this.commands = new HashMap<>();
        // Добавьте здесь команды, которые клиент может вызвать
    }

    @Override
    public void execute(String[] args, ObjectInputStream in, ObjectOutputStream out) throws CollectionException, IOException {
        out.writeObject("help");
        out.flush();

        try {
            String result = (String) in.readObject();
            System.out.println(result);
        } catch (ClassNotFoundException e) {
            throw new CollectionException("Ошибка при получении ответа от сервера");
        }
    }
}
