//package com.gabeWebTest.webTest.views.dashboard;
//
//import com.vaadin.flow.component.html.Image;
//import com.vaadin.flow.component.html.Paragraph;
//import com.vaadin.flow.component.html.Span;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ArticlePreviewDisplay extends VerticalLayout {
//
//    private Image image;
//    private Span title;
//    private Paragraph previewText;
//
//    public ArticlePreviewDisplay(String imageUrl, String titleText, String preview) {
//        image = new Image(imageUrl, "ArticleImage");
//        image.setWidth("100%");
//
//        title = new Span(titleText);
//        title.getStyle().set("font-weight", "bold");
//
//        previewText = new Paragraph(preview);
//        previewText.getStyle().set("line-height", "1.5");
//        previewText.getStyle().set("overflow", "hidden");
//        previewText.getStyle().set("text-overflow", "ellipsis");
//        previewText.getStyle().set("white-space", "nowrap");
//        previewText.setWidth("100%"); // Ensure text does not overflow
//
//        add(image, title, previewText);
//        setWidth("300px"); // Set component width
//        setPadding(true);
//        setSpacing(true);
//    }
//}
