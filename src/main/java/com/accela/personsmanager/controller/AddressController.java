package com.accela.personsmanager.controller;

import com.accela.personsmanager.model.dto.AddressDTO;
import com.accela.personsmanager.model.entity.Address;
import com.accela.personsmanager.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }

    //4
    @PostMapping
    public ResponseEntity<AddressDTO> addAddress(@RequestBody final AddressDTO addressDTO){
        Address address = addressService.addAddress(Address.from(addressDTO));
        log.debug("Creating address with street={0}, city={1}, state={2}, postalCode={3}", addressDTO.getStreet(), addressDTO.getCity(), addressDTO.getState(), addressDTO.getPostalCode() );
        return new ResponseEntity<>(AddressDTO.from(address), HttpStatus.OK);
    }

    //5
    @PutMapping(value = "{id}")
    public ResponseEntity<AddressDTO> editAddress(@PathVariable final Long id,
                                            @RequestBody final AddressDTO addressDto){
        Address editAddress = addressService.editAddress(id, Address.from(addressDto));
        return new ResponseEntity<>(AddressDTO.from(editAddress), HttpStatus.OK);
    }

    //6
    @DeleteMapping(value = "{id}")
    public ResponseEntity<AddressDTO> deleteAddress(@PathVariable final Long id){
        Address address = addressService.deleteAddress(id);
        return new ResponseEntity<>(AddressDTO.from(address), HttpStatus.OK);
    }

}
