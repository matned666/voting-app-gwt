package com.herokuapp.mrndesign.matned.repository;

import com.herokuapp.mrndesign.matned.model.Candidate;
import com.herokuapp.mrndesign.matned.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    @Query("select c from Candidate c where :voter member c.voters")
    Optional<Candidate> findByVote(@Param("voter") Voter voter);
}
