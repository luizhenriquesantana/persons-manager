package com.accela.personsmanager.model.dto;

import com.accela.personsmanager.model.entity.Address;
import lombok.Data;

import java.util.Objects;

@Data
public class AddressDTO {

    private Long id;

    private String street;

    private String city;

    private String state;

    private String postalCode;

    private PlainPersonDTO plainPersonDTO;

    public AddressDTO() {
    }

    public AddressDTO(Long id, String street, String city, String state, String postalCode, PlainPersonDTO plainPersonDTO) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.plainPersonDTO = plainPersonDTO;
    }

    public static AddressDTO from(Address address){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setCity(address.getCity());
        addressDTO.setStreet(address.getState());
        addressDTO.setPostalCode(address.getPostalCode());

        if(Objects.nonNull(address.getPerson())){
            addressDTO.setPlainPersonDTO(PlainPersonDTO.from(address.getPerson()));
        }

        return addressDTO;
    }
}
