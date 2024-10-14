package br.com.reservei.controller;

import br.com.reservei.dto.UserTypeRecordDto;
import br.com.reservei.entity.UserType;
import br.com.reservei.repository.UserTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/userType")
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

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") Integer id){

        Optional<UserType> item = userTypeRepository.findById(id);

        if(item.isEmpty()){

            Map<String, String> response = new HashMap<>();
            response.put("mensagem", "Dado não encontrado");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        }

        return ResponseEntity.status(HttpStatus.OK).body(userTypeRepository.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserType> updateUserType(@PathVariable(value = "id") Integer id, @RequestBody UserTypeRecordDto userTypeRecordDto){

        var UserTypeNew = new UserType();

        BeanUtils.copyProperties(userTypeRecordDto, UserTypeNew);

        return ResponseEntity.status(HttpStatus.OK).body(UserTypeNew);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUserType(@PathVariable(value = "id") Integer id){

        Optional<UserType> item = userTypeRepository.findById(id);

        if(item.isEmpty()){
            Map<String,  String> response = new HashMap<>();
            response.put("mensagem", "Dado não encontrado");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        userTypeRepository.delete(item.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
