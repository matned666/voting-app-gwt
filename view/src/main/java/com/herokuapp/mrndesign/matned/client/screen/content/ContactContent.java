package com.herokuapp.mrndesign.matned.client.screen.content;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.herokuapp.mrndesign.matned.client.screen.ContentType;

public class ContactContent extends BaseContent {

    public ContactContent() {
        super(ContentType.CONTACT);
        initTitle();
        initDescription();
    }

    private void initTitle(){
        HTML html = new HTML();
        html.setHTML("<h3>Contact information</h3>");
        add(html);
    }

    private void initDescription(){
        HTML html = new HTML();
        html.setHTML("<br>" +
                "<p>Developer address and contact</p> " +
                "<p>+48 666 666 666</p>" +
                "<p>info@info-mail.com</p>" +
                "<p>Wrocław</p> <br>" +
                "");
        html.getElement().setClassName("simple-centered-text");
        this.add(html);
    }

    @Override
    public Widget getWidget() {
        return this;
    }
}
