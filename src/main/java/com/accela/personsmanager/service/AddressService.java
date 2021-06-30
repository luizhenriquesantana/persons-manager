package com.accela.personsmanager.service;

import com.accela.personsmanager.model.entity.Address;
import com.accela.personsmanager.model.exception.AddressNotFoundException;
import com.accela.personsmanager.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    public Address addAddress(Address address){
        return addressRepository.save(address);
    }

    public List<Address> getAddresses(){
        return StreamSupport.stream(addressRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Address getAddress(Long id){
        return addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException(id));
    }

    public Address deleteAddress(Long id){
        Address addressToDelete = getAddress(id);
        addressRepository.delete(addressToDelete);

        return addressToDelete;
    }

    @Transactional
    public Address editAddress(Long id, Address address){
        Address addressToEdit = getAddress(id);

        addressToEdit.setStreet(address.getStreet());
        addressToEdit.setCity(address.getCity());
        addressToEdit.setState(address.getState());
        addressToEdit.setPostalCode(address.getPostalCode());

        return addressToEdit;
    }

}
