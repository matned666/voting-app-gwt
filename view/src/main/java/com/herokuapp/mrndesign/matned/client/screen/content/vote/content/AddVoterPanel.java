package com.herokuapp.mrndesign.matned.client.screen.content.vote.content;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.herokuapp.mrndesign.matned.client.model.Model;
import com.herokuapp.mrndesign.matned.client.model.dto.Voter;

import java.util.logging.Logger;

public class AddVoterPanel extends DecoratorPanel {
    Logger logger = java.util.logging.Logger.getLogger("AddVoterPanel");

    private final Model model;
    final TextBox nameTextBox = new TextBox();
    final TextBox surnameTextBox = new TextBox();


    public AddVoterPanel(Model model) {
        this.model = model;
        VerticalPanel panel = new VerticalPanel();
        HTML html = new HTML();
        html.setHTML("<h3>Add new voter:</h3>");
        panel.add(html);
        panel.setSpacing(10);
        getTextBox(nameTextBox, "Name:", "nameTextBox", panel);
        getTextBox(surnameTextBox, "Surname:", "surnameTextBox", panel);
        panel.add(new Button("Submit", (ClickHandler) event -> submit()));
        setWidget(panel);
    }

    private void submit() {
        if (nameTextBox.getText().length() == 0 || surnameTextBox.getText().length() == 0) {
            Window.alert("Name and surname text boxes cannot be empty");
            return;
        }
        String name = nameTextBox.getText();
        String surname = surnameTextBox.getText();
        Voter voter = new Voter(name, surname);
        model.saveVoter(voter);
        logger.info("New voter added: " + name + " " + surname);
    }

    private static void getTextBox(TextBox textBox, String labelTxt, String textBoxName, VerticalPanel panel) {
        HorizontalPanel horizontalPanel = new HorizontalPanel();
        textBox.setWidth("300px");
        textBox.setName(textBoxName);
        textBox.getElement().setPropertyString("placeholder", labelTxt);
        horizontalPanel.add(textBox);
        panel.add(horizontalPanel);
    }

}
