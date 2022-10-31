package com.herokuapp.mrndesign.matned.client.screen;

/**
 * Manages screen content between pages
 */
public interface ContentManager {

    void start();

    void setContent(ContentType contentType);

    Screen getScreen();
}
