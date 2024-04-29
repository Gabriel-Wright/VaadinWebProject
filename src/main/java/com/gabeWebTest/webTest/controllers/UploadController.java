package com.gabeWebTest.webTest.controllers;

import com.gabeWebTest.webTest.data.visualSource.VisualSource;
import com.gabeWebTest.webTest.data.webPage.WebPage;
import com.gabeWebTest.webTest.data.webPage.tags.Tag;
import com.gabeWebTest.webTest.services.TagService;
import com.gabeWebTest.webTest.services.VisualSourceService;
import com.gabeWebTest.webTest.services.WebPageService;
import com.gabeWebTest.webTest.utils.FileSaveHandler;
import com.sun.jna.platform.unix.X11;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.gabeWebTest.webTest.utils.FileSaveHandler.saveImageToDest;
import static com.gabeWebTest.webTest.utils.FileSaveHandler.saveTextToDest;

@RestController
@RequestMapping("/api/v1/upload")
public class UploadController {

    @Autowired
    private final WebPageService webPageService;
    private final TagService tagService;
    private final VisualSourceService visualSourceService;
    private final String resourcesPath = "src/main/resources/";

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    public UploadController(WebPageService webPageService, TagService tagService, VisualSourceService visualSourceService) {
        this.webPageService = webPageService;
        this.tagService = tagService;
        this.visualSourceService = visualSourceService;
    }

    @PostMapping("/text")
    public ResponseEntity<String> uploadText(@RequestParam("text") MultipartFile textFile) throws IOException {
        String fileName = textFile.getOriginalFilename();
        logger.info("Received request to upload text file: {}",fileName);
        if(StringUtils.isBlank(fileName)) {
            logger.warn("Received request without a file");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No file presented");
        }
        // Check if the content type is appropriate for a text file
        if (!isTextContentType(textFile.getContentType())) {
            logger.warn("Invalid file type received: {}", textFile.getContentType());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file type. Only text files are allowed.");
        }
        // Save the text file to the destination
        saveTextToDest(textFile);
        logger.info("Text file {} uploaded successfully", fileName);
        return ResponseEntity.ok("Text file:" + fileName + " uploaded successfully");
    }

    @PostMapping("/images")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile imageFile) throws IOException {
        String fileName = imageFile.getOriginalFilename();
        logger.info("Request receieved to upload image file: {}",fileName);
        if(StringUtils.isBlank(fileName)) {
            logger.warn("Received request without a file");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No file presented");
        }
        String formattedFileName = "/static/img/" + fileName;
        if(visualSourceService.findByImagePath(formattedFileName).isPresent()) {
            logger.warn("Image already exists under path: {}", fileName);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Image already exists under path:"+fileName);
        }
        // Check if the content type is appropriate for an image
        if (!isImageContentType(imageFile.getContentType())) {
            logger.warn("Invalid file type received: {}", imageFile.getContentType());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file type. Only image files are allowed.");
        }
        saveImageToDest(imageFile);
        visualSourceService.save(new VisualSource(formattedFileName));
        int newID = visualSourceService.findByImagePath(formattedFileName).get().getId();

        logger.info("Image {} uploaded successfully with new ID: {}", fileName, newID);
        return ResponseEntity.ok("Image:" + fileName+" uploaded successfully with new ID: "+newID);
    }

    @PostMapping("/article")
    public ResponseEntity<String> uploadArticle(@RequestParam("title") String title,
                                              @RequestParam("article_text_path") String articleTextPath,
                                              @RequestParam("thumbnail_path") int thumbnailID,
                                              @RequestParam("article_preview_text") String previewText,
                                              @RequestParam("tags") List<String> tags) {
        logger.info("Received request to upload article with title: {}", title);
        if(StringUtils.isBlank(title)) {
            logger.warn("Received request with blank title");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("WebPage title cannot be blank");
        }
        if(webPageIsPresent(title)) {
            // Return a response indicating that the webpage already exists
            logger.warn("Webpage with title '{}' already exists", title);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Webpage with title '" + title + "' already exists");
        }
        if(!isValidSavedImagePath(thumbnailID)) {
            logger.warn("No thumbnail found for ID: {}", thumbnailID);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No thumbnail under that path found");
        }
        if(!isValidTextFilePath(articleTextPath)) {
            logger.warn("No text file found for path: {}", articleTextPath);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No txt file under that path found");
        }
        // Proceed with creating and saving the new webpage
        WebPage uploadArticle = new WebPage(title, articleTextPath, thumbnailID, previewText, resolveTags(tags));
        webPageService.save(uploadArticle);

        // Return a success response
        int newID = Math.toIntExact(webPageService.findWebPageByTitle(title).get().getId());
        logger.info("Webpage uploaded successfully with ID: {}", newID);
        return ResponseEntity.ok("Webpage uploaded successfully with ID:" +newID);
    }

    private List<Tag> resolveTags(List<String> tagStringSet) {
        // Convert tag names to Tag entities
        List<Tag> convertedTags = new ArrayList<>();
        for (String tagName : tagStringSet) {
            Tag tag;
            if(!tagService.findByTagName(tagName).isPresent()) {
                // Create a new Tag entity if it doesn't exist
                tag = new Tag(tagName);
                tagService.save(tag);
            }
            tag = tagService.findByTagName(tagName).get();
            convertedTags.add(tag);
        }
        return convertedTags;
    }

    private boolean webPageIsPresent(String title) {
        return webPageService.findWebPageByTitle(title).isPresent();
    }

    private boolean isImageContentType(String contentType) {
        return contentType != null && (contentType.startsWith("image/jpeg") || contentType.startsWith("image/png")
        ||contentType.startsWith("image/gif"));
    }

    private boolean isTextContentType(String contentType) {
        // Add additional checks if needed
        return contentType != null && contentType.toLowerCase().startsWith("text/");
    }

    private boolean isValidSavedImagePath(int thumbnailID) {
        Optional<VisualSource> visualSource = visualSourceService.findVisualSource(thumbnailID);
        if(!visualSource.isPresent()) {
            return false;
        }

        String imagePath = visualSource.get().getImagePath();
        // If the image path given is blank or doesn't end with either .png or .jpg, then return false
        if (StringUtils.isBlank(imagePath) || !((imagePath.toLowerCase().endsWith(".png") || imagePath.toLowerCase().endsWith(".jpg") ||
                imagePath.toLowerCase().endsWith(".gif")))) {
            return false;
        }

        // Check if the file exists
        File file = new File(resourcesPath + imagePath);
        return file.exists();
    }

    private boolean isValidTextFilePath(String textFilePath) {
        // If the textFile provided is blank or doesn't end with .txt, then return false
        if (StringUtils.isBlank(textFilePath) || !textFilePath.toLowerCase().endsWith(".txt")) {
            return false;
        }

        // Check if the file exists
        File file = new File(resourcesPath + "/static/" + textFilePath);
        return file.exists();
    }
}
