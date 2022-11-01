package com.herokuapp.mrndesign.matned.client.screen.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Image;

public class LoadingImage extends Image {

    public LoadingImage() {
        setUrl(GWT.getModuleBaseURL() + "img/loading.gif");
        setHeight("100px");
        setWidth("100px");
        setVisible(false);
    }

    public void show() {
        setVisible(true);
    }

    public void hide() {
        setVisible(false);
    }
}
