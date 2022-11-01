package com.herokuapp.mrndesign.matned.client.model;

import com.herokuapp.mrndesign.matned.client.model.dto.Candidate;
import com.herokuapp.mrndesign.matned.client.model.dto.Voter;
import com.herokuapp.mrndesign.matned.client.model.utils.DataGridObserver;
import com.herokuapp.mrndesign.matned.client.model.utils.VotePossibilityObserver;
import com.herokuapp.mrndesign.matned.client.screen.Screen;

import java.util.List;

/**
 * Controller interface responsible for connection between frontend and backend
 */
public interface Controller {

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
     * Adds dataGrid to the observation list {@link ControllerImpl#dataGrids}
     *
     * @param dataGrid {@link DataGridObserver} interface
     */
    void addDataGridObserver(DataGridObserver dataGrid);

    /**
     * Adds frontend element to the observation list {@link ControllerImpl#votePossibilityObservers}
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

    /**
     * Requester result callback
     */
    void onRemoveCallback();

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
