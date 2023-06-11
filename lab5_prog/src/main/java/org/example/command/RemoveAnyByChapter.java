package org.example.command;

import org.example.CollectionManager;

import java.io.BufferedReader;
import java.io.IOException;

public class RemoveAnyByChapter extends Command {
    private final CollectionManager collectionManager;
    private final BufferedReader reader;

    public RemoveAnyByChapter(CollectionManager collectionManager, BufferedReader reader) {
        super("remove_any_by_chapter", "удалить из коллекции один элемент, значение поля chapter которого эквивалентно заданному");
        this.collectionManager = collectionManager;
        this.reader = reader;
    }

    @Override
    public void execute(String[] args, CollectionManager collectionManager) throws CollectionException {
        try {
            if (args.length == 0) {
                System.out.print("Введите значение поля chapter.name: ");
                String input = reader.readLine().trim();
                args = new String[]{input};
            }

            String chapterName = args[0].trim();
            int count = collectionManager.removeByChapterName(chapterName);

            if (count > 0) {
                System.out.println("Удалено " + count + " элемент(ов) с указанным значением поля chapter.name.");
            } else {
                System.out.println("Элементы с указанным значением поля chapter.name не найдены в коллекции.");
            }
        } catch (IOException e) {
            throw new CollectionException("Ошибка чтения ввода: " + e.getMessage());
        }
    }

    @Override
    public void execute(String[] args) throws CollectionException {
        execute(args, collectionManager);
    }
}
