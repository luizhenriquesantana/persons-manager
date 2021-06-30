package com.accela.personsmanager.model.dto;

import com.accela.personsmanager.model.entity.Person;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PersonDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private List<AddressDTO> addressesDTO;

    public static PersonDTO from (Person person){
        PersonDTO personDTO = new PersonDTO();

        personDTO.setId(person.getId());
        personDTO.setFirstName(person.getFirstName());
        personDTO.setLastName(person.getLastName());
        personDTO.setAddressesDTO(!CollectionUtils.isEmpty(person.getAddresses()) ? person.getAddresses().stream().map(AddressDTO::from).collect(Collectors.toList()) : new ArrayList<>());

        return personDTO;
    }

}
