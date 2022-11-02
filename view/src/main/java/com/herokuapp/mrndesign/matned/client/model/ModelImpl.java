package com.herokuapp.mrndesign.matned.client.model;

import com.herokuapp.mrndesign.matned.client.model.http.HttpRequester;
import com.herokuapp.mrndesign.matned.client.model.http.Requester;
import com.herokuapp.mrndesign.matned.client.model.mold.Candidate;
import com.herokuapp.mrndesign.matned.client.model.mold.Voter;
import com.herokuapp.mrndesign.matned.client.model.utils.DataObserver;
import com.herokuapp.mrndesign.matned.client.model.utils.VoteObserver;
import com.herokuapp.mrndesign.matned.client.model.utils.VotePossibilityObserver;
import com.herokuapp.mrndesign.matned.client.screen.Screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ModelImpl implements Model {
    private static final Logger logger = java.util.logging.Logger.getLogger("ModelImpl");

    private final List<Voter> voterList;
    private final List<Candidate> candidateList;
    private final Requester requester;
    private final List<VotePossibilityObserver> votePossibilityObservers = new ArrayList<>();
    private final List<DataObserver> dataGrids = new ArrayList<>();
    private Screen screen;

    public ModelImpl() {
        requester = new HttpRequester(this);
        voterList = new ArrayList<>();
        candidateList = new ArrayList<>();
        requester.requestVoters();
        requester.requestCandidates();
    }

    @Override
    public List<Voter> getAllVoters() {
        return voterList;
    }

    @Override
    public List<Candidate> getAllCandidates() {
        return candidateList;
    }

    @Override
    public boolean hasVoted(Long id) {
        return candidateList.stream().flatMap(c -> c.getListOfVotesIds().stream()).anyMatch(vID -> Objects.equals(vID, id));
    }

    @Override
    public void saveVoter(Voter voter) {
        startLoading();
        requester.saveVoter(voter);
    }

    @Override
    public void saveCandidate(Candidate candidate) {
        if (candidateList.contains(candidate)) {
            return;
        }
        startLoading();
        requester.saveCandidate(candidate);
    }

    private void refreshData() {
        notifyVotePossibility(VoteObserver.getInstance(this).isVoteLegal());
        dataGrids.forEach(DataObserver::onDataChange);
        screen.stopLoading();
    }

    @Override
    public void setSelectedVoter(Voter selected) {
        VoteObserver.getInstance(this).setSelectedVoter(selected);
        refreshData();
    }

    @Override
    public void notifyVotePossibility(boolean legal) {
        votePossibilityObservers.forEach(v -> v.setVotePossibility(legal));
    }

    @Override
    public void addDataGridObserver(DataObserver dataGrid) {
        startLoading();
        dataGrids.add(dataGrid);
    }

    @Override
    public void addVotePossibilityObserver(VotePossibilityObserver v) {
        startLoading();
        votePossibilityObservers.add(v);
    }

    @Override
    public void vote(Candidate candidate) {
        startLoading();
        VoteObserver.getInstance(this).setSelectedCandidate(candidate);
        requester.vote();
    }

    @Override
    public void removeVoter(Voter voter) {
        startLoading();
        List<Candidate> candidatesToRemove = candidateList.stream()
                .filter(c -> Objects.equals(c.getVoterId(), voter.getId()))
                .collect(Collectors.toList());
        if (!candidatesToRemove.isEmpty()) {
            candidatesToRemove.forEach(this::removeCandidate);
            return;
        }
        removeVoterVotes(voter);
        requester.removeVoter(voter);
    }

    private void removeVoterVotes(Voter voter) {
        candidateList.stream()
                .filter(c -> c.getListOfVotesIds()
                        .contains(voter.getId()))
                .forEach(c -> c.getListOfVotesIds().remove(voter.getId()));
    }

    @Override
    public void removeCandidate(Candidate candidate) {
        startLoading();
        requester.removeCandidate(candidate);
    }

    @Override
    public void onSaveVoterResultCallback(Voter voter) {
        voterList.add(voter);
        refreshData();
    }

    @Override
    public void onGetVotersResultCallback(List<Voter> voters) {
        voterList.clear();
        voterList.addAll(voters);
        refreshData();
    }

    @Override
    public void onVoteResultCallback(VoteObserver voteObserver) {
        voteObserver.vote();
        refreshData();
    }

    @Override
    public void onGetCandidatesResultCallback(List<Candidate> candidates) {
        candidateList.clear();
        candidateList.addAll(candidates);
        refreshData();
    }

    @Override
    public void onCandidateSaveResultCallback(Candidate candidate) {
        candidateList.add(candidate);
        refreshData();
    }

    @Override
    public void onRemoveVoterCallback(Voter voter) {
        voterList.remove(voter);
        refreshData();
    }

    @Override
    public void onRemoveCandidateCallback(Candidate candidate) {
        candidateList.remove(candidate);
        refreshData();
    }

    @Override
    public void onServerError(String message) {
        screen.showErrorMessage(message);
        refreshData();
    }

    @Override
    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    private void startLoading() {
        if (screen != null) {
            screen.startLoading();
        }
    }

}
