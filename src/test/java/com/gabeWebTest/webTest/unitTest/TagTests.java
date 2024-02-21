package com.gabeWebTest.webTest.unitTest;

import com.gabeWebTest.webTest.data.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class TagTests {

    @Test
    public void testConvertColorToHex() {
        //Setup
        Tag tag = new Tag();
        Color color = new Color(255,0,0);

        tag.setColor(color);

        String expectedHex = "#ff0000";
        String actualHex = tag.getColorHex();

        Assertions.assertEquals(expectedHex, actualHex);
    }

    @Test
    public void testConvertHexToColor() {
        Tag tag = new Tag();

        String testHex = "#ff0000";
        tag.setColorHex(testHex);

        Color expectedColor = new Color(255,0,0);
        Color actualColor = tag.getColor();
        Assertions.assertEquals(expectedColor, actualColor);
    }
}
