package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
    private final Socket clientSocket;
    private final CommandManager commandManager;

    public ClientHandler(Socket clientSocket, CommandManager commandManager) {
        this.clientSocket = clientSocket;
        this.commandManager = commandManager;
    }

    @Override
    public void run() {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            CollectionManager collectionManager = new CollectionManager();
            ConsoleManager consoleManager = new ConsoleManager(commandManager, collectionManager, reader, writer);

            consoleManager.interactiveMode();
        } catch (IOException e) {
            System.err.println("Ошибка при обработке запроса от клиента: " + e.getMessage());
        } finally {
            closeConnection();
            System.out.println("Отключился клиент: " + clientSocket.getInetAddress());
        }
    }

    private void closeConnection() {
        try {
            if (clientSocket != null)
                clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
