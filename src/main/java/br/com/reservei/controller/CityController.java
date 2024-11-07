package br.com.reservei.controller;

import br.com.reservei.dto.CityRecordDto;
import br.com.reservei.entity.City;
import br.com.reservei.entity.State;
import br.com.reservei.repository.CityRepository;
import br.com.reservei.service.CityService;
import br.com.reservei.service.StateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/city")
public class CityController {

    public CityService cityService;
    public StateService stateService;

    @Autowired
    public CityController(CityService cityService, StateService stateService) {
        this.cityService = cityService;
        this.stateService = stateService;
    }

    @PostMapping
    public ResponseEntity<City> createCity(@Valid @RequestBody CityRecordDto cityRecordDto) {
        City savedCity = cityService.saveCity(cityRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(savedCity);
    }


    @GetMapping
    public ResponseEntity<List<City>> getAllCities() {
        List<City> cities = cityService.getAllCities();
        return ResponseEntity.status(HttpStatus.OK).body(cities);

    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCity(@PathVariable Integer id) {
        return cityService.findByIdCity(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@Valid @RequestBody CityRecordDto  cityRecordDto, @PathVariable Integer id){
        City updatedCity = cityService.updateCity(id, cityRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Integer id){
        cityService.deleteCity(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}


