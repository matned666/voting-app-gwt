package com.herokuapp.mrndesign.matned.client.screen.content;

import com.google.gwt.user.client.ui.FlowPanel;
import com.herokuapp.mrndesign.matned.client.screen.ContentType;

public abstract class BaseContent extends FlowPanel implements Content{

    protected ContentType contentType;

    public BaseContent(ContentType contentType) {
        this.contentType = contentType;
        this.getElement().setClassName("content");
    }

    @Override
    public ContentType getContentType() {
        return contentType;
    }

    public void startLoading() {
//        EMPTY
    }

    public void stopLoading() {
//        EMPTY
    }
}
