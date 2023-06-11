package org.example.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.CollectionManager;
import org.example.Description.SpaceMarine;
import org.example.LocalDateTimeAdapter;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class Head extends Command {
    private final CollectionManager collectionManager;
    private final Gson gson;
    private SpaceMarine firstElement;

    public Head(CollectionManager collectionManager) {
        super("head", "вывести первый элемент коллекции");
        this.collectionManager = collectionManager;
        this.gson = createGson();
        updateFirstElement();
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager) throws CollectionException {
        if (firstElement == null) {
            throw new CollectionException("Коллекция пуста");
        }
        String json = gson.toJson(firstElement);
        return json;
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager, PrintWriter writer) throws CollectionException {
        String result = execute(args, collectionManager);
        writer.println(result.replaceAll("\n", ""));
        writer.flush();
        return result;
    }
    private Gson createGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
        gsonBuilder.setPrettyPrinting().serializeNulls();
        return gsonBuilder.create();
    }

    public void updateFirstElement() {
        try {
            firstElement = collectionManager.getSpaceMarineCollection().stream().findFirst().orElse(null);
        } catch (NoSuchElementException e) {
            firstElement = null;
        }
    }
    @Override
    public String execute(String[] args) throws CollectionException {
        throw new UnsupportedOperationException("Метод execute(String[]) не поддерживается на сервере");
    }
}
