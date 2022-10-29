package com.herokuapp.mrndesign.matned.service;

import com.herokuapp.mrndesign.matned.dto.VoterDTO;
import com.herokuapp.mrndesign.matned.model.Voter;
import com.herokuapp.mrndesign.matned.repository.VoterRepository;
import com.herokuapp.mrndesign.matned.service.exception.VoterNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoterService {

    private final VoterRepository voterRepository;

    @Autowired
    public VoterService(VoterRepository voterRepository) {
        this.voterRepository = voterRepository;
    }

    public List<VoterDTO> findAll(){
        return convertEntityToUserDTOList(voterRepository.findAll());
    }

    public VoterDTO findById(Long id) {return VoterDTO.apply(voterRepository.findById(id).orElseThrow(VoterNotFoundException::new));}

    public VoterDTO add(VoterDTO dto){
        return VoterDTO.apply(voterRepository.save(VoterDTO.applyNew(dto)));
    }

    public void deleteVoter(Long id) {
        voterRepository.deleteById(id);
    }

    List<VoterDTO> convertEntityToUserDTOList(List<Voter> all) {
        return all.stream()
                .map(VoterDTO::apply)
                .collect(Collectors.toList());
    }

}
