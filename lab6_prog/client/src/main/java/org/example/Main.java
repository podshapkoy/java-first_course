package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Подключено к серверу: " + SERVER_HOST + ":" + SERVER_PORT);
            Client client = new Client(socket);
            client.start(consoleReader);
            client.close();
        } catch (IOException e) {
            System.err.println("Ошибка подключения к серверу: " + e.getMessage());
        }
    }
}
