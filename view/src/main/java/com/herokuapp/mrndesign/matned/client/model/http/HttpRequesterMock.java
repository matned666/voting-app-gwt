package com.herokuapp.mrndesign.matned.client.model.http;

import com.herokuapp.mrndesign.matned.client.model.Model;
import com.herokuapp.mrndesign.matned.client.model.dto.Candidate;
import com.herokuapp.mrndesign.matned.client.model.dto.Voter;
import com.herokuapp.mrndesign.matned.client.model.utils.VoteObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class HttpRequesterMock implements HttpRequester {
    private static Logger logger = java.util.logging.Logger.getLogger("HttpRequesterMock");

    List<Voter> voters = new ArrayList<>();
    List<Candidate> candidates = new ArrayList<>();
    private final Model model;

    public HttpRequesterMock(Model model) {
        this.model = model;
        voters.add(new Voter(1L, "Mario", "Gonzales"));
        voters.add(new Voter(2L, "Danuta", "Krawczyk"));
        voters.add(new Voter(3L, "Jurek", "Dudek"));
        voters.add(new Voter(4L, "Stefan", "Kowalski"));
        voters.add(new Voter(5L, "John", "Doe"));
        voters.add(new Voter(6L, "Azja", "John"));
        voters.add(new Voter(7L, "Roman", "Niedopałek"));
        voters.add(new Voter(8L, "Kasia", "Guarana"));
        voters.add(new Voter(9L, "Dudu", "Smith"));
        voters.add(new Voter(10L, "John", "John"));
        voters.add(new Voter(11L, "Mark", "Tort"));
        voters.add(new Voter(12L, "Louis", "Nort"));
        voters.add(new Voter(13L, "Mateusz", "Popo"));
        voters.add(new Voter(14L, "Dorota", "Skawina"));

        candidates.add(new Candidate(1L, new ArrayList<>()));
        candidates.add(new Candidate(2L, new ArrayList<>()));
    }

    @Override
    public boolean hasVoted(Long id) {
        return voters.stream().anyMatch(voter ->
                candidates.stream()
                        .flatMap(c -> c.getListOfVotesIds().stream())
                        .anyMatch(voterId -> Objects.equals(voterId, id)));
    }

    @Override
    public Voter saveVoter(Voter voter) {
        voter.setId((long) (voters.size() + 1));
        voters.add(voter);
        logger.info("Voters size:" + voters.size());
        return voter;
    }

    @Override
    public void vote(VoteObserver voteObserver) {
        if (voteObserver.isVoteLegal()) {
            Candidate candidate = voteObserver.getSelectedCandidate();
            Voter voter = voteObserver.getSelectedVoter();
            logger.info("VOTER:" + String.valueOf(voter.getId()));
            candidate.getListOfVotesIds().add(voter.getId());
            model.notifyVotePossibility(false);
        }
    }

    @Override
    public List<Voter> getVoters() {
        return voters;
    }

    @Override
    public List<Candidate> getCandidates() {
        return candidates;
    }

    @Override
    public Candidate saveCandidate(Candidate candidate) {
        candidates.add(candidate);
        return candidate;
    }

    @Override
    public void removeVoter(Voter voter) {
        voters.remove(voter);
    }

    @Override
    public void removeCandidate(Candidate candidate) {
        candidates.remove(candidate);
    }


}
