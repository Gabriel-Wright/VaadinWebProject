package com.gabeWebTest.webTest.utils;

import com.vaadin.flow.component.html.Paragraph;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArticleParagraphParser {

    public final static int DEFAULT = -1;
    public final static int VERTICAL = 0;
    public final static int HORIZONTAL_LEFT = 1;
    public final static int HORIZONTAL_RIGHT = 2;

//    private static Pattern pattern = Pattern.compile("<([vh]),\\s*\\w+\\/\\w+\\.\\w+>");
    private static Pattern pattern = Pattern.compile("<([a-z]),(\\s*\\d*)>");


    private static Matcher matcher;
    public static int GET_FORMAT(String paragraphText) {
        matcher = pattern.matcher(paragraphText);

        if(matcher.find()) {
            String format = matcher.group(1);
            return matchFormatCode(format);
        }
        return DEFAULT;
    }

    private static int matchFormatCode(String formatCode) {
        return switch(formatCode) {
            case("v") -> VERTICAL;
            case("l") -> HORIZONTAL_LEFT;
            case("r") -> HORIZONTAL_RIGHT;
            default -> DEFAULT;
        };
    }

    //Realise maybe inefficient to do these both separately - but for code it is easier to read
    public static int GET_SOURCE_ID(String paragraphText) {
        matcher = pattern.matcher(paragraphText);

        if(matcher.find()) {
            String sourceIDString = matcher.group(2);
            try {
                return Integer.parseInt(sourceIDString);
            } catch (NumberFormatException e) {
//                System.err.println("Error parsing image ID: " + e.getMessage());
                return -1; // Or return a default value as needed
            }
        }
        return -1;
    }

    public static String REMOVE_PATTERN(String paragraphText) {
        return paragraphText.replaceAll("<[^>]+>", "");
    }

    public static String[] SPLIT_TEXT(String rawText) {
        return rawText.split("\\[p\\]");
    }
}
