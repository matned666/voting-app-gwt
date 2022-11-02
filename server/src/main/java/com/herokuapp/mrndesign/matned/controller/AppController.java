package com.herokuapp.mrndesign.matned.controller;

import com.herokuapp.mrndesign.matned.dto.CandidateDTO;
import com.herokuapp.mrndesign.matned.dto.VoterDTO;
import com.herokuapp.mrndesign.matned.service.CandidateService;
import com.herokuapp.mrndesign.matned.service.VoterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Main server controller
 */
@RestController
public class AppController {

    private final VoterService voterService;
    private final CandidateService candidateService;

    public AppController(VoterService voterService, CandidateService candidateService) {
        this.voterService = voterService;
        this.candidateService = candidateService;
    }

    /**
     * @return all voters as {@link VoterDTO} List
     */
    @GetMapping(value = "/voters")
    public List<VoterDTO> getAllVoters() throws InterruptedException {
//        TimeUnit.SECONDS.sleep(2);
        return voterService.findAll();
    }

    /**
     * @return all candidates as {@link CandidateDTO} List
     */
    @GetMapping(value = "/candidates")
    public List<CandidateDTO> getAllCandidates() throws InterruptedException {
//        TimeUnit.SECONDS.sleep(2);
        return candidateService.findAll();
    }

    /**
     * Saves {@link com.herokuapp.mrndesign.matned.model.Voter} given in request body
     *
     * @param voterDTO dto
     * @return saved voter taken from repository
     */
    @PostMapping(value = "/voters")
    public VoterDTO saveVoter(@RequestBody VoterDTO voterDTO) throws InterruptedException {
//        TimeUnit.SECONDS.sleep(2);
        return voterService.add(voterDTO);
    }

    /**
     * Saves new {@link com.herokuapp.mrndesign.matned.model.Candidate} by given Voter Id
     * The saved Candidate has empty votes list
     *
     * @param voterId Voter id
     * @return saved voter taken from repository
     */
    @PostMapping(value = "/candidates/{voterId}")
    public CandidateDTO saveNewCandidate(@PathVariable Long voterId) throws InterruptedException {
//        TimeUnit.SECONDS.sleep(2);
        return candidateService.add(voterId);
    }

    /**
     * Vote by voter on candidate
     *
     * @param voterId
     * @param candidateId
     */
    @PostMapping(value = "/giveVote/{voterId}/{candidateId}")
    public void vote(@PathVariable Long voterId, @PathVariable Long candidateId) throws InterruptedException {
//        TimeUnit.SECONDS.sleep(2);
        voterService.vote(candidateId, voterId);
    }

    /**
     * Removes {@link com.herokuapp.mrndesign.matned.model.Voter} by its UUID
     *
     * @param voterId
     */
    @DeleteMapping(value = "/voter/{voterId}")
    public void deleteVoter(@PathVariable Long voterId) throws InterruptedException {
//        TimeUnit.SECONDS.sleep(2);
        voterService.deleteVoter(voterId);
    }

    /**
     * Removes {@link com.herokuapp.mrndesign.matned.model.Candidate} by its UUID
     *
     * @param candidateId
     */
    @DeleteMapping(value = "/candidate/{candidateId}")
    public void deleteCandidate(@PathVariable Long candidateId) throws InterruptedException {
//        TimeUnit.SECONDS.sleep(2);
        candidateService.deleteCandidate(candidateId);
    }

}
