package com.herokuapp.mrndesign.matned.client.screen.content.vote.content;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.herokuapp.mrndesign.matned.client.model.Controller;
import com.herokuapp.mrndesign.matned.client.screen.ContentType;
import com.herokuapp.mrndesign.matned.client.screen.content.BaseContent;

public class VoteContent extends BaseContent {

    private final Controller controller;

    public VoteContent(Controller controller) {
        super(ContentType.VOTE);
        this.controller = controller;
        initDescription();
        initHorizontalPanel();
        add(new AddVoterPanel(controller));
        getElement().setClassName("content");
    }

    private void initDescription() {
        HTML html = new HTML();
        html.setHTML("<h3>Voting application</h3>");
        add(html);
    }

    private void initHorizontalPanel() {
        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.add(new VotersPanel(controller));
        horizontalPanel.add(new CandidatesPanel(controller));
        add(horizontalPanel);
    }

    @Override
    public Widget getWidget() {
        return this;
    }
}
