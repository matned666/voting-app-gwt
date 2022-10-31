package com.herokuapp.mrndesign.matned.service;

import com.herokuapp.mrndesign.matned.dto.CandidateDTO;
import com.herokuapp.mrndesign.matned.dto.VoterDTO;
import com.herokuapp.mrndesign.matned.model.Candidate;
import com.herokuapp.mrndesign.matned.model.Voter;
import com.herokuapp.mrndesign.matned.repository.CandidateRepository;
import com.herokuapp.mrndesign.matned.repository.VoterRepository;
import com.herokuapp.mrndesign.matned.service.exception.CandidateNotFoundException;
import com.herokuapp.mrndesign.matned.service.exception.VoterNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class VoterService {

    private final VoterRepository voterRepository;
    private final CandidateRepository candidateRepository;

    public VoterService(VoterRepository voterRepository, CandidateRepository candidateRepository) {
        this.voterRepository = voterRepository;
        this.candidateRepository = candidateRepository;
    }

    public List<VoterDTO> findAll(){
        return convertEntityToUserDTOList(voterRepository.findAll());
    }

    public VoterDTO findById(Long id) {return VoterDTO.apply(voterRepository.findById(id).orElseThrow(VoterNotFoundException::new));}

    public VoterDTO add(VoterDTO dto){
        return VoterDTO.apply(voterRepository.save(VoterDTO.applyNew(dto)));
    }

    public void deleteVoter(Long id) {
        voterRepository.deleteById(id);
    }

    List<VoterDTO> convertEntityToUserDTOList(List<Voter> all) {
        return all.stream()
                .map(VoterDTO::apply)
                .collect(Collectors.toList());
    }

    public void vote(Long candidateDTO, Long voterId) {
        Voter voter = voterRepository.findById(voterId).orElseThrow(VoterNotFoundException::new);
        Candidate candidate = candidateRepository.findById(candidateDTO).orElseThrow(CandidateNotFoundException::new);
        candidate.getVoters().add(voter);
        CandidateDTO.apply(candidateRepository.save(candidate));
    }

    public Boolean hasVoted(Long voterId) {
        List<Candidate> candidates = candidateRepository.findAll();
        return candidates.stream()
                .flatMap(c -> c.getVoters().stream())
                .anyMatch(voter -> Objects.equals(voter.getId(), voterId));
    }
}
