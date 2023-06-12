package org.example.command;

import org.example.CollectionManager;

public class Exit extends Command {

    public Exit() {
        super("exit", "завершить программу (без сохранения в файл)");
    }

    @Override
    public void execute(String[] args) {
        System.out.println("Завершение работы программы.");
        System.exit(0);
    }

    @Override
    public void execute(String[] args, CollectionManager collectionManager) throws CollectionException {
        execute(args);
    }
}
