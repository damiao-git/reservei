package br.com.reservei.controller;

import br.com.reservei.dto.AddressRecordDto;
import br.com.reservei.dto.CityRecordDto;
import br.com.reservei.entity.Address;
import br.com.reservei.entity.Category;
import br.com.reservei.entity.City;
import br.com.reservei.repository.CategoryRepository;
import br.com.reservei.service.AddressService;
import br.com.reservei.service.CityService;
import br.com.reservei.service.StateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    public CityService cityService;
    public StateService stateService;
    public AddressService addressService;

    @Autowired
    public AddressController(CityService cityService, StateService stateService, AddressService addressService){
        this.cityService = cityService;
        this.stateService = stateService;
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<Address> createAddress(@Valid @RequestBody AddressRecordDto addressRecordDto){
        Address savedAddress = addressService.saveAddress(addressRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(savedAddress);
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses (){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.getAllAddresses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddresses (@PathVariable Integer id){
        return addressService.findByIdAddress(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@Valid @RequestBody AddressRecordDto addressRecordDto, @PathVariable Integer id){
        Address updatedAddress = addressService.updateAddress(addressRecordDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedAddress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Integer id){
        addressService.deleteAddress(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
