package com.herokuapp.mrndesign.matned.client.screen;

import com.google.gwt.user.client.ui.Widget;
import com.herokuapp.mrndesign.matned.client.screen.content.Content;

public interface Screen {

    void setContent(Content content);

    Widget getWidget();

    void setSelectedButton(ContentType contentType);
}
