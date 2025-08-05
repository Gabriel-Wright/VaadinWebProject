package com.gabeWebTest.webTest.utils;

import com.gabeWebTest.webTest.data.visualSource.VisualSource;
import com.gabeWebTest.webTest.services.VisualSourceService;
import com.vaadin.flow.server.StreamResource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Optional;

public class ImageLoader {

    private VisualSourceService visualSourceService;
    private final String BASE_PATH = "src/main/resources/";
    private final String DEFAULT_IMAGE_PATH = "img/thumbnails/memoriesOfMurderThumbnail.jpg";

    public StreamResource LOAD_IMAGE(int sourceID) {
        Optional<VisualSource> visualSourceOptional = visualSourceService.findVisualSource(sourceID);
        if (!visualSourceOptional.isPresent()) {
            return returnDefaultResource();
        }
        StreamResource streamResource = new StreamResource(visualSourceOptional.get().getFileName(), () -> {
            try {
                return new FileInputStream(BASE_PATH+visualSourceOptional.get().getImagePath());
            } catch (FileNotFoundException  e) {
                //Need to figure out file for this one possible error - what happens if there is no file at the location.
                e.printStackTrace();
                return null;
            }
        });
        return streamResource;
    }

    private StreamResource returnDefaultResource() {
//        VisualSource defaultVisualSource = visualSourceService.findVisualSource(0).get();
        StreamResource streamResource = new StreamResource("memoriesOfMurderThumbnail.jpg", () -> {
            try {
                return new FileInputStream(BASE_PATH + DEFAULT_IMAGE_PATH);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        });
        return streamResource;
    }

    public void setVisualSourceService(VisualSourceService visualSourceService) {
        this.visualSourceService = visualSourceService;
    }
}
