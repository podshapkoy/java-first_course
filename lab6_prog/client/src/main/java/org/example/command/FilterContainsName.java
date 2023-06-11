package org.example.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.LocalDateTimeAdapter;

import java.io.*;
import java.time.LocalDateTime;

public class FilterContainsName extends Command {
    private final BufferedReader reader;
    private final Gson gson;

    public FilterContainsName(BufferedReader reader) {
        super("filter_contains_name", "вывести элементы, значение поля name которых содержит заданную подстроку");
        this.reader = reader;
        this.gson = createGson();
    }

    @Override
    public void execute(String[] args, ObjectInputStream in, ObjectOutputStream out) throws CollectionException, IOException {
        try {
            if (args.length == 0) {
                throw new CollectionException("Не указана подстрока для фильтрации");
            }

            String substring = args[0];

            out.writeObject("filter_contains_name");
            out.writeObject(args);
            out.flush();

            String response = (String) in.readObject();
            System.out.println(response);
        } catch (ClassNotFoundException e) {
            throw new CollectionException("Ошибка при чтении ответа от сервера: " + e.getMessage());
        }
    }

    private Gson createGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
        return gsonBuilder.create();
    }
}
