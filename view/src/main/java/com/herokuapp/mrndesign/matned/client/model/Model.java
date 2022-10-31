package com.herokuapp.mrndesign.matned.client.model;

import com.herokuapp.mrndesign.matned.client.model.dto.Candidate;
import com.herokuapp.mrndesign.matned.client.model.dto.Voter;
import com.herokuapp.mrndesign.matned.client.model.utils.DataGridObserver;
import com.herokuapp.mrndesign.matned.client.model.utils.VotePossibilityObserver;

import java.util.List;

public interface Model {

    List<Voter> getAllVoters();

    List<Candidate> getAllCandidates();

    boolean hasVoted(Long id);

    void saveVoter(Voter voter);

    void saveCandidate(Candidate candidate);

    void setSelectedVoter(Voter selected);

    void notifyVotePossibility(boolean legal);

    void addDataGridObserver(DataGridObserver dataGrid);

    void addVotePossibilityObserver(VotePossibilityObserver v);

    void vote(Candidate candidate);

    void removeVoter(Voter voter);

    void removeCandidate(Candidate candidate);

    void onSaveVoterResultReceive(Voter voter);

    void onGetVotersResultReceive(List<Voter> voters);

    void onGetCandidatesResultReceive(List<Candidate> candidates);

    void onCandidateSaveResultReceive(Candidate candidate);
}
