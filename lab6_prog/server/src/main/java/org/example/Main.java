package org.example;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Сервер запущен на порту " + SERVER_PORT);

            CollectionManager collectionManager = new CollectionManager();
            collectionManager.loadCollection("C:\\Users\\User\\Desktop\\lab6\\server\\data.json");
            CommandManager commandManager = new CommandManager(collectionManager);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Подключился новый клиент: " + clientSocket.getInetAddress().getHostAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket, commandManager);
                clientHandler.start();
            }
        } catch (IOException e) {
            System.err.println("Ошибка при запуске сервера: " + e.getMessage());
        }
    }
}
