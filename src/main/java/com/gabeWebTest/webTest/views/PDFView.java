package com.gabeWebTest.webTest.views;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("pdf-viewer")
public class PDFView extends VerticalLayout {

    public PDFView() {
        Html pdfViewer = new Html("<embed src='pdf/patientGabrielWrightSickNote.pdf' type='application/pdf' width='50%' height='600px' />");
        add(pdfViewer);
    }
}
