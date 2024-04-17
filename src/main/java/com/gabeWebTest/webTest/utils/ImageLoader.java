package com.gabeWebTest.webTest.utils;

import com.gabeWebTest.webTest.data.visualSource.VisualSource;
import com.gabeWebTest.webTest.services.VisualSourceService;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.StreamResource;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Optional;

public class ImageLoader {

    private VisualSourceService visualSourceService;
    private final String BASE_PATH = "src/main/resources/";
    private final String DEFAULT_IMAGE_PATH = "img/thumbnails/memoriesOfMurderThumbnail.jpg";

    public StreamResource LOAD_IMAGE(int sourceID) {
        Optional<VisualSource> visualSourceOptional = visualSourceService.findVisualSource(sourceID);
        if (!visualSourceOptional.isPresent()) {
            return null;
        }
        StreamResource streamResource = new StreamResource(visualSourceOptional.get().getFileName(), () -> {
            try {
                return new FileInputStream(BASE_PATH+visualSourceOptional.get().getImagePath());
            } catch (FileNotFoundException  e) {
                e.printStackTrace();
                return null;
            }
        });
        return streamResource;
    }
//        if(visualSourceOptional.isPresent()) {
//            imagePath = visualSourceOptional.get().getImagePath();
//        } else {
//            imagePath =DEFAULT_IMAGE_PATH;
//        }
//
//        StreamResource streamResource = new StreamResource(imagePath, () -> getClass().getResourceAsStream(imagePath));
//
//        return new Image(streamResource, DEFAULT_IMAGE_PATH);


    public void setVisualSourceService(VisualSourceService visualSourceService) {
        this.visualSourceService = visualSourceService;
    }
}
