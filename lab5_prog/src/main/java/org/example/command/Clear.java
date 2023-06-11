package org.example.command;

import org.example.CollectionManager;

/**
 * Команда "clear" - очищает коллекцию.
 */
public class Clear extends Command {
    private final CollectionManager collectionManager;

    /**
     * Создает новый объект команды.
     *
     * @param collectionManager менеджер коллекции
     */
    public Clear(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду "clear".
     *
     * @param args              аргументы команды
     * @param collectionManager менеджер коллекции
     * @throws CollectionException если возникает ошибка при выполнении операции с коллекцией
     */
    @Override
    public void execute(String[] args, CollectionManager collectionManager) throws CollectionException {
        collectionManager.clearCollection();
        System.out.println("Коллекция была очищена.");
    }

    /**
     * Выполняет команду "clear" с использованием менеджера коллекции по умолчанию.
     *
     * @param args аргументы команды
     * @throws CollectionException если возникает ошибка при выполнении операции с коллекцией
     */
    @Override
    public void execute(String[] args) throws CollectionException {
        execute(args, collectionManager);
    }
}
