package br.com.reservei.service;

import br.com.reservei.dto.DocumentTypeDto;
import br.com.reservei.dto.StateRecordDto;
import br.com.reservei.entity.DocumentType;
import br.com.reservei.entity.State;
import br.com.reservei.repository.StateRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StateService {
    StateRepository stateRepository;

    @Autowired
    public StateService(StateRepository stateRepository){
        this.stateRepository = stateRepository;
    }

    public State saveState(State stateRecordDto){
        return stateRepository.save(stateRecordDto);
    }

    public Optional<State> getStateById(Integer id){
        return stateRepository.findById(id);
    }

    public List<State> getState(){
        return stateRepository.findAll();
    }

    public ResponseEntity<Object> updateState(Integer id, StateRecordDto stateRecordDto) {
        Optional<State> stateOptional = stateRepository.findById(id);

        if (stateOptional.isEmpty()){
            Map<String, String> response = new HashMap<>();
            response.put("mensagem", "Nao foi encotrado");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        State st = stateOptional.get();
        BeanUtils.copyProperties(stateRecordDto, st);
        return ResponseEntity.status(HttpStatus.OK).body(stateRepository.save(st));
    }

    public void deleteState(Integer id){
        Optional<State> stateOptional = stateRepository.findById(id);

        if (!stateRepository.existsById(id)){
            throw new RuntimeException("State not found with id " + id);
        }
        stateRepository.deleteById(id);
    }
}
