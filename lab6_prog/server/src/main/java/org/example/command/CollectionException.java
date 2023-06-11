package org.example.command;

/**
 * Исключение, которое может возникнуть при выполнении операций с коллекцией.
 */
public class CollectionException extends Exception {

    /**
     * Создает новый объект исключения с указанным сообщением.
     *
     * @param message сообщение об ошибке
     */
    public CollectionException(String message) {
        super(message);
    }

    /**
     * Создает новый объект исключения с указанным сообщением и причиной.
     *
     * @param message сообщение об ошибке
     * @param cause   причина исключения
     */
    public CollectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
