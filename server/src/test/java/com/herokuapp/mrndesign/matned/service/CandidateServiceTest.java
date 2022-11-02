package com.herokuapp.mrndesign.matned.service;

import com.herokuapp.mrndesign.matned.dto.CandidateDTO;
import com.herokuapp.mrndesign.matned.dto.VoterDTO;
import com.herokuapp.mrndesign.matned.model.Candidate;
import com.herokuapp.mrndesign.matned.model.Voter;
import com.herokuapp.mrndesign.matned.repository.CandidateRepository;
import com.herokuapp.mrndesign.matned.repository.VoterRepository;
import com.herokuapp.mrndesign.matned.service.exception.VoterNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith({SpringExtension.class})
@SpringBootTest
class CandidateServiceTest {

    @Autowired
    private CandidateService candidateService;

    @MockBean
    private CandidateRepository candidateRepository;
    @MockBean
    private VoterRepository voterRepository;

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
        Voter v2 = new Voter(2L, "b", "b", c2);
        Voter v3 = new Voter(3L, "c", "c", c2);
        Voter v4 = new Voter(4L, "d", "d", null);

        c1.setVoter(v1);
        c2.setVoter(v2);

        c2.getVoters().add(v2);
        c2.getVoters().add(v3);

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
        doReturn(candidateList).when(candidateRepository).findAll();
        assertEquals(candidateDTOList, candidateService.findAll());
    }

    @Test
    void add() {
        doReturn(Optional.of(v1)).when(voterRepository).findById(1L);
        doReturn(c1).when(candidateRepository).save(any());
        assertEquals(candidateDTOList.get(0), candidateService.add(1L));
    }

    @Test
    void shouldThrowVoterNotFoundExceptin_WhenGiveWrongVoterId() {
        doReturn(Optional.empty()).when(voterRepository).findById(1L);
        assertThrows(VoterNotFoundException.class, () -> candidateService.add(1L));
    }


}