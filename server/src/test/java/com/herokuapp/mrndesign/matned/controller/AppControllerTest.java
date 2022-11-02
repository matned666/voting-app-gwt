package com.herokuapp.mrndesign.matned.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.herokuapp.mrndesign.matned.dto.CandidateDTO;
import com.herokuapp.mrndesign.matned.dto.VoterDTO;
import com.herokuapp.mrndesign.matned.service.CandidateService;
import com.herokuapp.mrndesign.matned.service.VoterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
class AppControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VoterService voterService;

    @MockBean
    private CandidateService candidateService;

    List<VoterDTO> voterList = new LinkedList<>();
    List<CandidateDTO> candidateList = new LinkedList<>();

    @BeforeEach
    void setup() {
        voterList.clear();
        candidateList.clear();

        voterList.add(new VoterDTO(1L, "a", "a", null));
        voterList.add(new VoterDTO(2L, "b", "b", null));

        candidateList.add(new CandidateDTO(1L, 1L, new HashSet<>()));
    }


    @Test
    void getAllVoters() throws Exception {
        Mockito.doReturn(voterList).when(voterService).findAll();
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/voters")
                                .accept("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json("" +
                        "[" +
                        "{\"id\":1,\"name\":\"a\",\"surname\":\"a\",\"voteCandidateId\":null}," +
                        "{\"id\":2,\"name\":\"b\",\"surname\":\"b\",\"voteCandidateId\":null}" +
                        "]"))
                .andReturn();
    }

    @Test
    void getAllCandidates() throws Exception {
        Mockito.doReturn(candidateList).when(candidateService).findAll();
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/candidates")
                                .accept("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"voterId\":1,\"listOfVotesIds\":[]}]"))
                .andReturn();
    }

    @Test
    void saveVoter() throws Exception {
        VoterDTO voter = new VoterDTO(3L, "c", "c", null);
        Mockito.doReturn(voter).when(voterService).add(any());
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/voters")
                                .content(asJsonString(voter))
                                .contentType("application/json")
                                .accept("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":3,\"name\":\"c\",\"surname\":\"c\",\"voteCandidateId\":null}"))
                .andReturn();
    }

    @Test
    void saveNewCandidate() throws Exception {
        CandidateDTO candidate = new CandidateDTO(3L, 2L, new HashSet<>());
        Mockito.doReturn(candidate).when(candidateService).add(2L);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/candidates/2")
                                .contentType("application/json")
                                .accept("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":3,\"voterId\":2,\"listOfVotesIds\":[]}"))
                .andReturn();
    }

    @Test
    void vote() throws Exception {
        Mockito.doNothing().when(voterService).vote(1L, 1L);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/giveVote/1/1")
                                .contentType("application/json")
                                .accept("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void deleteVoter() throws Exception {
        Mockito.doNothing().when(voterService).deleteVoter(1L);
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/voter/1")
                                .contentType("application/json")
                                .accept("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void deleteCandidate() throws Exception {
        Mockito.doNothing().when(candidateService).deleteCandidate(1L);
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/candidate/1")
                                .contentType("application/json")
                                .accept("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }

    private String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}