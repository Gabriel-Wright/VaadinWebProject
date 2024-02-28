package com.gabeWebTest.webTest.views.dashboard;

import com.gabeWebTest.webTest.data.Tag;
import com.gabeWebTest.webTest.services.TagService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
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

    private TagFilterListener tagFilterListener;
    //Needed to update articles within
//    private ArticlePreviewDisplay articleDisplay;

    @Autowired
    public TagFilter(TagService tagService){
        selectedTags = new HashSet<>();
        this.tagService = tagService;
//        this.articleDisplay = articleDisplay;
    }

    public void setTagFilterListener(TagFilterListener listener) {
        this.tagFilterListener = listener;
    }

    public Set<Tag> getSelectedTags() {
        return selectedTags;
    }

    public HorizontalLayout createTagFilterDropDown() {
        // Get all tags from TagService
        List<Tag> tags = tagService.findAllTags();

        MultiSelectComboBox<Tag> filterDropDown = new MultiSelectComboBox<>();
        filterDropDown.setLabel("Filter by topic");
        filterDropDown.setItemLabelGenerator(Tag::getTagName);
        filterDropDown.setItems(tags);
        filterDropDown.setRenderer(createRenderer());
        filterDropDown.getElement().getStyle().set("background-color", "transparent");
        filterDropDown.getElement().getStyle().set("box-shadow", "none");

//        //tags have colors
//        for(Tag tag: tags) {
//            tag.getColor();
//

        filterDropDown.setAutoExpand(MultiSelectComboBox.AutoExpandMode.BOTH);
        // Add listener to the filter dropdown - switching this to a confirm button
        // Create a button for confirming the selection
        Button confirmButton = new Button("Confirm");
        confirmButton.addClickListener(event -> {
             selectedTags = filterDropDown.getValue();
//            System.out.println(selectedTags.toString());
            if (tagFilterListener != null) {
                tagFilterListener.onTagFilterChanged(selectedTags);
            }
        });

        // Create a layout to hold the dropdown and the confirm button
        HorizontalLayout layout = new HorizontalLayout(filterDropDown, confirmButton);

        return layout;
    }

    private Renderer<Tag> createRenderer() {
        return new ComponentRenderer<>(Tag::createTagComponent);
    }
}
