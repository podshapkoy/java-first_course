package org.example.command;

import org.example.Description.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Add extends Command {
    public Add() {
        super("add", "добавить новый элемент в коллекцию");
    }

    @Override
    public void execute(String[] args, ObjectInputStream in, ObjectOutputStream out) throws CollectionException, IOException {
        // Проверка наличия всех необходимых аргументов команды
        if (args.length < 11) {
            throw new CollectionException("Недостаточно аргументов для выполнения команды");
        }

        // Извлечение значений аргументов команды
        String name = args[0];
        double x = Double.parseDouble(args[1]);
        double y = Double.parseDouble(args[2]);
        float health = Float.parseFloat(args[3]);
        int heartCount = Integer.parseInt(args[4]);
        String category = args[5];
        String weaponType = args[6];
        String chapterName = args[7];
        String parentLegion = args[8];
        Long marinesCount = Long.parseLong(args[9]);
        String world = args[10];


        // Создание объекта SpaceMarine и отправка на сервер
        SpaceMarine spaceMarine = new SpaceMarine(null, name, new Coordinates(x, y), null, health, heartCount, AstartesCategory.valueOf(category), Weapon.valueOf(weaponType), new Chapter(chapterName, parentLegion, marinesCount, world));
        out.writeObject("add");
        out.writeObject(spaceMarine);
        out.flush();

        try {
            // Получение ответа от сервера
            String result = (String) in.readObject();
            System.out.println(result);
        } catch (ClassNotFoundException e) {
            throw new CollectionException("Ошибка при получении ответа от сервера");
        }
    }
}
