package com.herokuapp.mrndesign.matned.client.screen;

public interface ContentManager {

    void start();

    void setContent(ContentType contentType);

    Screen getScreen();
}
