package com.ebi.masterdata.person.converter;

import java.util.ArrayList;

import com.ebi.masterdata.person.dto.PersonDTO;
import com.ebi.masterdata.person.entity.Person;

public class PersonConverter {
	
	public static void convertPersonDTOToPersonEntity(PersonDTO personDTO, Person person) {
		person.setFirstName(personDTO.getFirstName());
		person.setLastName(personDTO.getLastName());
		person.setAge(personDTO.getAge());
		person.setFavouriteColor(personDTO.getFavouriteColor());
		
		if(personDTO.getHobby() != null && !personDTO.getHobby().isEmpty()) {
			person.setHobby(personDTO.getHobby());
		}
	}
	
	public static PersonDTO convertPersonEntityToPersonDTO(Person person) {
		PersonDTO personDTO = new PersonDTO();
		personDTO.setPersonId(person.getPersonId());
		personDTO.setFirstName(person.getFirstName());
		personDTO.setLastName(person.getLastName());
		personDTO.setAge(person.getAge());
		personDTO.setFavouriteColor(person.getFavouriteColor());
		
		if(person.getHobby() != null && !person.getHobby().isEmpty()) {
			personDTO.setHobby(person.getHobby());
		} else {
			personDTO.setHobby(new ArrayList<>());
		}
		return personDTO;
	}
}
