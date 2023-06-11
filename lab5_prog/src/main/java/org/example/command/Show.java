package org.example.command;

import com.google.gson.*;
import org.example.CollectionManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Show extends Command {
    private final CollectionManager collectionManager;
    private final Gson gson;

    public Show(CollectionManager collectionManager) {
        super("show", "вывести все элементы коллекции");
        this.collectionManager = collectionManager;
        this.gson = createGson();
    }

    @Override
    public void execute(String[] args) throws CollectionException {
        execute(args, collectionManager);
    }

    @Override
    public void execute(String[] args, CollectionManager collectionManager) throws CollectionException {
        if (collectionManager.getCollection().isEmpty()) {
            System.out.println("Коллекция пуста.");
            return;
        }

        collectionManager.getCollection().forEach(element -> {
            String json = gson.toJson(element);
            System.out.println(json);
        });
    }

    private Gson createGson() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (localDateTime, type, jsonSerializationContext) ->
                new JsonPrimitive(localDateTime.format(formatter)));
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (jsonElement, type, jsonDeserializationContext) ->
                LocalDateTime.parse(jsonElement.getAsString(), formatter));
        gsonBuilder.setPrettyPrinting();
        return gsonBuilder.create();
    }
}
