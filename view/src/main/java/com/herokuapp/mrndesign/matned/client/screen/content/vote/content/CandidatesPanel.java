package com.herokuapp.mrndesign.matned.client.screen.content.vote.content;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.ListDataProvider;
import com.herokuapp.mrndesign.matned.client.model.Model;
import com.herokuapp.mrndesign.matned.client.model.mold.Candidate;
import com.herokuapp.mrndesign.matned.client.model.mold.Voter;
import com.herokuapp.mrndesign.matned.client.model.utils.DataObserver;
import com.herokuapp.mrndesign.matned.client.model.utils.VotePossibilityObserver;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CandidatesPanel extends DataGrid<Candidate> implements DataObserver {
    private static final Logger logger = java.util.logging.Logger.getLogger("CandidatesPanel");

    private final Model model;
    private final ListDataProvider<Candidate> dataProvider;


    public CandidatesPanel(Model model) {
        this.model = model;
        setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
        initColumns();
        initData();
        setWidth("600px");
        setHeight("500px");
        model.addDataGridObserver(this);
        logger.info("Candidates panel created");
        getElement().getStyle().setCursor(com.google.gwt.dom.client.Style.Cursor.POINTER);
        dataProvider = new ListDataProvider<>();
        dataProvider.addDataDisplay(this);
        dataProvider.getList().addAll(model.getAllCandidates());
    }

    private void initColumns() {
        initCandidateNameColumn();
        initNumberOfVotesColumn();
        initVoteButtonColumn();
        initRemoveColumn();
    }

    private void initCandidateNameColumn() {
        TextColumn<Candidate> nameColumn = new TextColumn<>() {
            @Override
            public String getValue(Candidate candidateDTO) {
                Voter voter = model.getAllVoters()
                        .stream()
                        .filter(v -> Objects.equals(v.getId(), candidateDTO.getVoterId()))
                        .findFirst()
                        .orElse(null);
                return voter != null ? voter.getName() + " " + voter.getSurname()
                        : null;
            }
        };
        addColumn(nameColumn, "Candidate's name");
    }

    private void initNumberOfVotesColumn() {
        TextColumn<Candidate> voteColumn = new TextColumn<>() {
            @Override
            public String getValue(Candidate candidate) {
                int votes = candidate.getListOfVotesIds().size();
                return votes == 0 ? "none" : String.valueOf(votes);
            }
        };
        addColumn(voteColumn, "Votes");
    }

    private void initVoteButtonColumn() {
        Column<Candidate, String> voteOnCandidateColumn = new Column<>(new ObservingButtonCell(model)) {
            @Override
            public String getValue(Candidate candidate) {
                return "Vote";
            }
        };
        voteOnCandidateColumn.setFieldUpdater((index, candidate, value) -> model.vote(candidate));
        addColumn(voteOnCandidateColumn, "Vote");
    }

    private void initRemoveColumn() {
        Column<Candidate, String> deleteColumn = new Column<>(new ButtonCell()) {
            @Override
            public String getValue(Candidate voter) {
                return "x";
            }
        };
        deleteColumn.setFieldUpdater((index, candidate, value) -> model.removeCandidate(candidate));

        addColumn(deleteColumn, "Remove");
    }

    private void initData() {
        List<Candidate> candidates = model.getAllCandidates();
        setRowCount(candidates.size(), true);
        setRowData(0, candidates);
    }

    @Override
    public void onDataChange() {
        List<Candidate> list = dataProvider.getList();
        List<Candidate> actualData = model.getAllCandidates();
        if (list.size() != actualData.size()) {
            List<Candidate> difference = actualData.stream()
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

    private static class ObservingButtonCell extends ButtonCell implements VotePossibilityObserver {

        private boolean enabled;

        public ObservingButtonCell(Model model) {
            model.addVotePossibilityObserver(this);
        }

        @Override
        public void render(Context context, String data, SafeHtmlBuilder sb) {
            sb.appendHtmlConstant("<button type=\"button\" tabindex=\"-1\"");
            sb.appendHtmlConstant(enabled ? ">" : " disabled >");
            sb.appendHtmlConstant("Vote</button>");
        }

        @Override
        public void setVotePossibility(boolean enabled) {
            this.enabled = enabled;
        }
    }

}
