package com.gabeWebTest.webTest.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileNameParser {

    public static String EXTRACT_FILENAME(String filePath) {
        // Define the regular expression pattern to match the filename
        Pattern pattern = Pattern.compile(".*/([^/]+)$");

        // Create a matcher for the given file path
        Matcher matcher = pattern.matcher(filePath);

        // Check if the pattern matches the file path
        if (matcher.find()) {
            // Extract and return the filename
            return matcher.group(1);
        } else {
            // If no match is found, return null or an empty string, depending on your requirements
            return null;
        }
    }

}
