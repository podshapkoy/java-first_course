package org.example.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.Description.SpaceMarine;
import org.example.LocalDateTimeAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
            out.writeObject(substring);
            out.flush();

            Object response = in.readObject();
            if (response instanceof String) {
                System.out.println(response);
            } else if (response instanceof List<?>) {
                List<?> filteredList = (List<?>) response;
                List<SpaceMarine> spaceMarines = new ArrayList<>();
                for (Object obj : filteredList) {
                    if (obj instanceof SpaceMarine) {
                        SpaceMarine spaceMarine = (SpaceMarine) obj;
                        spaceMarines.add(spaceMarine);
                    }
                }
                if (!spaceMarines.isEmpty()) {
                    String json = gson.toJson(spaceMarines);
                    System.out.println(json);
                } else {
                    System.out.println("Нет элементов, значение поля name которых содержит заданную подстроку");
                }
            } else {
                System.out.println("Некорректный формат ответа от сервера");
            }
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
