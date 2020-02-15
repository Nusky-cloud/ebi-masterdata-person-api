package com.ebi.masterdata.person.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebi.masterdata.person.converter.PersonConverter;
import com.ebi.masterdata.person.dto.PersonDTO;
import com.ebi.masterdata.person.entity.Person;
import com.ebi.masterdata.person.repository.PersonRepository;
import com.ebi.masterdata.person.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public void createPerson(PersonDTO newPersonDTO) {
		Person newPerson = new Person();
		PersonConverter.convertPersonDTOToPersonEntity(newPersonDTO, newPerson);
		personRepository.save(newPerson);
	}

	@Override
	public void updatePerson(PersonDTO personDTO, Person existingPerson) {
		PersonConverter.convertPersonDTOToPersonEntity(personDTO, existingPerson);
		personRepository.save(existingPerson);
	}

	@Override
	public Optional<Person> getPersonById(Long personId) {
		return personRepository.findById(personId);
	}

	@Override
	public List<PersonDTO> getAllPersons() {
		List<Person> allPersons = personRepository.findAll();
		List<PersonDTO> allPersonDTOs = new ArrayList<>();
		
		if(allPersons != null && !allPersons.isEmpty()) {
			allPersons.forEach(person -> {
				allPersonDTOs.add(PersonConverter.convertPersonEntityToPersonDTO(person));
			});
		}
		
		return allPersonDTOs;
	}

	@Override
	public void removePersonById(Long personId) {
		personRepository.deleteById(personId);
	}
}
