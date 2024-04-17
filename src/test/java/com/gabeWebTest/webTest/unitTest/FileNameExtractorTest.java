package com.gabeWebTest.webTest.unitTest;

import com.gabeWebTest.webTest.utils.FileNameParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileNameExtractorTest {

    @Test
    public void testFIleNameExtraction() {

        String filePath = "/static/img/testImage.png";
        String expectedFileName = "testImage.png";

        Assertions.assertEquals(expectedFileName, FileNameParser.EXTRACT_FILENAME(filePath));
    }
}
