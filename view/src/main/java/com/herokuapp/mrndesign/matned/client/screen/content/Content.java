package com.herokuapp.mrndesign.matned.client.screen.content;

import com.google.gwt.user.client.ui.Widget;
import com.herokuapp.mrndesign.matned.client.screen.ContentType;

public interface Content {

    Widget getWidget();

    ContentType getContentType();
}
