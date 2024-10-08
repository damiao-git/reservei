package br.com.reservei.controller;

import br.com.reservei.dto.RaceRecordDto;
import br.com.reservei.entity.Race;
import br.com.reservei.repository.RaceRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RequestMapping(value = "/race")
@RestController
public class RaceController {

    @Autowired
    RaceRepository raceRepository;


    @PostMapping
    public ResponseEntity<Race> saveRace(@RequestBody @Valid RaceRecordDto raceRecordDto)
    {
        var race = new Race();

        BeanUtils.copyProperties(raceRecordDto, race);

        return ResponseEntity.status(HttpStatus.CREATED).body(raceRepository.save(race));
    }

    @GetMapping
    public ResponseEntity<List<Race>> getAllRaces(){
        return ResponseEntity.status(HttpStatus.OK).body(raceRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUmSo(@PathVariable(value = "id") Integer id){

        Optional<Race> race = raceRepository.findById(id);
        ResponseEntity<Object> response = new ResponseEntity<Object>(HttpStatus.OK);


        if(race.isEmpty()){

            Map<String, String> response2 = new HashMap<>();
            response2.put("mensagem", "Nao foi encontrado com sucesso!");

            return new ResponseEntity<>(response2, HttpStatus.OK);
        }
            return ResponseEntity.status(HttpStatus.OK).body(raceRepository.findById(id));
    }



}