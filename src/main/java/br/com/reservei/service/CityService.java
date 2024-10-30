package br.com.reservei.service;

import br.com.reservei.dto.CityRecordDto;
import br.com.reservei.dto.StateRecordDto;
import br.com.reservei.entity.City;
import br.com.reservei.entity.State;
import br.com.reservei.repository.CityRepository;
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
public class CityService {

    public final CityRepository cityRepository;
    public final StateRepository stateRepository;


    @Autowired
    public CityService(CityRepository cityRepository, StateRepository stateRepository){
        this.cityRepository = cityRepository;
        this.stateRepository = stateRepository;
    }

    public City saveCity(CityRecordDto cityRecordDto){

        State state = stateRepository.findById(cityRecordDto.state_id())
                .orElseThrow(() -> new IllegalArgumentException("State not found with id: " + cityRecordDto.state_id()));

        City city = new City();
        city.setName(cityRecordDto.name());
        city.setState(state);

        return cityRepository.save(city);
    }

    public List<City> getAllCities(){
        return cityRepository.findAll();
    }

    public Optional<City> findByIdCity(Integer id){
        return cityRepository.findById(id);
    }

    public City updateCity(Integer id, CityRecordDto cityRecordDto){

        State state = stateRepository.findById(cityRecordDto.state_id())
                .orElseThrow(() -> new IllegalArgumentException("State not found with id: " + cityRecordDto.state_id()));

        City city = cityRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("City not found with id: " + cityRecordDto.state_id()));
        city.setName(cityRecordDto.name());
        city.setState(state);

        return cityRepository.save(city);
    }

    public void deleteCity(Integer id){
        if (!cityRepository.existsById(id)){
            throw new RuntimeException("City not found with id " + id);
        }
        cityRepository.deleteById(id);
    }
}
