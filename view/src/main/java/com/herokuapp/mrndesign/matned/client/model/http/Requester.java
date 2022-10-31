package com.herokuapp.mrndesign.matned.client.model.http;

import com.herokuapp.mrndesign.matned.client.model.dto.Candidate;
import com.herokuapp.mrndesign.matned.client.model.dto.Voter;
import com.herokuapp.mrndesign.matned.client.model.utils.VoteObserver;

/**
 * Interface responsible for requests from repository
 */
public interface Requester {

    /**
     * POST request to <a href="http://localhost:8080/voters">http://localhost:8080/voters</a>
     *
     * @param voter
     */
    void saveVoter(Voter voter);

    /**
     * ROST request to <a href="http://localhost:8080/giveVote/0/0">http://localhost:8080/giveVote/voterId/candidateId</a>
     *
     * @param voteObserver
     */
    void vote(VoteObserver voteObserver);

    /**
     * GET request to <a href="http://localhost:8080/voters">http://localhost:8080/voters</a>
     */
    void requestVoters();

    /**
     * GET request to <a href="http://localhost:8080/candidates">http://localhost:8080/candidates</a>
     */
    void requestCandidates();

    /**
     * POST request to <a href="http://localhost:8080/candidates/0">http://localhost:8080/candidates/id</a>
     *
     * @param candidate
     */
    void saveCandidate(Candidate candidate);

    /**
     * DELETE request to <a href="http://localhost:8080/voter/0">http://localhost:8080/voter/id</a>
     *
     * @param voter
     */
    void removeVoter(Voter voter);

    /**
     * DELETE request to <a href="http://localhost:8080/candidate/0">http://localhost:8080/candidate/id</a>
     *
     * @param candidate
     */
    void removeCandidate(Candidate candidate);
}
