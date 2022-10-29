package com.herokuapp.mrndesign.matned.service;

import com.herokuapp.mrndesign.matned.dto.CandidateDTO;
import com.herokuapp.mrndesign.matned.dto.VoteDTO;
import com.herokuapp.mrndesign.matned.dto.VoterDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
public class AppController {

    private final VoterService voterService;
    private final VoteService voteService;
    private final CandidateService candidateService;

    public AppController(VoterService voterService, VoteService voteService, CandidateService candidateService) {
        this.voterService = voterService;
        this.voteService = voteService;
        this.candidateService = candidateService;
    }

    @GetMapping(value = "/voters")
    public CompletableFuture<List<VoterDTO>> getAllVoters(){
        return CompletableFuture.supplyAsync(voterService::findAll);
    }

    @GetMapping(value = "/candidate")
    public CompletableFuture<List<CandidateDTO>> getAllCandidates(){
        return CompletableFuture.supplyAsync(candidateService::findAll);
    }

    @GetMapping(value = "/vote")
    public CompletableFuture<List<VoteDTO>> getAllVotes(){
        return CompletableFuture.supplyAsync(voteService::findAllVotes);
    }

    @GetMapping(value = "/vote/candidate/{candidateId}")
    public CompletableFuture<List<VoteDTO>> getAllVotesForCandidate(@PathVariable Long candidateId){
        return CompletableFuture.supplyAsync(()->voteService.findAllVotesForCandidate(candidateId));
    }

    @GetMapping(value = "/vote/candidate/{voterId}")
    public CompletableFuture<CandidateDTO> getVoterVote(@PathVariable Long voterId){
        return CompletableFuture.supplyAsync(()->voteService.voterVote(voterId));
    }

    @GetMapping(value = "/voters/{voterId}")
    public CompletableFuture<VoterDTO> getAllVoters(@PathVariable Long voterId){
        return CompletableFuture.supplyAsync(()->voterService.findById(voterId));
    }

    @PostMapping(value = "/voters")
    public CompletableFuture<VoterDTO> saveNewVoter(@RequestBody VoterDTO voterDTO) {
        return CompletableFuture.supplyAsync(()->voterService.add(voterDTO));
    }

    @PostMapping(value = "/candidates/{voterId}")
    public CompletableFuture<CandidateDTO> saveNewCandidate(@PathVariable Long voterId) {
        return CompletableFuture.supplyAsync(()->candidateService.add(voterId));
    }

    @PostMapping(value = "/giveVote/{voterId}/{candidateId}")
    public CompletableFuture<VoteDTO> giveVote(@PathVariable Long voterId, @PathVariable Long candidateDTO) {
        return CompletableFuture.supplyAsync(()->voteService.vote(candidateDTO, voterId));
    }

    @DeleteMapping(value = "voter/{voterId}")
    public void deleteVoter(@PathVariable Long voterId) {
        voterService.deleteVoter(voterId);
    }

    @DeleteMapping(value = "candidate/{candidateId}")
    public void deleteCandidate(@PathVariable Long candidateId) {
        voterService.deleteVoter(candidateId);
    }

    @DeleteMapping(value = "candidate/{voteId}")
    public void deleteVote(@PathVariable Long voteId) {
        voteService.deleteVote(voteId);
    }

}
