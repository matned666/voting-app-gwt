package com.herokuapp.mrndesign.matned.controller;

import com.herokuapp.mrndesign.matned.dto.CandidateDTO;
import com.herokuapp.mrndesign.matned.dto.VoterDTO;
import com.herokuapp.mrndesign.matned.service.CandidateService;
import com.herokuapp.mrndesign.matned.service.VoterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppController {

    private final VoterService voterService;
    private final CandidateService candidateService;

    public AppController(VoterService voterService, CandidateService candidateService) {
        this.voterService = voterService;
        this.candidateService = candidateService;
    }

    @GetMapping(value = "/voters")
    public List<VoterDTO> getAllVoters() {
        return voterService.findAll();
    }

    @GetMapping(value = "/candidate")
    public List<CandidateDTO> getAllCandidates() {
        return candidateService.findAll();
    }

    @GetMapping(value = "/voters/{voterId}")
    public VoterDTO getAllVoters(@PathVariable Long voterId) {
        return voterService.findById(voterId);
    }

    @PostMapping(value = "/voters")
    public VoterDTO saveNewVoter(@RequestBody VoterDTO voterDTO) {
        return voterService.add(voterDTO);
    }

    @PostMapping(value = "/candidates/{voterId}")
    public CandidateDTO saveNewCandidate(@PathVariable Long voterId) {
        return candidateService.add(voterId);
    }

    @PostMapping(value = "/giveVote/{voterId}/{candidateId}")
    public CandidateDTO giveVote(@PathVariable Long voterId, @PathVariable Long candidateId) {
        return voterService.vote(candidateId, voterId);
    }

    @DeleteMapping(value = "voter/{voterId}")
    public void deleteVoter(@PathVariable Long voterId) {
        voterService.deleteVoter(voterId);
    }

    @DeleteMapping(value = "candidate/{candidateId}")
    public void deleteCandidate(@PathVariable Long candidateId) {
        voterService.deleteVoter(candidateId);
    }


}
