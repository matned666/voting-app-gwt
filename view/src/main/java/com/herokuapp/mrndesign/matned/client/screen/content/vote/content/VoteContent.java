package com.herokuapp.mrndesign.matned.client.screen.content.vote.content;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.herokuapp.mrndesign.matned.client.model.Model;
import com.herokuapp.mrndesign.matned.client.screen.ContentType;
import com.herokuapp.mrndesign.matned.client.screen.content.BaseContent;
import com.herokuapp.mrndesign.matned.client.screen.widget.LoadingImage;

import java.util.logging.Logger;

public class VoteContent extends BaseContent {
    private static final Logger logger = java.util.logging.Logger.getLogger("VoteContent");

    private final Model model;
    private LoadingImage loadingImage;

    public VoteContent(Model model) {
        super(ContentType.VOTE);
        this.model = model;
        initDescription();
        initHorizontalPanel();
        add(new AddVoterPanel(model));
        getElement().setClassName("content");
        loadingImage = new LoadingImage();
        logger.info("loading img: " + loadingImage.toString());
        add(loadingImage);
    }

    private void initDescription() {
        HorizontalPanel panel = new HorizontalPanel();
        HTML html = new HTML();
        html.setHTML("<h3>Voting application</h3>");
        panel.add(html);
        add(panel);
    }

    private void initHorizontalPanel() {
        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.add(new VotersPanel(model));
        horizontalPanel.add(new CandidatesPanel(model));
        add(horizontalPanel);
    }

    @Override
    public Widget getWidget() {
        return this;
    }

    @Override
    public void startLoading() {
        loadingImage.show();
    }

    @Override
    public void stopLoading() {
        loadingImage.hide();
    }

}
