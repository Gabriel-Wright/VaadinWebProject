package com.gabeWebTest.webTest.controllers;

import com.gabeWebTest.webTest.data.visualSource.VisualSource;
import com.gabeWebTest.webTest.data.visualSource.VisualSourceRepository;
import com.gabeWebTest.webTest.data.webPage.WebPage;
import com.gabeWebTest.webTest.data.webPage.WebPageRepository;
import com.gabeWebTest.webTest.data.webPage.tags.Tag;
import com.gabeWebTest.webTest.data.webPage.tags.TagRepository;
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
    private final WebPageRepository webPageRepository;
    private final TagRepository tagRepository;
    private final VisualSourceRepository visualSourceRepository;
    private final VisualSourceService visualSourceService;
    private final String resourcesPath = "src/main/resources/static";

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    public UploadController(WebPageService webPageService, WebPageRepository webPageRepository, TagRepository tagRepository, VisualSourceRepository visualSourceRepository, VisualSourceService visualSourceService) {
        this.webPageService = webPageService;
        this.webPageRepository = webPageRepository;
        this.tagRepository = tagRepository;
        this.visualSourceRepository = visualSourceRepository;
        this.visualSourceService = visualSourceService;
    }

    @PostMapping("/text")
    public ResponseEntity<String> uploadText(@RequestParam("text") MultipartFile textFile) throws IOException {
        String fileName = textFile.getOriginalFilename();
        if(StringUtils.isBlank(fileName)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No file presented");
        }
        // Check if the content type is appropriate for an image
        // Check if the content type is appropriate for a text file
        if (!isTextContentType(textFile.getContentType())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file type. Only text files are allowed.");
        }
        // Save the text file to the destination
        saveTextToDest(textFile);
        return ResponseEntity.ok("Text file:" + fileName + " uploaded successfully");
    }

    @PostMapping("/images")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile imageFile) throws IOException {
        String fileName = imageFile.getOriginalFilename();
        if(StringUtils.isBlank(fileName)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No file presented");
        }
        String formattedFileName = "/static/img/" + fileName;
        if(visualSourceRepository.findByImagePath(formattedFileName).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Image already exists under path:"+fileName);
        }
        // Check if the content type is appropriate for an image
        if (!isImageContentType(imageFile.getContentType())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file type. Only image files are allowed.");
        }
        saveImageToDest(imageFile);
        visualSourceRepository.save(new VisualSource(formattedFileName));
        int newID = visualSourceRepository.findByImagePath(formattedFileName).get().getId();
        return ResponseEntity.ok("Image:" + fileName+" uploaded successfully with new ID: "+newID);
    }

    @PostMapping("/article")
    public ResponseEntity<String> uploadArticle(@RequestParam("title") String title,
                                              @RequestParam("article_text_path") String articleTextPath,
                                              @RequestParam("thumbnail_path") int thumbnailID,
                                              @RequestParam("article_preview_text") String previewText,
                                              @RequestParam("tags") List<String> tags) {
        if(StringUtils.isBlank(title)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("WebPage title cannot be blank");
        }
        if(webPageIsPresent(title)) {
            // Return a response indicating that the webpage already exists
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Webpage with title '" + title + "' already exists");
        }
        if(!isValidSavedImagePath(thumbnailID)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No thumbnail under that path found");
        }
        if(!isValidTextFilePath(articleTextPath)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No txt file under that path found");
        }
        // Proceed with creating and saving the new webpage
        WebPage uploadArticle = new WebPage(title, articleTextPath, thumbnailID, previewText, resolveTags(tags));
        webPageRepository.save(uploadArticle);

        // Return a success response
        int newID = Math.toIntExact(webPageRepository.findByTitle(title).get().getId());
        return ResponseEntity.ok("Webpage uploaded successfully with ID:" +newID);
    }

    private List<Tag> resolveTags(List<String> tagStringSet) {
        // Convert tag names to Tag entities
        List<Tag> convertedTags = new ArrayList<>();
        for (String tagName : tagStringSet) {
            Tag tag = tagRepository.findByTagName(tagName);
            if (tag == null) {
                // Create a new Tag entity if it doesn't exist
                tag = new Tag(tagName);
                tag = tagRepository.save(tag);
            }
            convertedTags.add(tag);
        }
        return convertedTags;
    }

    private boolean webPageIsPresent(String title) {
        return webPageRepository.findByTitle(title).isPresent();
    }

    private boolean isImageContentType(String contentType) {
        return contentType != null && (contentType.startsWith("image/jpeg") || contentType.startsWith("image/png"));
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
        if (StringUtils.isBlank(imagePath) || !((imagePath.toLowerCase().endsWith(".png") || imagePath.toLowerCase().endsWith(".jpg")))) {
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
        File file = new File(resourcesPath + textFilePath);
        return file.exists();
    }
}
