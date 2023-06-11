package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private final Socket socket;
    private final BufferedReader reader;
    private final PrintWriter writer;

    public Client(Socket socket) throws IOException {
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(socket.getOutputStream(), true);
    }

    public void start(BufferedReader consoleReader) throws IOException {
        String serverResponse = reader.readLine();
        String userInput;
        while ((userInput = consoleReader.readLine()) != null) {
            writer.println(userInput);
            writer.flush();
            serverResponse = reader.readLine();
            if (serverResponse == null) {
                break;
            }
            System.out.println("Ответ от сервера: " + serverResponse);
        }
    }
    public void close() {
        try {
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}