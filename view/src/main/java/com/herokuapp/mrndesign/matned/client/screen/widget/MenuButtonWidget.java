package com.herokuapp.mrndesign.matned.client.screen.widget;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.herokuapp.mrndesign.matned.client.screen.ContentManager;
import com.herokuapp.mrndesign.matned.client.screen.ContentType;
import com.herokuapp.mrndesign.matned.client.screen.MenuButton;

public class MenuButtonWidget extends Button implements MenuButton {


    public MenuButtonWidget(ContentManager screenManager, ContentType contentType) {
        getElement().setInnerText(contentType.getText());
        getElement().setClassName("button not-selected");
        addClickHandler(event-> {
            screenManager.setContent(contentType);
            screenManager.getScreen().setSelectedButton(contentType);
        });
    }

    @Override
    public void select() {
        getElement().setClassName("button selected");
    }

    @Override
    public void deselect() {
        getElement().setClassName("button not-selected");
    }

    @Override
    public Widget getWidget(){
        return this;
    }
}
