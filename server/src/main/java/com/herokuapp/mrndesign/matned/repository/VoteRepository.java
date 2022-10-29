package com.herokuapp.mrndesign.matned.repository;

import com.herokuapp.mrndesign.matned.dto.CandidateDTO;
import com.herokuapp.mrndesign.matned.model.Candidate;
import com.herokuapp.mrndesign.matned.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("select v from Vote v where u.candidate.id = ?1" )
    List<Vote> findAllVotesByCandidateId(Long id);

    @Query("select case when count(v)> 0 then true else false end from Vote v where v.voter.id = ?1")
    boolean isVoteGiven(Long voterId);

    List<Vote> findAllForCandidate(Long candidateId);

    CandidateDTO findByVoter(Long voterId);
}
