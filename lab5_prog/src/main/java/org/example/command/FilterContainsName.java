package org.example.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.CollectionManager;
import org.example.Description.SpaceMarine;
import org.example.LocalDateTimeAdapter;

import java.io.BufferedReader;
import java.io.IOException;
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
    public void execute(String[] args, CollectionManager collectionManager) throws CollectionException {
        try {
            if (args.length == 0) {
                System.out.print("Введите подстроку для фильтрации: ");
                String substring = reader.readLine().trim();
                filterByName(substring, collectionManager);
            } else {
                String substring = args[0];
                filterByName(substring, collectionManager);
            }
        } catch (Exception e) {
            throw new CollectionException("Элементы с именем, содержащим подстроку '" + e.getMessage() + "', не найдены");
        }
    }
//    @Override
//    public void execute(String[] args, CollectionManager collectionManager) throws CollectionException {
//        if (args.length == 0) {
//            System.out.print("Введите подстроку для фильтрации: ");
//            String substring;
//            try {
//                substring = reader.readLine().trim();
//            } catch (IOException e) {
//                throw new CollectionException("Ошибка чтения ввода");
//            }
//            filterByName(substring, collectionManager);
//        } else {
//            String substring = args[0];
//            filterByName(substring, collectionManager);
//        }
//    }

    @Override
    public void execute(String[] args) throws CollectionException {
        execute(args, collectionManager);
    }

    private void filterByName(String substring, CollectionManager collectionManager) throws CollectionException {
        Collection<SpaceMarine> collection = collectionManager.getSpaceMarineCollection();
        boolean found = false;
        for (SpaceMarine marine : collection) {
            if (marine.getName().contains(substring)) {
                String json = gson.toJson(marine);
                System.out.println(json);
                found = true;
            }
        }
        if (!found) {
            throw new CollectionException(substring);
        }
    }

    private Gson createGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
        return gsonBuilder.create();
    }
}
