package com.gabeWebTest.webTest.views.dashboard;

import com.gabeWebTest.webTest.data.Tag;
import com.gabeWebTest.webTest.services.FadeOutCompletionEvent;
import com.gabeWebTest.webTest.services.TagService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Component allows
@Component
public class TagFilter {

    private TagService tagService;
    private Set<Tag> selectedTags;

    private TagFilterListener tagFilterListener;

    @Autowired
    public TagFilter(TagService tagService){
        selectedTags = new HashSet<>();
        this.tagService = tagService;
    }

    public void setTagFilterListener(TagFilterListener listener) {
        this.tagFilterListener = listener;
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
        filterDropDown.setRenderer(createRenderer());
        filterDropDown.getElement().getStyle().set("background-color", "transparent");
        filterDropDown.getElement().getStyle().set("box-shadow", "none");

        filterDropDown.addSelectionListener(event -> {
            selectedTags = event.getValue();

            if (tagFilterListener != null) {
                tagFilterListener.onTagFilterChanged(selectedTags);
            }
        });

        filterDropDown.setAutoExpand(MultiSelectComboBox.AutoExpandMode.BOTH);

        return filterDropDown;
    }

    private Renderer<Tag> createRenderer() {
        return new ComponentRenderer<>(Tag::createTagComponent);
    }

}
