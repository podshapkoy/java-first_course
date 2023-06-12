package org.example.command;

import com.google.gson.*;
import org.example.CollectionManager;
import org.example.Description.SpaceMarine;
import org.example.LocalDateTimeAdapter;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.NoSuchElementException;

public class Head extends Command {
    private final CollectionManager collectionManager;
    private final Gson gson;
    private SpaceMarine firstElement;

    public Head(final CollectionManager collectionManager) {
        super("head", "вывести первый элемент коллекции");
        this.collectionManager = collectionManager;
        this.gson = createGson();
        updateFirstElement();
    }

    @Override
    public void execute(String[] args) throws CollectionException {
        if (firstElement == null) {
            throw new CollectionException("Коллекция пуста");
        }
        String json = gson.toJson(firstElement);
        System.out.println(json);
    }

    @Override
    public void execute(String[] args, CollectionManager collectionManager) throws CollectionException {
        execute(args);
    }

    private Gson createGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()); // Регистрация адаптера типов
        gsonBuilder.setPrettyPrinting();
        return gsonBuilder.create();
    }

    public void updateFirstElement() {
        try {
            firstElement = collectionManager.getSpaceMarineCollection().stream().findFirst().orElse(null);
        } catch (NoSuchElementException e) {
            firstElement = null;
        }
    }
//    public void updateFirstElement() {
//        try {
//            Collection<SpaceMarine> spaceMarineCollection = collectionManager.getSpaceMarineCollection();
//            if (spaceMarineCollection.isEmpty()) {
//                firstElement = null;
//            } else {
//                firstElement = spaceMarineCollection.iterator().next();
//            }
//        } catch (NoSuchElementException e) {
//            firstElement = null;
//        }
//    }
}
