package com.herokuapp.mrndesign.matned.repository;

import com.herokuapp.mrndesign.matned.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
