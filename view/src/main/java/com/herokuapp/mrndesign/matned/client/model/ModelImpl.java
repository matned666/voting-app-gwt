package com.herokuapp.mrndesign.matned.client.model;

import com.herokuapp.mrndesign.matned.client.model.dto.Candidate;
import com.herokuapp.mrndesign.matned.client.model.dto.Voter;
import com.herokuapp.mrndesign.matned.client.model.http.HttpRequester;
import com.herokuapp.mrndesign.matned.client.model.http.HttpRequesterImpl;
import com.herokuapp.mrndesign.matned.client.model.utils.DataGridObserver;
import com.herokuapp.mrndesign.matned.client.model.utils.VoteObserver;
import com.herokuapp.mrndesign.matned.client.model.utils.VotePossibilityObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class ModelImpl implements Model {
    private static final Logger logger = java.util.logging.Logger.getLogger("ModelImpl");

    private final List<Voter> voterList;
    private final List<Candidate> candidateList;
    private final HttpRequester requester;
    private final VoteObserver voteObserver;
    private final List<VotePossibilityObserver> votePossibilityObservers = new ArrayList<>();
    private final List<DataGridObserver> dataGrids = new ArrayList<>();


    public ModelImpl() {
        requester = new HttpRequesterImpl(this);
        voterList = new ArrayList<>();
        candidateList = new ArrayList<>();
        requester.requestVoters();
        requester.requestCandidates();
        voteObserver = new VoteObserver(this);
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
        requester.saveVoter(voter);
    }

    @Override
    public void saveCandidate(Candidate candidate) {
        if (candidateList.contains(candidate)) {
            return;
        }
        requester.saveCandidate(candidate);
    }

    private void refreshData() {
        dataGrids.forEach(DataGridObserver::onDataChange);
    }

    @Override
    public void setSelectedVoter(Voter selected) {
        voteObserver.setSelectedVoter(selected);
        refreshData();
    }

    @Override
    public void notifyVotePossibility(boolean legal) {
        votePossibilityObservers.forEach(v -> v.setVotePossibility(legal));
    }

    @Override
    public void addDataGridObserver(DataGridObserver dataGrid) {
        dataGrids.add(dataGrid);
    }

    @Override
    public void addVotePossibilityObserver(VotePossibilityObserver v) {
        votePossibilityObservers.add(v);
    }

    @Override
    public void vote(Candidate candidate) {
        voteObserver.setSelectedCandidate(candidate);
        requester.vote(voteObserver);
        voteObserver.vote();
        notifyVotePossibility(false);
        refreshData();
    }

    @Override
    public void removeVoter(Voter voter) {
        requester.removeVoter(voter);
        voterList.remove(voter);
        refreshData();
    }

    @Override
    public void removeCandidate(Candidate candidate) {
        requester.removeCandidate(candidate);
        candidateList.remove(candidate);
        refreshData();
    }

    @Override
    public void onSaveVoterResultReceive(Voter voter) {
        voterList.add(voter);
        refreshData();
    }

    @Override
    public void onGetVotersResultReceive(List<Voter> voters) {
        voterList.clear();
        voterList.addAll(voters);
        refreshData();
    }

    @Override
    public void onGetCandidatesResultReceive(List<Candidate> candidates) {
        candidateList.clear();
        candidateList.addAll(candidates);
        refreshData();
    }

    @Override
    public void onCandidateSaveResultReceive(Candidate candidate) {
        candidateList.add(candidate);
        refreshData();
    }

}
