package com.herokuapp.mrndesign.matned.client.screen;

import com.google.gwt.user.client.ui.RootPanel;
import com.herokuapp.mrndesign.matned.client.model.Model;
import com.herokuapp.mrndesign.matned.client.model.ModelImpl;
import com.herokuapp.mrndesign.matned.client.screen.content.AboutContent;
import com.herokuapp.mrndesign.matned.client.screen.content.ContactContent;
import com.herokuapp.mrndesign.matned.client.screen.content.Content;
import com.herokuapp.mrndesign.matned.client.screen.content.vote.content.VoteContent;
import com.herokuapp.mrndesign.matned.client.screen.widget.ScreenWidget;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ContentManagerImpl implements ContentManager {

    private final Logger logger = Logger.getLogger(ContentManager.class.getName());

    private final Map<ContentType, Content> contentMapByType = new HashMap<>();
    private final Screen screen;
    private final Content initContent;

    public ContentManagerImpl() {
        Model model = new ModelImpl();
        initContent = new VoteContent(model);
        contentMapByType.put(ContentType.VOTE, initContent);
        screen = new ScreenWidget(this, initContent);
    }

    @Override
    public void start(){
        RootPanel.get().add(screen.getWidget());
        logger.info("Start");
    }

    @Override
    public void setContent(ContentType contentType) {
        Content content = contentMapByType.get(contentType);
        if (content == null) {
            content = createNewContent(contentType);
        }
        screen.setContent(content);
    }

    @Override
    public Screen getScreen() {
        return screen;
    }

    private Content createNewContent(ContentType contentType) {
        switch (contentType) {
            case ABOUT:
                return new AboutContent();
            case CONTACT:
                return new ContactContent();
            default:
                return initContent;
        }
    }

}

