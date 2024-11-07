package br.com.reservei.service;

import br.com.reservei.dto.AddressRecordDto;
import br.com.reservei.entity.Address;
import br.com.reservei.entity.City;
import br.com.reservei.entity.State;
import br.com.reservei.repository.AddressRepository;
import br.com.reservei.repository.CityRepository;
import br.com.reservei.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    public final CityRepository cityRepository;
    public final StateRepository stateRepository;
    public final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, StateRepository stateRepository, CityRepository cityRepository){
        this.cityRepository = cityRepository;
        this.stateRepository = stateRepository;
        this.addressRepository = addressRepository;
    }

    public Address saveAddress(AddressRecordDto addressRecordDto){
        City city = cityRepository.findById(addressRecordDto.city_id())
                .orElseThrow(() -> new IllegalArgumentException("City not found with id: " + addressRecordDto.city_id()));

        State state = city.getState();

        Address address = new Address();
        address.setCep(addressRecordDto.cep());
        address.setState(state);
        address.setCity(city);
        address.setNeighborhood(addressRecordDto.neighborhood());
        address.setAddress_line(addressRecordDto.address_line());
        address.setComplements(addressRecordDto.complements());

        return addressRepository.save(address);
    }

    public List<Address> getAllAddresses(){
        return addressRepository.findAll();
    }

    public Optional<Address> findByIdAddress(Integer id){
        return addressRepository.findById(id);
    }

    public Address updateAddress(AddressRecordDto addressRecordDto, Integer id){

        City city = cityRepository.findById(addressRecordDto.city_id())
                .orElseThrow(() -> new IllegalArgumentException("City not found with id: " + addressRecordDto.city_id()));

        State state = city.getState();

        Address address = addressRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Address not found with id: " + id));

        address.setCep(addressRecordDto.cep());
        address.setState(state);
        address.setCity(city);
        address.setNeighborhood(addressRecordDto.neighborhood());
        address.setAddress_line(addressRecordDto.address_line());
        address.setComplements(addressRecordDto.complements());

        return addressRepository.save(address);
    }
    public void deleteAddress(Integer id){
        if (!addressRepository.existsById(id)){
            throw new RuntimeException("Address not found with id " + id);
        }
        addressRepository.deleteById(id);
    }
}
