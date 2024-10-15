package br.com.reservei.controller;


import br.com.reservei.dto.StatusRecordDto;
import br.com.reservei.entity.Status;
import br.com.reservei.service.StatusService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/status")
@RestController
public class StatusController {

    public final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService){
        this.statusService = statusService;
    }

    @PostMapping
    public ResponseEntity<Status> createNewStatus(@RequestBody StatusRecordDto statusRecordDto){
        Status status = new Status();
        BeanUtils.copyProperties(statusRecordDto, status);
        return ResponseEntity.status(HttpStatus.CREATED).body(statusService.saveStatus(status));
    }

    @GetMapping()
    public ResponseEntity<List<Status>> getAllStatus(){
        return ResponseEntity.status(HttpStatus.OK).body(statusService.getAllStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Status>> getStatusById(@PathVariable(value = "id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(statusService.getStatusById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStatus(@PathVariable(value = "id") Integer id, @RequestBody StatusRecordDto statusRecordDto){
        return ResponseEntity.ok(statusService.updateStatus(id, statusRecordDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatus(@PathVariable(value = "id") Integer id){
        statusService.deleteStatus(id);
        return ResponseEntity.noContent().build();
    }

}
