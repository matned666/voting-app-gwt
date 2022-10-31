package com.herokuapp.mrndesign.matned.service;

import com.herokuapp.mrndesign.matned.dto.CandidateDTO;
import com.herokuapp.mrndesign.matned.model.Candidate;
import com.herokuapp.mrndesign.matned.model.Voter;
import com.herokuapp.mrndesign.matned.repository.CandidateRepository;
import com.herokuapp.mrndesign.matned.repository.VoterRepository;
import com.herokuapp.mrndesign.matned.service.exception.VoterNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final VoterRepository voterRepository;

    public CandidateService(CandidateRepository candidateRepository, VoterRepository voterRepository) {
        this.candidateRepository = candidateRepository;
        this.voterRepository = voterRepository;
    }

    public List<CandidateDTO> findAll(){
        return convertCandidatesToDTOList(candidateRepository.findAll());
    }

    public CandidateDTO findById(Long id) {return CandidateDTO.apply(candidateRepository.findById(id).orElseThrow(VoterNotFoundException::new));}

    public CandidateDTO add(Long voterId) {
        Voter v = voterRepository.findById(voterId).orElseThrow(VoterNotFoundException::new);
        return CandidateDTO.apply(candidateRepository.save(CandidateDTO.applyNew(v, new ArrayList<>())));
    }

    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
    }

    List<CandidateDTO> convertCandidatesToDTOList(List<Candidate> all) {
        return all.stream()
                .map(CandidateDTO::apply)
                .collect(Collectors.toList());
    }

}
