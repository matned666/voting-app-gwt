package com.herokuapp.mrndesign.matned.client.model.http;

import com.herokuapp.mrndesign.matned.client.model.dto.Candidate;
import com.herokuapp.mrndesign.matned.client.model.dto.Voter;
import com.herokuapp.mrndesign.matned.client.model.utils.VoteObserver;

public interface HttpRequester {

    void saveVoter(Voter voter);

    void vote(VoteObserver voteObserver);

    void requestVoters();

    void requestCandidates();

    void saveCandidate(Candidate candidate);

    void removeVoter(Voter voter);

    void removeCandidate(Candidate candidate);
}
