package org.example.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.CollectionManager;
import org.example.LocalDateTimeAdapter;

import java.io.PrintWriter;
import java.time.LocalDateTime;
public class Info extends Command {
    private final CollectionManager collectionManager;
    private final Gson gson;

    public Info(CollectionManager collectionManager) {
        super("info", "вывести информацию о коллекции");
        this.collectionManager = collectionManager;
        this.gson = createGson();
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager) throws CollectionException {
        StringBuilder sb = new StringBuilder();
        sb.append("Тип коллекции: ").append(collectionManager.getCollection().getClass().getName()).append("\n");
        sb.append("Количество элементов в коллекции: ").append(collectionManager.getCollection().size()).append("\n");
        sb.append("Дата инициализации коллекции: ").append(collectionManager.getInitializationDate()).append("\n");
        return sb.toString();
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
