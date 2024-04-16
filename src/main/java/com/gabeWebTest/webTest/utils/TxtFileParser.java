package com.gabeWebTest.webTest.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TxtFileParser {
    private static final String TEXT_LOCATION = "src/main/resources/static/";

    public static String readTextFromFile(String filePath) {
        // Read the content of the file as a string
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Path.of(TEXT_LOCATION+filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new String(bytes);
    }
}
