package br.com.reservei.controller;

import br.com.reservei.dto.GenderRecordDto;
import br.com.reservei.entity.Gender;
import br.com.reservei.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/gender")
public class GenderController {
    @Autowired
    GenderRepository genderRepository;

    @GetMapping
    public ResponseEntity <List<Gender>> getAllGenders(){
        return ResponseEntity.status(HttpStatus.OK).body(genderRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity <Object> getGender(@PathVariable(value = "id") Integer id){

        Optional<Gender> gender = genderRepository.findById(id);

        if (gender.isEmpty()){
            Map<String, String> response = new HashMap<>();
            response.put("mensagem", "Nao foi encontrado!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(genderRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<Gender> postGender(@RequestBody GenderRecordDto genderRecordDto){
        var gender = new Gender();
        BeanUtils.copyProperties(genderRecordDto, gender);
        return ResponseEntity.status(HttpStatus.CREATED).body(genderRepository.save(gender));
    }

    @PutMapping("/{id}")
    public ResponseEntity <Object> updateGender(@PathVariable(value = "id") Integer id, @RequestBody GenderRecordDto genderRecordDto){
        Optional<Gender> genderOptional = genderRepository.findById(id);

        if (genderOptional.isEmpty()){
            Map<String, String> response = new HashMap<>();
            response.put("mensagem", "Nao foi encotrado");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Gender gender = genderOptional.get();
        BeanUtils.copyProperties(genderRecordDto, gender);
        return ResponseEntity.status(HttpStatus.OK).body(genderRepository.save(gender));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity <Object> deleteGender(@PathVariable(value = "id") Integer id){
        Optional<Gender> genderOptional = genderRepository.findById(id);

        if (genderOptional.isEmpty()){
            Map<String, String> response = new HashMap<>();
            response.put("mensagem", "Nao foi encotrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        Gender gender = genderOptional.get();
        genderRepository.delete(gender);
        return ResponseEntity.status(HttpStatus.OK).build();

    }
}
