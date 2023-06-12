package org.example.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.CollectionManager;
import org.example.Description.SpaceMarine;
import org.example.LocalDateTimeAdapter;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Collection;

public class FilterContainsName extends Command {
    private final CollectionManager collectionManager;
    private final BufferedReader reader;
    private final Gson gson;

    public FilterContainsName(CollectionManager collectionManager, BufferedReader reader) {
        super("filter_contains_name", "вывести элементы, значение поля name которых содержит заданную подстроку");
        this.collectionManager = collectionManager;
        this.reader = reader;
        this.gson = createGson();
    }

    @Override
    public String execute(String[] args) throws CollectionException {
        if (args.length == 0) {
            throw new CollectionException("Не указана подстрока для фильтрации");
        }

        String substring = args[0];
        return filterByName(substring, collectionManager);
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager, PrintWriter writer) throws CollectionException {
        if (args.length == 0) {
            throw new CollectionException("Не указана подстрока для фильтрации");
        }

        String substring = args[0];
        return filterByName(substring, collectionManager, writer);
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager) throws CollectionException {
        return execute(args);
    }

    private String filterByName(String substring, CollectionManager collectionManager) throws CollectionException {
        Collection<SpaceMarine> collection = collectionManager.getSpaceMarineCollection();
        StringBuilder result = new StringBuilder();
        boolean found = false;
        for (SpaceMarine marine : collection) {
            if (marine.getName().contains(substring)) {
                String json = gson.toJson(marine);
                result.append(json).append("\n");
                found = true;
            }
        }
        if (!found) {
            throw new CollectionException("Элементы с именем, содержащим подстроку '" + substring + "', не найдены");
        }
        return result.toString();
    }

    private String filterByName(String substring, CollectionManager collectionManager, PrintWriter writer) throws CollectionException {
        Collection<SpaceMarine> collection = collectionManager.getSpaceMarineCollection();
        boolean found = false;
        for (SpaceMarine marine : collection) {
            if (marine.getName().contains(substring)) {
                String json = gson.toJson(marine);
                writer.println(json);
                found = true;
            }
        }
        if (!found) {
            throw new CollectionException("Элементы с именем, содержащим подстроку '" + substring + "', не найдены");
        }
        return "Фильтрация по имени успешно выполнена.";
    }

    private Gson createGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
        return gsonBuilder.create();
    }
}
