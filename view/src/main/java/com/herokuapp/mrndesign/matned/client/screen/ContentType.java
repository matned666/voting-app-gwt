package com.herokuapp.mrndesign.matned.client.screen;

import com.herokuapp.mrndesign.matned.client.screen.widget.MenuButtonWidget;

/**
 * Content type displayed on a screen
 */
public enum ContentType {
    VOTE("VOTE"),
    ABOUT("ABOUT ME"),
    CONTACT("CONTACT");

    private final String text;

    ContentType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public MenuButtonWidget getButton(ContentManager screenManager) {
        switch (this) {
            case ABOUT: return new MenuButtonWidget(screenManager, ABOUT);
            case VOTE: return new MenuButtonWidget(screenManager, VOTE);
            default: return new MenuButtonWidget(screenManager, CONTACT);
        }
    }

}
