package com.gabeWebTest.webTest.utils;

import com.gabeWebTest.webTest.data.visualSource.VisualSource;
import com.gabeWebTest.webTest.services.VisualSourceService;
import com.vaadin.flow.component.html.Image;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ImageLoader {

    private VisualSourceService visualSourceService;
    private final String DEFAULT_IMAGE_PATH = "img/thumbnails/memoriesOfMurderThumbnail.jpg";

    public Image LOAD_IMAGE(int sourceID) {
        Optional<VisualSource> visualSourceOptional = visualSourceService.findVisualSource(sourceID);
        String imagePath;
        if(visualSourceOptional.isPresent()) {
            imagePath = visualSourceOptional.get().getImagePath();
        } else {
            imagePath =DEFAULT_IMAGE_PATH;
        }
        return new Image(imagePath, imagePath);

    }

    public void setVisualSourceService(VisualSourceService visualSourceService) {
        this.visualSourceService = visualSourceService;
    }
}
