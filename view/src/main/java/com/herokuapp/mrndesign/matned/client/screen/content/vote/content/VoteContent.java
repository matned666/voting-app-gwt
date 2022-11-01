package com.herokuapp.mrndesign.matned.client.screen.content.vote.content;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.herokuapp.mrndesign.matned.client.model.Model;
import com.herokuapp.mrndesign.matned.client.screen.ContentType;
import com.herokuapp.mrndesign.matned.client.screen.content.BaseContent;

public class VoteContent extends BaseContent {

    private final Model model;

    public VoteContent(Model model) {
        super(ContentType.VOTE);
        this.model = model;
        initDescription();
        initHorizontalPanel();
        add(new AddVoterPanel(model));
        getElement().setClassName("content");
    }

    private void initDescription() {
        HTML html = new HTML();
        html.setHTML("<h3>Voting application</h3>");
        add(html);
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
}
