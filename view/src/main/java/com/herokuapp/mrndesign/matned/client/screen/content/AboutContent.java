package com.herokuapp.mrndesign.matned.client.screen.content;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.herokuapp.mrndesign.matned.client.screen.ContentType;

public class AboutContent extends BaseContent {

    public AboutContent() {
        super(ContentType.ABOUT);
        initTitle();
        initDescription();
    }

    private void initTitle(){
        Label title = new Label();
        title.setText("ABOUT SCREEN");
        title.getElement().setClassName("title");
        this.add(title);
    }

    private void initDescription(){
        HTML html = new HTML();
        html.setHTML("<br>" +
                "<p>Developer matned666</p> " +
                "<p>Sample archetype for menu based programs</p>" +
                "<br>" +
                "<p></p> <br>" +
                "");
        html.getElement().setClassName("simple-centered-text");
        this.add(html);
    }


    @Override
    public Widget getWidget() {
        return this;
    }
}
