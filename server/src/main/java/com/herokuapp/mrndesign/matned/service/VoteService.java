package com.herokuapp.mrndesign.matned.service;

import com.herokuapp.mrndesign.matned.dto.CandidateDTO;
import com.herokuapp.mrndesign.matned.dto.VoteDTO;
import com.herokuapp.mrndesign.matned.model.Candidate;
import com.herokuapp.mrndesign.matned.model.Vote;
import com.herokuapp.mrndesign.matned.model.Voter;
import com.herokuapp.mrndesign.matned.repository.CandidateRepository;
import com.herokuapp.mrndesign.matned.repository.VoteRepository;
import com.herokuapp.mrndesign.matned.repository.VoterRepository;
import com.herokuapp.mrndesign.matned.service.exception.CandidateNotFoundException;
import com.herokuapp.mrndesign.matned.service.exception.VoteGivenException;
import com.herokuapp.mrndesign.matned.service.exception.VoterNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoteService {

    private final CandidateRepository candidateRepository;
    private final VoterRepository voterRepository;
    private final VoteRepository voteRepository;

    public VoteService(CandidateRepository candidateRepository, VoterRepository voterRepository, VoteRepository voteRepository) {
        this.candidateRepository = candidateRepository;
        this.voterRepository = voterRepository;
        this.voteRepository = voteRepository;
    }

    public VoteDTO vote(Long candidateId, Long voterId) {
        if (voteRepository.isVoteGiven(voterId)) {
            throw new VoteGivenException();
        }
        Voter voter = voterRepository.findById(voterId).orElseThrow(VoterNotFoundException::new);
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow(CandidateNotFoundException::new);
        Vote vote = new Vote(candidate, voter);
        return VoteDTO.apply(voteRepository.save(vote));
    }

    public List<VoteDTO> findAllVotes() {
        return convertVotesToDTOList(voteRepository.findAll());
    }

    public List<VoteDTO> findAllVotesForCandidate(Long candidateId) {
        Candidate c = candidateRepository.findById(candidateId).orElseThrow(CandidateNotFoundException::new);
        return convertVotesToDTOList(voteRepository.findAllForCandidate(c.getId()));
    }

    public CandidateDTO voterVote(Long voterId) {
        return CandidateDTO.apply(voteRepository.findByVoter(voterId).orElseThrow(CandidateNotFoundException::new));
    }

    public void deleteVote(Long voteId) {
        voteRepository.deleteById(voteId);
    }

    List<VoteDTO> convertVotesToDTOList(List<Vote> all) {
        return all.stream().map(VoteDTO::apply).collect(Collectors.toList());
    }

}
