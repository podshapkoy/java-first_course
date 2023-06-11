package org.example.command;

import org.example.CollectionManager;

import java.io.PrintWriter;

/**
 * Команда "clear" - очищает коллекцию.
 */
public class Clear extends Command {
    private final CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager) throws CollectionException {
        collectionManager.clearCollection();
        return "Коллекция была очищена.";
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager, PrintWriter writer) throws CollectionException {
        String result = execute(args, collectionManager);
        writer.println(result);
        writer.flush();
        return result;
    }

    @Override
    public String execute(String[] args) throws CollectionException {
        throw new UnsupportedOperationException("Метод execute(String[]) не поддерживается на сервере");
    }
}