package org.example;

import java.io.IOException;

public class Main {

    /**
     * Точка входа в программу.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        String filename = System.getenv("FILENAME"); // получение имени файла из переменной окружения
        if (filename == null) {
            System.err.println("Переменная окружения FILENAME не задана");
            System.exit(1);
        }
        try {
            CollectionManager collectionManager = new CollectionManager();
            collectionManager.loadCollection(filename);
            CommandManager commandManager = new CommandManager(collectionManager);
            ConsoleManager consoleManager = new ConsoleManager(commandManager, collectionManager);

            consoleManager.interactiveMode(); // запуск интерактивного режима

            Thread shutdownHook = new Thread(new ShutdownHook(collectionManager, filename));
            Runtime.getRuntime().addShutdownHook(shutdownHook); // сохранение в файл и завершение программы

        } catch (IOException e) {
            System.err.println("Ошибка ввода-вывода: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Ошибка преобразования числа: " + e.getMessage());
        }
    }

}
