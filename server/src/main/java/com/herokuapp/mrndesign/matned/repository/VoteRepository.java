package com.herokuapp.mrndesign.matned.repository;

import com.herokuapp.mrndesign.matned.model.Candidate;
import com.herokuapp.mrndesign.matned.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("select case when count(v)> 0 then true else false end from Vote v where v.voter.id = ?1")
    boolean isVoteGiven(Long voterId);

    @Query("select v from Vote v where v.candidate.id = ?1")
    List<Vote> findAllForCandidate(Long candidateId);

    @Query("select v.candidate from Vote v where v.voter.id = ?1")
    Optional<Candidate> findByVoter(Long voterId);

}
