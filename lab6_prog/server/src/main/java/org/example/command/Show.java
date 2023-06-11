package org.example.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.CollectionManager;
import org.example.LocalDateTimeAdapter;

import java.io.PrintWriter;
import java.time.LocalDateTime;

public class Show extends Command {
    private final CollectionManager collectionManager;
    private final Gson gson;

    public Show(CollectionManager collectionManager) {
        super("show", "вывести все элементы коллекции");
        this.collectionManager = collectionManager;
        this.gson = createGson();
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager) throws CollectionException {
        if (collectionManager.getSpaceMarineCollection().isEmpty()) {
            return "Коллекция пуста.";
        }

        String json = gson.toJson(collectionManager.getSpaceMarineCollection());
        return json.replaceAll("\n", "");
    }
    @Override
    public String execute(String[] args, CollectionManager collectionManager, PrintWriter writer) throws CollectionException {
        String result = execute(args, collectionManager);
        writer.println(result);
        writer.flush();
        return result;
    }

    private Gson createGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
        gsonBuilder.setPrettyPrinting().serializeNulls();
        return gsonBuilder.create();
    }

    @Override
    public String execute(String[] args) throws CollectionException {
        throw new UnsupportedOperationException("Метод execute(String[]) не поддерживается на сервере");
    }
}
