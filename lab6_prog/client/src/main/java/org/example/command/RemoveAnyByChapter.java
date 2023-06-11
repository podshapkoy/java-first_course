package org.example.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RemoveAnyByChapter extends Command {
    private final BufferedReader reader;

    public RemoveAnyByChapter(BufferedReader reader) {
        super("remove_any_by_chapter", "удалить из коллекции один элемент, значение поля chapter которого эквивалентно заданному");
        this.reader = reader;
    }

    @Override
    public void execute(String[] args, ObjectInputStream in, ObjectOutputStream out) throws CollectionException, IOException {
        try {
            if (args.length == 0) {
                throw new CollectionException("Не указано значение поля chapter.name");
            }

            String chapterName = args[0].trim();

            out.writeObject("remove_any_by_chapter");
            out.writeObject(args);
            out.flush();

            String response = (String) in.readObject();
            System.out.println(response);
        } catch (ClassNotFoundException e) {
            throw new CollectionException("Ошибка при чтении ответа от сервера: " + e.getMessage());
        }
    }
}
