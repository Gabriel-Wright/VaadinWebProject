package com.gabeWebTest.webTest.views.article;

import com.gabeWebTest.webTest.data.ArticleFormat;
import com.gabeWebTest.webTest.data.WebPage;
import com.gabeWebTest.webTest.services.WebPageService;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Route("article/{webPageId}")
public class ArticleView extends Div implements HasUrlParameter<Long> {

    @Autowired
    private final WebPageService webPageService;
    private WebPage webPage;
    public ArticleView(WebPageService webPageService) {
        this.webPageService = webPageService;
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long aLong) {
        webPage = webPageService.findWebPageById(aLong).orElse(null);
        add(webPage.getTitle());
    }
}
