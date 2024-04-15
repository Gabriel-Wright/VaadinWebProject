package com.gabeWebTest.webTest.utils;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSaveHandler {

    private static final String IMAGE_DESTINATION = "src/main/resources/static/img/";
    private static final String TEXT_DESTINATION = "src/main/resources/static/text/";

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

    public static void saveTextToDest(MultipartFile file) throws IOException {
        // Create the directory if it doesn't exist
        Files.createDirectories(Paths.get(TEXT_DESTINATION));

        // Copy the input stream of the uploaded file to the destination file
        try (InputStream inputStream = file.getInputStream();
             OutputStream outputStream = new FileOutputStream(new File(TEXT_DESTINATION + file.getOriginalFilename()))) {
            FileCopyUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            throw new IOException("Failed to save image to destination", e);
        }
    }

}
