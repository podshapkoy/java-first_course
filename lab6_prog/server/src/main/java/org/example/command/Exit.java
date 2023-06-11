package org.example.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.CollectionManager;
import org.example.LocalDateTimeAdapter;

import java.io.*;
import java.time.LocalDateTime;

public class Exit extends Command {

    private final CollectionManager collectionManager;

    public Exit(CollectionManager collectionManager) {
        super("exit", "завершить программу и сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute(String[] args) throws CollectionException {
        saveCollectionToFile();
        return "Завершение работы программы.";
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager, PrintWriter writer) throws CollectionException {
        return execute(args);
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager) throws CollectionException {
        return execute(args);
    }

    private void saveCollectionToFile() throws CollectionException {
        try {
            String filename = "C:/Users/User/Desktop/prog/lab5_fixed/lab6/server/data_new.json";
            File file = new File(filename);
            if (file.exists()) {
                throw new CollectionException("Файл \"" + filename + "\" уже существует");
            }

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
            Gson gson = gsonBuilder.setPrettyPrinting().create();

            String json = gson.toJson(collectionManager.getSpaceMarineCollection());

            try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
                outputStream.write(json.getBytes());
            }

            System.out.println("Коллекция была сохранена в файл \"" + filename + "\"");
        } catch (IOException e) {
            throw new CollectionException("Ошибка при сохранении коллекции в файл: " + e.getMessage());
        }
    }
}
