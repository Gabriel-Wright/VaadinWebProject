package com.gabeWebTest.webTest.unitTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import static com.gabeWebTest.webTest.utils.ArticleParagraphParser.*;

public class ParagraphParserTests {

    @Test
    public void testGetParagraphFormat() {
        String paragraphTextV = "<v,3> hello this is a paragraph whats going on guys";
        String paragraphTextH = "<l,> hello this is a paragraph watsas";
        String paragraphTextD = "<a,4> blah blah";

        Assertions.assertEquals(VERTICAL, GET_FORMAT(paragraphTextV));
        Assertions.assertEquals(HORIZONTAL_LEFT, GET_FORMAT(paragraphTextH));
        Assertions.assertEquals(DEFAULT, GET_FORMAT(paragraphTextD));
    }

    @Test
    public void testGetSourceID() {
        String paragraphText2 = "<v,2>";
        String paragraphText4 = "<l,4>";

        Assertions.assertEquals(2, GET_SOURCE_ID(paragraphText2));
        Assertions.assertEquals(4, GET_SOURCE_ID(paragraphText4));
    }

    @Test
    public void testRemovePattern() {
        String paragraphTest = "<v,4>This is an excellent test paragraph";
        String expectedParagraph = "This is an excellent test paragraph";

        String paragraphTest2 = "<l,6> Yay lets test thisone lol";
        String expectedParagraph2 = " Yay lets test thisone lol";

        Assertions.assertEquals(expectedParagraph, REMOVE_PATTERN(paragraphTest));
        Assertions.assertEquals(expectedParagraph2, REMOVE_PATTERN(paragraphTest2));
    }

    @Test
    public void testSplitText() {
        String text = "Hello this is the text [p] whats going on lol xd lets see[p] wahoo [p] blah";
        String[] paragraphs = SPLIT_TEXT(text);
        Assertions.assertEquals(4, paragraphs.length);
        Assertions.assertEquals(" whats going on lol xd lets see", paragraphs[1]);
        Assertions.assertEquals("Hello this is the text ", paragraphs[0]);
    }
}
