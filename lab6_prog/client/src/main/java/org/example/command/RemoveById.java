package org.example.command;

import java.io.*;

public class RemoveById extends Command {
    public RemoveById() {
        super("remove_by_id", "удалить элемент из коллекции по его id");
    }

    @Override
    public void execute(String[] args, ObjectInputStream in, ObjectOutputStream out) throws CollectionException, IOException {
        if (args.length == 0) {
            System.out.print("Введите id элемента для удаления: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = reader.readLine().trim();
            args = input.split("\\s+");
        }
        long id = Long.parseLong(args[0]);

        out.writeObject("remove_by_id");
        out.writeLong(id);
        out.flush();

        try {
            String result = (String) in.readObject();
            System.out.println(result);
        } catch (ClassNotFoundException e) {
            throw new CollectionException("Ошибка при получении ответа от сервера");
        }
    }
}