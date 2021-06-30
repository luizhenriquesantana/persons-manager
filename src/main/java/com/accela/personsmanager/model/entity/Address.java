package com.accela.personsmanager.model.entity;

import com.accela.personsmanager.model.dto.AddressDTO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table (name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String street;

    private String city;

    private String state;

    private String postalCode;

    @ManyToOne
    private Person person;

    public Address() {
    }

    public Address(Long id, String street, String city, String state, String postalCode, Person person) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.person = person;
    }

    public static Address from(AddressDTO addressDTO){
        Address address = new Address();

        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setPostalCode(addressDTO.getPostalCode());

        return address;
    }
}
