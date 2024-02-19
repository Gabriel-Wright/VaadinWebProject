package com.gabeWebTest.webTest.views.dashboard;

import com.gabeWebTest.webTest.data.Tag;
import com.gabeWebTest.webTest.services.TagService;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Component allows
@Component
public class TagFilter {

    private TagService tagService;
    private Set<Tag> selectedTags;
    private final String label = "Filter by topic";

    //Needed to update articles within
//    private ArticlePreviewDisplay articleDisplay;

    @Autowired
    public TagFilter(TagService tagService){
        selectedTags = new HashSet<>();
        this.tagService = tagService;
//        this.articleDisplay = articleDisplay;
    }

    public Set<Tag> getSelectedTags() {
        return selectedTags;
    }

    public MultiSelectComboBox<Tag> createTagFilterDropDown() {
        // Get all tags from TagService
        List<Tag> tags = tagService.findAllTags();

        MultiSelectComboBox<Tag> filterDropDown = new MultiSelectComboBox<>();
        filterDropDown.setLabel("Filter by topic");
        filterDropDown.setItemLabelGenerator(Tag::getTagName);
        filterDropDown.setItems(tags);
        filterDropDown.setAutoExpand(MultiSelectComboBox.AutoExpandMode.BOTH);
        // Add listener to the filter dropdown
        filterDropDown.addValueChangeListener(event -> {
            //set selected tags to these
            Set<Tag> selectedTags = event.getValue();
            updateDisplayedArticles();
        });

        return filterDropDown;
    }

    private void updateDisplayedArticles() {
        //Filter articles based on selected tags
        //WebPageService.findByTags(selectedTags);
        //Update display with filtered articles
        //updateArticleDisplay(filteredArticles);
    }

}
