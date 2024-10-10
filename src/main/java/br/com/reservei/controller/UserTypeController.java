package br.com.reservei.controller;

import br.com.reservei.dto.UserTypeRecordDto;
import br.com.reservei.entity.UserType;
import br.com.reservei.repository.UserTypeRepository;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/userType")
public class UserTypeController {

    @Autowired
    UserTypeRepository userTypeRepository;

    @PostMapping
    public ResponseEntity<UserType> saveUserType(@RequestBody UserTypeRecordDto userTypeRecordDto){

        var userTypeNew = new UserType();

        BeanUtils.copyProperties(userTypeRecordDto, userTypeNew);

        return ResponseEntity.status(HttpStatus.OK).body(userTypeRepository.save(userTypeNew));
    }

    @GetMapping
    public ResponseEntity<List<UserType>> getAllUserType(){
        return ResponseEntity.status(HttpStatus.OK).body(userTypeRepository.findAll());
    }
}
