package com.accela.personsmanager.model.dto;

import com.accela.personsmanager.model.entity.Person;
import lombok.Data;

@Data
public class PlainPersonDTO {

    private Long id;

    private String firstName;

    private String lastName;

    public static PlainPersonDTO from(Person person){
        PlainPersonDTO plainPersonDTO = new PlainPersonDTO();
        plainPersonDTO.setId(person.getId());
        plainPersonDTO.setFirstName(person.getFirstName());
        plainPersonDTO.setLastName(person.getLastName());

        return plainPersonDTO;
    }
}
