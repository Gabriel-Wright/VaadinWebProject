package com.gabeWebTest.webTest.views.dashboard;

import com.gabeWebTest.webTest.data.Tag;
import com.gabeWebTest.webTest.services.TagService;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.provider.DataKeyMapper;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.data.renderer.Rendering;
import com.vaadin.flow.dom.Element;
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
        filterDropDown.setRenderer(createRenderer());
//        //tags have colors
//        for(Tag tag: tags) {
//            tag.getColor();
//

        filterDropDown.setAutoExpand(MultiSelectComboBox.AutoExpandMode.BOTH);
        // Add listener to the filter dropdown
        filterDropDown.addValueChangeListener(event -> {
            //set selected tags to these
            Set<Tag> selectedTags = event.getValue();
            updateDisplayedArticles();
        });

        return filterDropDown;
    }

    private Renderer<Tag> createRenderer() {
        return new ComponentRenderer<>(tag -> {
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            Icon icon = tag.getIcon().create();
            icon.getStyle().set("color", tag.getColorHex());
            Span tagSpan = new Span(tag.getTagName());
            //What i want to do is set the background color of the span to the value of tag.getColorHex()
            tagSpan.getStyle().set("background-color",tag.getColorHex());
            horizontalLayout.add(icon, tagSpan);
            return horizontalLayout;
        });
    }

    private void updateDisplayedArticles() {
        //Filter articles based on selected tags
        //WebPageService.findByTags(selectedTags);
        //Update display with filtered articles
        //updateArticleDisplay(filteredArticles);
    }

}
