package org.example.command;

import org.example.CollectionManager;
import org.example.Description.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class Add extends Command {

    private final CollectionManager collectionManager;

    public Add(CollectionManager collectionManager) {
        super("add", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager) {
        throw new UnsupportedOperationException("Метод execute(String[], CollectionManager) не поддерживается на сервере");
    }

    @Override
    public String execute(String[] args) throws CollectionException {
        try {
            int id = collectionManager.generateId();
            LocalDateTime creationDate = LocalDateTime.now();

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Введите имя: ");
            String name = reader.readLine().trim();
            if (name.isEmpty()) {
                throw new SpaceMarineException("Ошибка: имя не может быть пустым");
            }

            System.out.print("Введите координату X типа double, которая должна быть больше -412: ");
            String xString = reader.readLine().trim();
            double x;
            try {
                x = Double.valueOf(xString);
            } catch (NumberFormatException e) {
                throw new CoordinatesException("Ошибка: некорректный формат координаты X");
            }

            System.out.print("Введите координату Y типа double: ");
            String yString = reader.readLine().trim();
            double y;
            try {
                y = Double.valueOf(yString);
            } catch (NumberFormatException e) {
                throw new CoordinatesException("Ошибка: некорректный формат координаты Y");
            }

            Coordinates coordinates = new Coordinates(x, y);
            if (coordinates.getX() <= -412) {
                throw new CoordinatesException("Ошибка: значение координаты X должно быть больше -412");
            }

            System.out.print("Введите значение здоровья, которое должно быть больше 0: ");
            String healthString = reader.readLine().trim();
            float health = Float.parseFloat(healthString);
            if (health <= 0) {
                throw new SpaceMarineException(" значение здоровья должно быть больше 0");
            }

            System.out.print("Введите количество сердец, которое должно быть больше 0 и не превышать 3: ");
            String heartCountString = reader.readLine().trim();
            Integer heartCount = Integer.parseInt(heartCountString);
            if (heartCount != null && (heartCount <= 0 || heartCount > 3)) {
                throw new SpaceMarineException(" значение количества сердец должно быть больше 0 и не превышать 3");
            }

            System.out.print("Введите категорию (DREADNOUGHT, TACTICAL, LIBRARIAN, CHAPLAIN, HELIX): ");
            String categoryString = reader.readLine().trim();
            AstartesCategory category = null;
            if (!categoryString.isEmpty()) {
                category = AstartesCategory.valueOf(categoryString);
            }

            System.out.print("Введите тип оружия (BOLTGUN, MELTAGUN, GRENADE_LAUNCHER, MULTI_MELTA): ");
            String weaponTypeString = reader.readLine().trim();
            Weapon weaponType = null;
            if (!weaponTypeString.isEmpty()) {
                weaponType = Weapon.valueOf(weaponTypeString);
            }

            System.out.print("Введите название главы: ");
            String chapterName = reader.readLine().trim();
            if (chapterName.isEmpty()) {
                throw new ChapterException(" название главы не может быть пустым");
            }

            System.out.print("Введите родительскую легию главы: ");
            String parentLegion = reader.readLine().trim();

            System.out.print("Введите количество морских пехотинцев главы, которое должно быть больше 0 и не превышать 1000: ");
            String marinesCountString = reader.readLine().trim();
            Long marinesCount = Long.parseLong(marinesCountString);
            if (marinesCount != null && (marinesCount <= 0 || marinesCount > 1000)) {
                throw new ChapterException(" количество морских пехотинцев должно быть больше 0 и не превышать 1000");
            }

            System.out.print("Введите название мира главы: ");
            String world = reader.readLine().trim();

            Chapter chapter = new Chapter(chapterName, parentLegion, marinesCount, world);
            SpaceMarine spaceMarine = new SpaceMarine(
                    id, name, coordinates, creationDate, health, heartCount, category, weaponType, chapter
            );

            if (collectionManager.containsId(spaceMarine.getId())) {
                throw new SpaceMarineException(" элемент с таким id уже существует в коллекции");
            }

            collectionManager.addElement(spaceMarine);
            return "Объект добавлен в коллекцию";
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении данных: " + e.getMessage(), e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Ошибка при чтении данных: некорректный формат числовых данных", e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Ошибка при чтении данных: некорректный формат данных", e);
        } catch (SpaceMarineException | CoordinatesException | ChapterException e) {
            throw new RuntimeException("Ошибка: " + e.getMessage(), e);
        } catch (CollectionException e) {
            throw new RuntimeException("Ошибка при добавлении элемента в коллекцию: " + e.getMessage(), e);
        }
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager, PrintWriter writer) throws CollectionException {
        throw new UnsupportedOperationException("Метод execute(String[], CollectionManager, PrintWriter) не поддерживается на сервере");
    }
}
