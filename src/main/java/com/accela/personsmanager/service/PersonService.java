package com.accela.personsmanager.service;

import com.accela.personsmanager.model.entity.Address;
import com.accela.personsmanager.model.entity.Person;
import com.accela.personsmanager.model.exception.PersonNotFoundException;
import com.accela.personsmanager.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    private final AddressService addressService;

    @Autowired
    public PersonService(PersonRepository personRepository, AddressService addressService){
        this.personRepository = personRepository;
        this.addressService = addressService;
    }

    public Person addPerson(Person person){
        return personRepository.save(person);
    }

    public List<Person> getPersons(){
        return StreamSupport.stream(personRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Person getPerson(Long id){
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    public Person deletePerson(Long id){
        Person personToDelete = getPerson(id);
        personRepository.delete(personToDelete);

        return personToDelete;
    }

    @Transactional
    public Person editPerson(Long id, Person person){
        Person personToEdit = getPerson(id);
        personToEdit.setFirstName(person.getFirstName());
        personToEdit.setLastName(person.getLastName());

        return personToEdit;
    }


    @Transactional
    public Person addAddressToPerson(Long personId, Long addressId){
        Person person = getPerson(personId);
        Address address = addressService.getAddress(addressId);

        person.addAddress(address);
        address.setPerson(person);

        return person;
    }

    @Transactional
    public Person removeAddressFromPerson(Long personId, Long addressId){
        Person person = getPerson(personId);
        Address address = addressService.getAddress(addressId);

        person.removeAddress(address);
        return person;
    }
}
