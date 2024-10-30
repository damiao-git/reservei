package br.com.reservei.controller;

import br.com.reservei.dto.DocumentTypeDto;
import br.com.reservei.dto.StateRecordDto;
import br.com.reservei.entity.State;
import br.com.reservei.service.DocumentTypeService;
import br.com.reservei.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/state")
public class StateController {

    StateService stateService;

    @Autowired
    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @PostMapping
    public ResponseEntity<State> saveState(@RequestBody State state) {
        State st = stateService.saveState(state);
        return ResponseEntity.status(HttpStatus.CREATED).body(st);
    }

    @GetMapping
    public ResponseEntity<List<State>> getAllStates() {
        List<State> states = stateService.getState();
        return ResponseEntity.status(HttpStatus.OK).body(states);
    }

    @GetMapping("{id}")
    public ResponseEntity<State> getStateById(@PathVariable Integer id) {
        return stateService.getStateById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateState(@PathVariable Integer id, @RequestBody StateRecordDto stateRecordDto) {
        return stateService.updateState(id, stateRecordDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteState(@PathVariable Integer id){
        stateService.deleteState(id);
        return ResponseEntity.noContent().build();
    }
}
