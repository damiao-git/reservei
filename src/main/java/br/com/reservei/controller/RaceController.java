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

        if(race.isEmpty()){

            Map<String, String> response = new HashMap<>();
            response.put("mensagem", "Nao foi encontrado!");

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
            return ResponseEntity.status(HttpStatus.OK).body(raceRepository.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRace(@PathVariable(value = "id") Integer id, @RequestBody RaceRecordDto raceRecordDto){
        Optional<Race> raceOptional = raceRepository.findById(id);

        if(raceOptional.isEmpty()){
            Map<String, String> response = new HashMap<>();
            response.put("mensagem", "Nao foi encontrado!");

            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        Race race = raceOptional.get();

        BeanUtils.copyProperties(raceRecordDto, race);


        return ResponseEntity.status(HttpStatus.OK).body(raceRepository.save(race));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRace(@PathVariable(value = "id") Integer id){

        Optional<Race> race = raceRepository.findById(id);

        if(race.isEmpty()){
            Map<String, String> response = new HashMap<>();
            response.put("mensagem", "Nao foi encontrado!");

            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        raceRepository.delete(race.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}