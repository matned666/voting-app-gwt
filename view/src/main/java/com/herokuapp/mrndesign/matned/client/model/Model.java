package com.herokuapp.mrndesign.matned.client.model;

import com.herokuapp.mrndesign.matned.client.model.mold.Candidate;
import com.herokuapp.mrndesign.matned.client.model.mold.Voter;
import com.herokuapp.mrndesign.matned.client.model.utils.DataObserver;
import com.herokuapp.mrndesign.matned.client.model.utils.VoteObserver;
import com.herokuapp.mrndesign.matned.client.model.utils.VotePossibilityObserver;
import com.herokuapp.mrndesign.matned.client.screen.Screen;

import java.util.List;

/**
 * Model interface responsible for connection between frontend and backend
 */
public interface Model {

    /**
     * Gets all {@link Voter} from repository
     *
     * @return List
     */
    List<Voter> getAllVoters();

    /**
     * Gets all {@link Candidate} from repository
     *
     * @return List
     */
    List<Candidate> getAllCandidates();

    /**
     * Checks if a {@link Voter} with a given id is allowed to vote
     *
     * @param voterId Long
     * @return boolean check
     */
    boolean hasVoted(Long voterId);

    /**
     * Save
     *
     * @param voter {@link Voter}
     */
    void saveVoter(Voter voter);

    /**
     * Save
     *
     * @param candidate {@link Candidate}
     */
    void saveCandidate(Candidate candidate);

    /**
     * Sets currently selected Voter
     *
     * @param selected {@link Voter}
     */
    void setSelectedVoter(Voter selected);

    /**
     * Notifies the frontend elements that the selected {@link Voter} <br>
     * is allowed to vote
     *
     * @param legal
     */
    void notifyVotePossibility(boolean legal);

    /**
     * Adds dataGrid to the observation list {@link ModelImpl#dataGrids}
     *
     * @param dataGrid {@link DataObserver} interface
     */
    void addDataGridObserver(DataObserver dataGrid);

    /**
     * Adds frontend element to the observation list {@link ModelImpl#votePossibilityObservers}
     *
     * @param v {@link VotePossibilityObserver} interface
     */
    void addVotePossibilityObserver(VotePossibilityObserver v);

    /**
     * makes a vote
     *
     * @param candidate
     */
    void vote(Candidate candidate);

    /**
     * removes {@link Voter}
     *
     * @param voter
     */
    void removeVoter(Voter voter);

    /**
     * removes {@link Candidate}
     *
     * @param candidate
     */
    void removeCandidate(Candidate candidate);

    /**
     * Requester result callback
     *
     * @param voter {@link Voter}
     */
    void onSaveVoterResultCallback(Voter voter);

    /**
     * Requester result callback
     *
     * @param voters {@link Voter} List
     */
    void onGetVotersResultCallback(List<Voter> voters);

    void onVoteResultCallback(VoteObserver voteObserver);

    /**
     * Requester result callback
     *
     * @param candidates {@link Candidate} List
     */
    void onGetCandidatesResultCallback(List<Candidate> candidates);

    /**
     * Requester result callback
     *
     * @param candidate {@link Candidate}
     */
    void onCandidateSaveResultCallback(Candidate candidate);

    void onRemoveVoterCallback(Voter voter);

    /**
     * Requester result callback
     */
    void onRemoveCandidateCallback(Candidate candidate);

    /**
     * Server error callback
     */
    void onServerError(String message);

    /**
     * Initial screen set - for communication with view purposes
     *
     * @param screen Screen
     */
    void setScreen(Screen screen);

}
