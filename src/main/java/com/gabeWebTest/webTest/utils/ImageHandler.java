package com.gabeWebTest.webTest.utils;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImageHandler {

    private static final String IMAGE_DESTINATION = "src/main/resources/static/img/";

    public static void saveImageToDest(MultipartFile file) throws IOException {
        // Create the directory if it doesn't exist
        Files.createDirectories(Paths.get(IMAGE_DESTINATION));

        // Copy the input stream of the uploaded file to the destination file
        try (InputStream inputStream = file.getInputStream();
             OutputStream outputStream = new FileOutputStream(new File(IMAGE_DESTINATION + file.getOriginalFilename()))) {
            FileCopyUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            throw new IOException("Failed to save image to destination", e);
        }
    }

}
