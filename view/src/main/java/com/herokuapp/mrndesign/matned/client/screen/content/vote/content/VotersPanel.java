package com.herokuapp.mrndesign.matned.client.screen.content.vote.content;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;
import com.herokuapp.mrndesign.matned.client.model.Model;
import com.herokuapp.mrndesign.matned.client.model.dto.Candidate;
import com.herokuapp.mrndesign.matned.client.model.dto.Voter;
import com.herokuapp.mrndesign.matned.client.model.utils.DataGridObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class VotersPanel extends DataGrid<Voter> implements DataGridObserver {
    private final ListDataProvider<Voter> dataProvider;
    Logger logger = java.util.logging.Logger.getLogger("VotersPanel");
    private final Model model;

    public VotersPanel(Model model) {
        this.model = model;
        setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
        initSelectionModel();
        initColumns();
        initData();
        setWidth("600px");
        setHeight("500px");
        model.addDataGridObserver(this);
        getElement().getStyle().setCursor(com.google.gwt.dom.client.Style.Cursor.POINTER);
        dataProvider = new ListDataProvider<>();
        dataProvider.addDataDisplay(this);
        dataProvider.getList().addAll(model.getAllVoters());
    }

    private void initSelectionModel() {
        final SingleSelectionModel<Voter> selectionModel = new SingleSelectionModel<>();
        setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(event -> {
            Voter selected = selectionModel.getSelectedObject();
            if (selected != null) {
                model.setSelectedVoter(selected);
            }
        });
    }

    private void initColumns() {
        initRemoveColumn();
        initNameColumn();
        initVoteColumn();
        initCandidateColumn();
    }

    private void initRemoveColumn() {
        Column<Voter, String> deleteColumn = new Column<>(new ButtonCell()) {
            @Override
            public String getValue(Voter voter) {
                return "x";
            }
        };
        deleteColumn.setFieldUpdater((index, voter, value) -> model.removeVoter(voter));
        addColumn(deleteColumn, "Remove");
    }

    private void initNameColumn() {
        TextColumn<Voter> nameColumn = new TextColumn<>() {
            @Override
            public String getValue(Voter voter) {
                return voter.getName() + " " + voter.getSurname();
            }
        };
        addColumn(nameColumn, "Voter's name");
    }

    private void initVoteColumn() {
        TextColumn<Voter> voteColumn = new TextColumn<>() {
            @Override
            public String getValue(Voter voter) {
                return model.hasVoted(voter.getId()) ? "VOTED" : null;
            }
        };

        addColumn(voteColumn, "Status");
    }

    private void initCandidateColumn() {
        Column<Voter, String> candidateColumn = new Column<>(new ButtonCell()) {
            @Override
            public String getValue(Voter voter) {
                return ">>";
            }

            @Override
            public void render(Cell.Context context, Voter object, SafeHtmlBuilder sb) {
                if (isCandidate(object)) {
                    return;
                }
                super.render(context, object, sb);
            }
        };
        candidateColumn.setFieldUpdater((index, voter, value) -> {
            Candidate c = new Candidate(voter.getId(), new ArrayList<>());
            model.saveCandidate(c);
        });
        addColumn(candidateColumn, "Be candidate");
    }

    private boolean isCandidate(Voter candidate) {
        return model.getAllCandidates().stream().anyMatch(c -> Objects.equals(c.getVoterId(), candidate.getId()));
    }

    private void initData() {
        List<Voter> voters = model.getAllVoters();
        setRowCount(voters.size(), true);
        setRowData(0, voters);
        logger.info("voters:" + voters.size());
    }

    @Override
    public void onDataChange() {
        List<Voter> list = dataProvider.getList();
        List<Voter> actualData = model.getAllVoters();
        if (list.size() != actualData.size()) {
            List<Voter> difference = actualData.stream()
                    .filter(v -> !list.contains(v))
                    .collect(Collectors.toList());
            if (difference.size() == 0) {
                difference = list.stream()
                        .filter(v -> !actualData.contains(v))
                        .collect(Collectors.toList());
                list.removeAll(difference);
            } else {
                list.addAll(difference);
            }
        }
        redraw();
    }

}
