package com.gabeWebTest.webTest.utils;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Paragraph;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArticleParagraphParser {

    public final static int DEFAULT = -1;
    public final static int VERTICAL = 0;
    public final static int HORIZONTAL_LEFT = 1;
    public final static int HORIZONTAL_RIGHT = 2;
    public final static int YOUTUBE_EMBED = 3;
    public final static int CODE_EMBED = 4;
    public final static int HEADING = 5;

//    private static Pattern pattern = Pattern.compile("<([vh]),\\s*\\w+\\/\\w+\\.\\w+>");
    //SINGLE INT SHOULD BE AFTER THE ,
    private static Pattern PATTERN_INT = Pattern.compile("<¬([a-z]),(\\s*\\d*)¬>");
    //ANY STRING CAN BE AFTER THE ,
    private static Pattern PATTERN_STRING = Pattern.compile("<¬([a-z]),([^¬>]*)¬>");
    //LINK PATTERN
    private static Pattern PATTERN_LINK  = Pattern.compile("¬¬([^,]+),([^¬]+)¬¬");
    private static Matcher matcher;
    public static int GET_FORMAT(String paragraphText) {
        matcher = PATTERN_STRING.matcher(paragraphText);

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
            case("y") -> YOUTUBE_EMBED;
            case("c") -> CODE_EMBED;
            case("h") -> HEADING;
            default -> DEFAULT;
        };
    }

    //Realise maybe inefficient to do these both separately - but for code it is easier to read
    public static int GET_SOURCE_ID(String paragraphText) {
        matcher = PATTERN_INT.matcher(paragraphText);

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

    public static String GET_PATTERN_STRING(String paragraphText) {
        matcher = PATTERN_STRING.matcher(paragraphText);

        if(matcher.find()) {
            String youtubeString = matcher.group(2);
            return youtubeString;
        }
        return "https://youtu.be/hoKluzn07eQ?si=9nhXnRnJ-MNfbwGd";

    }

    public static String REMOVE_PATTERN(String paragraphText) {
        return paragraphText.replaceAll("<¬[^>]+¬>", "");
    }

    public static String[] SPLIT_TEXT(String rawText) {
        return rawText.split("\\[p\\]");
    }

    public static Paragraph LOAD_PARAGRAPH_WITH_LINKS(String paragraphText) {
        Paragraph paragraph = new Paragraph();

        List<Anchor> links = getHtmlLinks(paragraphText);
        int numLinks = links.size();
        String[] paragraphSplitParts = PATTERN_LINK.split(paragraphText);
        int numParts = paragraphSplitParts.length;
        for(int i=0; i<numParts; i++) {
            paragraph.add(paragraphSplitParts[i]);
            if(i < numLinks) {
                paragraph.add(links.get(i));
            }
        }

        return paragraph;
    }

    private static List<Anchor> getHtmlLinks(String paragraphText) {
        List<Anchor> links = new ArrayList<>();
        Matcher matcher = PATTERN_LINK.matcher(paragraphText);
        while(matcher.find()) {
            String text = matcher.group(1);
            String url = matcher.group(2);
            String htmlString = "<a href=\""+url+"\">"+text+"</a>";
            Anchor anchor = new Anchor(url, text);
            links.add(anchor);
        }
        return links;
    }
}
