package org.example.command;

import org.example.CollectionManager;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class RemoveAnyByChapter extends Command {
    private final CollectionManager collectionManager;
    private final BufferedReader reader;

    public RemoveAnyByChapter(CollectionManager collectionManager, BufferedReader reader) {
        super("remove_any_by_chapter", "удалить из коллекции один элемент, значение поля chapter которого эквивалентно заданному");
        this.collectionManager = collectionManager;
        this.reader = reader;
    }

    @Override
    public String execute(String[] args) throws CollectionException {
        if (args.length == 0) {
            throw new CollectionException("Не указано значение поля chapter.name");
        }

        String chapterName = args[0].trim();
        int count = collectionManager.removeByChapterName(chapterName);

        if (count > 0) {
            System.out.println("Удалено " + count + " элемент(ов) с указанным значением поля chapter.name.");
            return "Удалено " + count + " элемент(ов) с указанным значением поля chapter.name.";
        } else {
            System.out.println("Элементы с указанным значением поля chapter.name не найдены в коллекции.");
            return "Элементы с указанным значением поля chapter.name не найдены в коллекции.";
        }
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager, PrintWriter writer) throws CollectionException {
        if (args.length == 0) {
            throw new CollectionException("Не указано значение поля chapter.name");
        }

        String chapterName = args[0].trim();
        int count = collectionManager.removeByChapterName(chapterName);

        if (count > 0) {
            writer.println("Удалено " + count + " элемент(ов) с указанным значением поля chapter.name.");
            return "Удалено " + count + " элемент(ов) с указанным значением поля chapter.name.";
        } else {
            writer.println("Элементы с указанным значением поля chapter.name не найдены в коллекции.");
            return "Элементы с указанным значением поля chapter.name не найдены в коллекции.";
        }
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager) throws CollectionException {
        return execute(args);
    }
}
