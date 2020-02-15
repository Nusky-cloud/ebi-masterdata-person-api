package com.ebi.masterdata.person.service;

import java.util.List;
import java.util.Optional;

import com.ebi.masterdata.person.dto.PersonDTO;
import com.ebi.masterdata.person.entity.Person;

public interface PersonService {
	
	public void createPerson(PersonDTO newPersonDTO);
	
	public void updatePerson(PersonDTO personDTO, Person existingPerson);
	
	public Optional<Person> getPersonById(Long personId);
	
	public List<PersonDTO> getAllPersons();
	
	public void removePersonById(Long personId);
}
