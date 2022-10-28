package com.herokuapp.mrndesign.matned.client;

import com.google.gwt.core.client.EntryPoint;
import com.herokuapp.mrndesign.matned.client.screen.ContentManager;
import com.herokuapp.mrndesign.matned.client.screen.ContentManagerImpl;

public class App implements EntryPoint {

    @Override
    public void onModuleLoad() {
        ContentManager cm = new ContentManagerImpl();
        cm.start();
    }
}
