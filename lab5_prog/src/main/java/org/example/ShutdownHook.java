package org.example;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * ShutdownHook - класс, реализующий интерфейс Runnable, предназначен для сохранения коллекции в файл при завершении программы.
 */
public class ShutdownHook implements Runnable {
    private final CollectionManager collectionManager;
    private final String filename;

    /**
     * Создает новый объект ShutdownHook.
     *
     * @param collectionManager менеджер коллекции
     * @param filename          имя файла, в который будет сохранена коллекция
     */
    public ShutdownHook(CollectionManager collectionManager, String filename) {
        this.collectionManager = collectionManager;
        this.filename = filename;
    }

    /**
     * Выполняет сохранение коллекции в файл при завершении программы.
     */
    @Override
    public void run() {
        try {
            String json = collectionManager.getSpaceMarineCollectionJson();
            try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filename))) {
                outputStream.write(json.getBytes(StandardCharsets.UTF_8));
            }
            System.out.println("Коллекция была сохранена в файл \"" + filename + "\"");
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении коллекции в файл: " + e.getMessage());
        }
    }
}
