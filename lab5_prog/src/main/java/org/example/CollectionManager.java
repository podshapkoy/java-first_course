package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.example.Description.SpaceMarine;
import org.example.command.CollectionException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;

public class CollectionManager {
    private final List<SpaceMarine> collection;
    private final Collection<SpaceMarine> spaceMarines;
    private final LocalDateTime initializationDate;
    private final Gson gson;

    public CollectionManager() {
        this.collection = new ArrayList<>();
        spaceMarines = new ArrayList<>();
        initializationDate = LocalDateTime.now();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
        gson = gsonBuilder.create();
    }

    public void loadCollection(String filename) throws IOException {
        // загрузка коллекции из файла
        try {
            // Создаем объект GsonBuilder и регистрируем в нём кастомный адаптер типов LocalDateTimeDeserializer
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());

            // Создаем объект Gson для работы с JSON, используя созданный ранее GsonBuilder
            Gson gson = gsonBuilder.create();

            // Открываем файл и считываем его содержимое в строку
            String json = Files.readString(Path.of(filename));

            // Преобразуем JSON-строку в список объектов SpaceMarine
            List<SpaceMarine> spaceMarines = gson.fromJson(json, new TypeToken<List<SpaceMarine>>() {
            }.getType());

            // Очищаем текущую коллекцию и добавляем загруженные объекты SpaceMarine
            collection.clear();
            for (SpaceMarine spaceMarine : spaceMarines) {
                try {
                    addElement(spaceMarine);
                } catch (CollectionException e) {
                    System.err.println("Ошибка при добавлении элемента в коллекцию: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        } catch (JsonSyntaxException e) {
            System.out.println("Ошибка при чтении JSON: " + e.getMessage());
        }
    }

    public void addElement(SpaceMarine spaceMarine) throws CollectionException {
        if (spaceMarine == null) {
            System.out.println("Ошибка: переданный объект SpaceMarine равен null");
            return;
        }

        if (collection.contains(spaceMarine)) {
            System.out.println("Ошибка: объект SpaceMarine с таким id уже существует в коллекции");
            return;
        }

        collection.add(spaceMarine);
        spaceMarines.add(spaceMarine);
        System.out.println("Объект SpaceMarine успешно добавлен в коллекцию");
    }

    public void clearCollection() {
        collection.clear();
        spaceMarines.clear();
    }


    public SpaceMarine removeById(Integer id) {
        SpaceMarine removedSpaceMarine = null;

        for (SpaceMarine spaceMarine : collection) {
            if (spaceMarine.getId().equals(id)) {
                removedSpaceMarine = spaceMarine;
                break;
            }
        }

        if (removedSpaceMarine != null) {
            collection.remove(removedSpaceMarine);
            spaceMarines.remove(removedSpaceMarine);
        }

        return removedSpaceMarine;
    }

    public Collection<SpaceMarine> getCollection() {
        return Collections.unmodifiableCollection(collection);
    }


    public Collection<SpaceMarine> getSpaceMarineCollection() {
        return Collections.unmodifiableCollection(spaceMarines);
    }

    public LocalDateTime getInitializationDate() {
        return initializationDate;
    }

    public boolean containsId(Integer id) {
        for (SpaceMarine spaceMarine : collection) {
            if (spaceMarine.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public SpaceMarine getSpaceMarineById(Integer id) {
        for (SpaceMarine spaceMarine : collection) {
            if (spaceMarine.getId().equals(id)) {
                return spaceMarine;
            }
        }
        return null;
    }

    public boolean containsSpaceMarineId(Integer id) {
        for (SpaceMarine spaceMarine : collection) {
            if (spaceMarine.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void updateSpaceMarineId(SpaceMarine oldSpaceMarine, Integer newId) {
        SpaceMarine spaceMarineToUpdate = null;

        for (SpaceMarine spaceMarine : collection) {
            if (spaceMarine.getId().equals(oldSpaceMarine.getId())) {
                spaceMarineToUpdate = spaceMarine;
                break;
            }
        }

        if (spaceMarineToUpdate != null) {
            spaceMarineToUpdate.setId(newId);
            System.out.println("Id объекта SpaceMarine успешно обновлен");
        } else {
            System.out.println("Объект SpaceMarine с указанным oldId не найден");
        }
    }

    public String getSpaceMarineCollectionJson() {
        return gson.toJson(spaceMarines);
    }

    public int removeGreater(int heartCount) {
        Iterator<SpaceMarine> iterator = collection.iterator();
        List<SpaceMarine> removedMarines = new ArrayList<>();

        while (iterator.hasNext()) {
            SpaceMarine spaceMarine = iterator.next();
            if (spaceMarine.getHeartCount() > heartCount) {
                iterator.remove();
                spaceMarines.remove(spaceMarine);
                removedMarines.add(spaceMarine);
            }
        }

        return removedMarines.size();
    }

    public int removeLower(int heartCount) {
        Iterator<SpaceMarine> iterator = collection.iterator();
        List<SpaceMarine> removedMarines = new ArrayList<>();

        while (iterator.hasNext()) {
            SpaceMarine spaceMarine = iterator.next();
            if (spaceMarine.getHeartCount() < heartCount) {
                iterator.remove();
                spaceMarines.remove(spaceMarine);
                removedMarines.add(spaceMarine);
            }
        }

        return removedMarines.size();
    }

    public int removeByChapterName(String chapterName) {
        Iterator<SpaceMarine> iterator = collection.iterator();
        int count = 0;

        while (iterator.hasNext()) {
            SpaceMarine spaceMarine = iterator.next();
            if (spaceMarine.getChapter().getName().trim().equalsIgnoreCase(chapterName)) {
                iterator.remove();
                spaceMarines.remove(spaceMarine);
                count++;
                break;
            }
        }
        return count;
    }

    public int generateId() {
        int maxId = 0;
        for (SpaceMarine spaceMarine : spaceMarines) {
            if (spaceMarine.getId() > maxId) {
                maxId = spaceMarine.getId();
            }
        }
        return maxId + 1;
    }

}

