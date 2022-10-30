package com.herokuapp.mrndesign.matned.client.model.http;

import com.herokuapp.mrndesign.matned.client.model.dto.Candidate;
import com.herokuapp.mrndesign.matned.client.model.dto.Voter;
import com.herokuapp.mrndesign.matned.client.model.utils.VoteObserver;

import java.util.List;

public interface HttpRequester {

    boolean hasVoted(Long id);

    Voter saveVoter(Voter voter);

    void vote(VoteObserver voteObserver);

    List<Voter> getVoters();

    List<Candidate> getCandidates();

    Candidate saveCandidate(Candidate candidate);

    void removeVoter(Voter voter);

    void removeCandidate(Candidate candidate);
}
