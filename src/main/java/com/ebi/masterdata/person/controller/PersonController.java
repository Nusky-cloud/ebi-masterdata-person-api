package com.ebi.masterdata.person.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.ebi.masterdata.person.converter.PersonConverter;
import com.ebi.masterdata.person.dto.PersonDTO;
import com.ebi.masterdata.person.entity.Person;
import com.ebi.masterdata.person.protocol.ResponseEnvelope;
import com.ebi.masterdata.person.service.PersonService;
import com.ebi.masterdata.person.util.CommonUtil;

@CrossOrigin
@RestController
public class PersonController {

	@Autowired
	private PersonService personService;

	@PostMapping("/create")
	public ResponseEntity<ResponseEnvelope<Void>> createPerson(@RequestBody PersonDTO personDTO) {
		ResponseEnvelope<Void> responseEnvelope = null;
		if (personDTO == null) {
			responseEnvelope = new ResponseEnvelope<>(HttpStatus.BAD_REQUEST, "Person details are empty!");
		} else {
			if (!CommonUtil.isStringNotNullAndNotEmpty(personDTO.getFirstName())
					|| !CommonUtil.isStringNotNullAndNotEmpty(personDTO.getLastName())) {
				responseEnvelope = new ResponseEnvelope<>(HttpStatus.BAD_REQUEST, "Person first name and last name is mandatory!");
			} else {
				personService.createPerson(personDTO);
				responseEnvelope = new ResponseEnvelope<>(HttpStatus.CREATED, "Person record created!");
			}
		}
		return new ResponseEntity<>(responseEnvelope, responseEnvelope.getStatus());
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseEnvelope<Void>> updatePerson(@RequestBody PersonDTO personDTO) {
		ResponseEnvelope<Void> responseEnvelope = null;
		if (personDTO == null) {
			responseEnvelope = new ResponseEnvelope<>(HttpStatus.BAD_REQUEST, "Person details are empty!");
		} else {
			if(personDTO.getPersonId() == null) {
				responseEnvelope = new ResponseEnvelope<>(HttpStatus.BAD_REQUEST, "Need person id to update the record!");
			} else if (!CommonUtil.isStringNotNullAndNotEmpty(personDTO.getFirstName())
					|| !CommonUtil.isStringNotNullAndNotEmpty(personDTO.getLastName())) {
				responseEnvelope = new ResponseEnvelope<>(HttpStatus.BAD_REQUEST, "Person first name and last name is mandatory!");
			} else {
				Optional<Person> existingPerson = personService.getPersonById(personDTO.getPersonId());
				if(!existingPerson.isPresent()) {
					responseEnvelope = new ResponseEnvelope<>(HttpStatus.NO_CONTENT, "No record found for the provided person id!");
				} else {
					personService.updatePerson(personDTO, existingPerson.get());
					responseEnvelope = new ResponseEnvelope<>(HttpStatus.OK, "Person record updated!");
				}
			}
		}
		return new ResponseEntity<>(responseEnvelope, responseEnvelope.getStatus());
	}
	
	@GetMapping("/{personId}")
	public ResponseEntity<ResponseEnvelope<PersonDTO>> getPersonById(@PathVariable("personId") Long personId) {
		ResponseEnvelope<PersonDTO> responseEnvelope = null;
		if(personId == null) {
			responseEnvelope = new ResponseEnvelope<>(HttpStatus.BAD_REQUEST, "Need person id to fetch the record!");
		} else {
			Optional<Person> existingPerson = personService.getPersonById(personId);
			if(!existingPerson.isPresent()) {
				responseEnvelope = new ResponseEnvelope<>(HttpStatus.NO_CONTENT, "No record found for the provided person id!");
			} else {
				PersonDTO personDTO = PersonConverter.convertPersonEntityToPersonDTO(existingPerson.get());
				responseEnvelope = new ResponseEnvelope<>(HttpStatus.OK, "Person record found!", personDTO);
			}
		}
		return new ResponseEntity<>(responseEnvelope, responseEnvelope.getStatus());
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseEnvelope<List<PersonDTO>>> getAllPersons() {
		ResponseEnvelope<List<PersonDTO>> responseEnvelope = null;
		List<PersonDTO> personDTOs = personService.getAllPersons();
		
		if(personDTOs.isEmpty()) {
			responseEnvelope = new ResponseEnvelope<>(HttpStatus.NO_CONTENT, "No person records found!");
		} else {
			responseEnvelope = new ResponseEnvelope<>(HttpStatus.OK, "All person records found!", personDTOs);
		}
		
		return new ResponseEntity<>(responseEnvelope, responseEnvelope.getStatus());
	}
	
	@DeleteMapping("/remove/{personId}")
	public ResponseEntity<ResponseEnvelope<Void>> removePersonById(@PathVariable("personId") Long personId) {
		ResponseEnvelope<Void> responseEnvelope = null;
		if(personId == null) {
			responseEnvelope = new ResponseEnvelope<>(HttpStatus.BAD_REQUEST, "Need person id to remove the record!");
		} else {
			Optional<Person> existingPerson = personService.getPersonById(personId);
			if(!existingPerson.isPresent()) {
				responseEnvelope = new ResponseEnvelope<>(HttpStatus.NO_CONTENT, "No record found for the provided person id!");
			} else {
				personService.removePersonById(personId);
				responseEnvelope = new ResponseEnvelope<>(HttpStatus.OK, "Person record removed!");
			}
		}
		return new ResponseEntity<>(responseEnvelope, responseEnvelope.getStatus());
	}
}
