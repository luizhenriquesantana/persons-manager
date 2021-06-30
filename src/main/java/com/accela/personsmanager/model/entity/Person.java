package com.accela.personsmanager.model.entity;

import com.accela.personsmanager.model.dto.AddressDTO;
import com.accela.personsmanager.model.dto.PersonDTO;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private List<Address> addresses;

    public void addAddress(Address address){
        if(CollectionUtils.isEmpty(addresses)){
            setAddresses(new ArrayList<>());
        }
        getAddresses().add(address);
    }

    public void removeAddress(Address address){
        if(!CollectionUtils.isEmpty(addresses)) {
            getAddresses().remove(address);
        }
    }

    public static Person from(PersonDTO personDTO){
        Person person = new Person();
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());

        if(!CollectionUtils.isEmpty(personDTO.getAddressesDTO())){
            List<Address> addresses = personDTO.getAddressesDTO().stream().map(Address::from).collect(Collectors.toList());
            person.setAddresses(addresses);
        }

        return person;
    }
}
