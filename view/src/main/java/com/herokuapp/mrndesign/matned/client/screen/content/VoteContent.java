package com.herokuapp.mrndesign.matned.client.screen.content;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.herokuapp.mrndesign.matned.client.screen.ContentType;

public class VoteContent extends BaseContent {

    public VoteContent() {
        super(ContentType.VOTE);
        initTitle();
        initDescription();
    }

    private void initTitle(){
        Label title = new Label();
        title.setText("HOME SCREEN");
        title.getElement().setClassName("title");
        this.add(title);
    }

    private void initDescription(){
        HTML html = new HTML();
        html.setHTML("" +
                "<p>This a sample Home page</p>" +
                "<p>Change whatever you wish.</p>" +
                "<br>" +
                "<p>Enjoy.</p> <br>" +
                "");
        html.getElement().setClassName("simple-centered-text");
        this.add(html);
    }

    @Override
    public Widget getWidget() {
        return this;
    }
}
