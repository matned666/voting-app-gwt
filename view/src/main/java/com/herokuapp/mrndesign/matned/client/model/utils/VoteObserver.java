package com.herokuapp.mrndesign.matned.client.model.utils;

import com.herokuapp.mrndesign.matned.client.model.Model;
import com.herokuapp.mrndesign.matned.client.model.dto.Candidate;
import com.herokuapp.mrndesign.matned.client.model.dto.Voter;
import io.reactivex.subjects.PublishSubject;

public class VoteObserver {

    private Voter selectedVoter;
    private Candidate selectedCandidate;

    private final PublishSubject<Boolean> onSelectionCorrect = PublishSubject.create();

    private final Model model;

    public VoteObserver(Model model) {
        this.model = model;
        onSelectionCorrect.subscribe(model::notifyVotePossibility);
    }

    public Voter getSelectedVoter() {
        return selectedVoter;
    }

    public void setSelectedVoter(Voter selectedVoter) {
        this.selectedVoter = selectedVoter;
        onSelectionCorrect.onNext(isVoteLegal());
    }

    public Candidate getSelectedCandidate() {
        return selectedCandidate;
    }

    public void setSelectedCandidate(Candidate selectedCandidate) {
        this.selectedCandidate = selectedCandidate;
        onSelectionCorrect.onNext(isVoteLegal());
    }

    public boolean isVoteLegal() {
        if (selectedVoter == null) {
            return false;
        }
        return !model.hasVoted(selectedVoter.getId());
    }
}
