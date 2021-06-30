package com.accela.personsmanager.controller;

import com.accela.personsmanager.model.dto.PersonDTO;
import com.accela.personsmanager.model.entity.Person;
import com.accela.personsmanager.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/persons")
@CrossOrigin
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    //1.
    @PostMapping
    public ResponseEntity<PersonDTO> addPerson(@RequestBody final PersonDTO personDto){
        Person person = personService.addPerson(Person.from(personDto));
        log.debug("Creating a person with first_name={0} and last_name={1}", personDto.getFirstName(), personDto.getLastName());
        return new ResponseEntity<>(PersonDTO.from(person), HttpStatus.OK);
    }

    //2
    @PutMapping(value = "{id}")
    public ResponseEntity<PersonDTO> editPerson(@PathVariable final Long id, @RequestBody final PersonDTO personDto){
        Person person = personService.editPerson(id, Person.from(personDto));
        log.debug("Editing person with id={0}", id);
        return new ResponseEntity<>(PersonDTO.from(person), HttpStatus.OK);
    }

    //3
    @DeleteMapping(value = "{id}")
    public ResponseEntity<PersonDTO> deletePerson(@PathVariable final Long id){
        Person person = personService.deletePerson(id);
        log.debug("Deleting person with id={0}", id);
        return new ResponseEntity<>(PersonDTO.from(person), HttpStatus.OK);
    }

    //4
    @PostMapping(value = "{personId}/addresses/{addressId}/add")
    public ResponseEntity<PersonDTO> addAddressToPerson(@PathVariable final Long personId, @PathVariable final Long addressId){
        Person person = personService.addAddressToPerson(personId, addressId);
        log.debug("Adding address={0} to person id={1}", addressId, personId);
        return new ResponseEntity<>(PersonDTO.from(person), HttpStatus.OK);
    }

    //7
    @GetMapping(value = "/count")
    public ResponseEntity<Long> personsCount(){
        List<Person> persons = personService.getPersons();
        Long personsCount = persons.stream().map(PersonDTO::from).count();
        log.debug("Counting number of persons");
        return new ResponseEntity<>(personsCount, HttpStatus.OK);
    }

    //8
    @GetMapping
    public ResponseEntity<List<PersonDTO>> getPersons(){
        List<Person> persons = personService.getPersons();
        List<PersonDTO> adressesDTO = persons.stream().map(PersonDTO::from).collect(Collectors.toList());
        log.debug("Getting all the persons");
        return new ResponseEntity<>(adressesDTO, HttpStatus.OK);
    }


}
