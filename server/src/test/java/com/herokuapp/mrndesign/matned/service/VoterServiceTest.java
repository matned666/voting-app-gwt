package com.herokuapp.mrndesign.matned.service;

import com.herokuapp.mrndesign.matned.dto.CandidateDTO;
import com.herokuapp.mrndesign.matned.dto.VoterDTO;
import com.herokuapp.mrndesign.matned.model.Candidate;
import com.herokuapp.mrndesign.matned.model.Voter;
import com.herokuapp.mrndesign.matned.repository.CandidateRepository;
import com.herokuapp.mrndesign.matned.repository.VoterRepository;
import com.herokuapp.mrndesign.matned.service.exception.CandidateNotFoundException;
import com.herokuapp.mrndesign.matned.service.exception.VoterNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith({SpringExtension.class})
@SpringBootTest
class VoterServiceTest {

    @Autowired
    private VoterService voterService;

    @MockBean
    private VoterRepository voterRepository;
    @MockBean
    private CandidateRepository candidateRepository;

    List<VoterDTO> voterDTOList = new LinkedList<>();
    List<Voter> voterList = new LinkedList<>();
    List<CandidateDTO> candidateDTOList = new LinkedList<>();
    List<Candidate> candidateList = new LinkedList<>();
    private Voter v1;
    private Candidate c1;

    @BeforeEach
    void setup() {
        voterDTOList.clear();
        voterList.clear();
        candidateDTOList.clear();
        candidateList.clear();


        c1 = new Candidate(1L, new HashSet<>());
        Candidate c2 = new Candidate(2L, new HashSet<>());

        v1 = new Voter(1L, "a", "a", null);
        Voter v2 = new Voter(2L, "b", "b", c1);
        Voter v3 = new Voter(3L, "c", "c", c1);
        Voter v4 = new Voter(4L, "d", "d", null);

        c1.setVoter(v1);
        c2.setVoter(v2);

        c1.getVoters().add(v2);
        c1.getVoters().add(v3);

        candidateDTOList.add(CandidateDTO.apply(c1));
        candidateDTOList.add(CandidateDTO.apply(c2));

        voterDTOList.add(VoterDTO.apply(v1));
        voterDTOList.add(VoterDTO.apply(v2));
        voterDTOList.add(VoterDTO.apply(v3));
        voterDTOList.add(VoterDTO.apply(v4));

        voterList.addAll(Arrays.asList(v1, v2, v3, v4));
        candidateList.addAll(Arrays.asList(c1, c2));
    }


    @Test
    void findAll() {
        doReturn(voterList).when(voterRepository).findAll();
        assertEquals(voterDTOList, voterService.findAll());
    }

    @Test
    void add() {
        doReturn(v1).when(voterRepository).save(any());
        assertEquals(voterDTOList.get(0), voterService.add(voterDTOList.get(0)));
    }

    @Test
    void vote() {
        doReturn(Optional.of(v1)).when(voterRepository).findById(1L);
        doReturn(Optional.of(c1)).when(candidateRepository).findById(1L);
        doReturn(c1).when(candidateRepository).save(any());
        voterService.vote(1L, 1L);
        assertTrue(c1.getVoters().contains(v1));
        assertEquals(3, c1.getVoters().size());
    }

    @Test
    void vote_shouldThrowVoterNotFoundException_whenVoterFoundNull() {
        doReturn(Optional.empty()).when(voterRepository).findById(1L);
        assertThrows(VoterNotFoundException.class, () -> voterService.vote(1L, 1L));
    }

    @Test
    void vote_shouldThrowCandidateNotFoundException_whenVoterFoundNull() {
        doReturn(Optional.of(v1)).when(voterRepository).findById(1L);
        doReturn(Optional.empty()).when(candidateRepository).findById(1L);
        assertThrows(CandidateNotFoundException.class, () -> voterService.vote(1L, 1L));
    }


}