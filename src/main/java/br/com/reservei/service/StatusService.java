package br.com.reservei.service;


import br.com.reservei.dto.StatusRecordDto;
import br.com.reservei.entity.Status;
import br.com.reservei.repository.StatusRepository;
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
public class StatusService {

    public final StatusRepository statusRepository;

    @Autowired
    public StatusService(StatusRepository statusRepository){
        this.statusRepository = statusRepository;
    }

    public Status saveStatus(Status status){
        return statusRepository.save(status);
    }

    public List<Status> getAllStatus(){
        return statusRepository.findAll();
    }

    public Optional<Status> getStatusById(Integer id){
        return statusRepository.findById(id);
    }

    public Status updateStatus(Integer id, StatusRecordDto status){

        var findStatus = statusRepository.findById(id);

        if(findStatus.isEmpty()){
            throw new RuntimeException("User not found with id " + id);

        }

        Status newStatus = findStatus.get();

        BeanUtils.copyProperties(status, newStatus);
        return statusRepository.save(newStatus);
    }

    public void deleteStatus(Integer id){

        if(!statusRepository.existsById(id)){
            throw new RuntimeException("Status not found with id " + id);
        }
        statusRepository.deleteById(id);
    }




}
