package com.herokuapp.mrndesign.matned.repository;

import com.herokuapp.mrndesign.matned.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepository extends JpaRepository<Voter, Long> {

}
