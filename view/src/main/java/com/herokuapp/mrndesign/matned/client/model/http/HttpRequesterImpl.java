package com.herokuapp.mrndesign.matned.client.model.http;

import com.herokuapp.mrndesign.matned.client.model.Model;
import com.herokuapp.mrndesign.matned.client.model.dto.Candidate;
import com.herokuapp.mrndesign.matned.client.model.dto.Voter;
import com.herokuapp.mrndesign.matned.client.model.utils.VoteObserver;

import java.util.List;

public class HttpRequesterImpl implements HttpRequester {

    private final Model model;

    public HttpRequesterImpl(Model model) {
        this.model = model;
    }

    @Override
    public boolean hasVoted(Long id) {
        return true;
    }

    @Override
    public Voter saveVoter(Voter voter) {

        return voter;
    }

    @Override
    public void vote(VoteObserver voteObserver) {

    }

    @Override
    public List<Voter> getVoters() {
        return null;
    }

    @Override
    public List<Candidate> getCandidates() {
        return null;
    }

    @Override
    public Candidate saveCandidate(Candidate candidate) {

        return candidate;
    }


}
