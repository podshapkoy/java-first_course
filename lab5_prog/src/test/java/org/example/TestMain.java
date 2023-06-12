package org.example;

import org.example.Description.*;
import org.example.command.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;


class TestMain {
    private CollectionManager collectionManager;
    private BufferedReader reader;

    @BeforeEach
    void setUp() throws CollectionException {
        collectionManager = new CollectionManager();
        SpaceMarine spaceMarine1 = new SpaceMarine(1, "John Doe", null, null, 356F, 2, null, null, null);
        SpaceMarine spaceMarine2 = new SpaceMarine(4, "Jane Smith", null, null, 567F, 3, null, null, null);
        SpaceMarine spaceMarine3 = new SpaceMarine(7, "Mike Johnson", null, null, 789F, 3, null, null, null);
        collectionManager.addElement(spaceMarine1);
        collectionManager.addElement(spaceMarine2);
        collectionManager.addElement(spaceMarine3);

        // Создаем BufferedReader для чтения ввода с консоли
        String input = "2";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Test
    void test_Head() throws CollectionException {
        Head headCommand = new Head(collectionManager);
        Assertions.assertFalse(collectionManager.getCollection().isEmpty());
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        headCommand.execute(new String[]{});
        String output = outputStream.toString().trim();
        System.setOut(originalOut);
        Assertions.assertFalse(output.isEmpty());
    }

    @Test
    void test_Show() throws CollectionException {
        Show showCommand = new Show(collectionManager);
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        showCommand.execute(new String[]{});
        String output = outputStream.toString().trim();
        System.setOut(originalOut);
        Assertions.assertFalse(output.isEmpty());
    }
    @Test
    void test_Info() {
        Command infoCommand = new Info(collectionManager);
        String[] args = {};

        Assertions.assertDoesNotThrow(() -> infoCommand.execute(args, collectionManager));
    }

    @Test
    void test_Filter_Contains_Name() {
        FilterContainsName filterContainsNameCommand = new FilterContainsName(collectionManager, reader);
        String[] args = {"xyz"};

        Assertions.assertThrows(CollectionException.class, () -> filterContainsNameCommand.execute(args));
    }

    @Test
    void test_Count_Greater_Than_Heart_Count() {
        CountGreaterThanHeartCount command = new CountGreaterThanHeartCount(collectionManager);
        String[] args = {"2"};

        Assertions.assertDoesNotThrow(() -> command.execute(args, collectionManager));
    }

    @Test
    void test_Execute_Script_File_Name() {
        ExecuteScriptFileName command = new ExecuteScriptFileName(collectionManager);
        String[] args = {};

        Assertions.assertDoesNotThrow(() -> command.execute(args));
    }

}
