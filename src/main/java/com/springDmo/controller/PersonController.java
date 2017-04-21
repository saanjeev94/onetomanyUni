package com.springDmo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springDmo.entities.Address;
import com.springDmo.entities.Person;
import com.springDmo.repository.PersonRepository;

@RestController
@RequestMapping(value = "/person")
public class PersonController {
	
	@Autowired
	PersonRepository personRepository;
	
	 public static final Logger logger = LoggerFactory.getLogger(PersonController.class);
	 
	
	
	
	
	//----------------------- Insert----------------------\\
	//@PostMapping (value = "/insert/")
	 @RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<String> insert(@RequestBody Person person){
		 System.out.println("Creating Person " + person.getFname());
//		personRepository.save(p1);
//		personRepository.save(p2);
//		personRepository.save(p3);
		 logger.info("Creating User : {}", person);
		 if (personRepository.isUserExist(person)) {
			 logger.error("Unable to create. A User with name {} already exist", person.getFname()+" "+person.getLname());
	            return new ResponseEntity<String>("fail to insert", HttpStatus.CONFLICT);
	        }
		 personRepository.save(person);
		return new ResponseEntity<String> ("Inserted sucessfully", HttpStatus.OK);
	}
	
	//-------------------------display all user--------------------\\
	 @RequestMapping(value = "/display", method = RequestMethod.GET)
	 public ResponseEntity<List<Person>> listAllUsers() {
	        List<Person> person = personRepository.findAll();
	        if(person.isEmpty()){
	            return new ResponseEntity<List<Person>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Person>>(person, HttpStatus.OK);
	    }
	 
	 
	 
	//-------------------------display single user--------------------\\
		 @RequestMapping(value = "/display/{id}", method = RequestMethod.GET)
		 public ResponseEntity<Person> SingleUser(@PathVariable("id") int id) {
			 logger.info("Finding User with id {}", id);
		        List<Person> listPerson = personRepository.findAll();
		        Person person = null;
		        for(Person p : listPerson)
		        {
		        	if(p.getId() == id){
		        		person = p;
		        	}
		        }
		        if(person == null){
		            return new ResponseEntity(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		        }
		        return new ResponseEntity<Person>(person, HttpStatus.OK);
		    }
	 
	 //----------------------------update--------------//
	 @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<String> updateUser(@PathVariable("id") int id, @RequestBody Person person) {
	        logger.info("Updating User with id {}", id);
	 
	        Person currentPerson = personRepository.findOne(id);
	 
	        if (currentPerson == null) {
	            logger.error("Unable to update. User with id {} not found.", id);
	            return new ResponseEntity("Unable to upate. User with id " + id + " not found.",
	                    HttpStatus.NOT_FOUND);
	        }
	 
	        currentPerson.setFname(person.getFname());
	        currentPerson.setLname(person.getLname());
	        
	        List<Address> personAddress = person.getListAdress();
	        currentPerson.setListAdress(personAddress);
//	        currentPerson.setAddress(person.getAddress());
	 
	        personRepository.save(currentPerson);
	        return new ResponseEntity<String>("updating person sucessfully", HttpStatus.OK);
	    }
	 
	 
	 //----------------------- delete a user ----------------------//
	 @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
	        logger.info("Fetching & Deleting User with id {}", id);
	 
	        Person person = personRepository.findOne(id);
	        if (person == null) {
	            logger.error("Unable to delete. User with id {} not found.", id);
	            return new ResponseEntity("Unable to delete. User with id " + id + " not found.",
	                    HttpStatus.NOT_FOUND);
	        }
	        personRepository.delete(id);
	        return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
	    }
	 

	 // ------------------- Delete All Users-----------------------------
	 
	    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	    public ResponseEntity<Person> deleteAllUsers() {
	        logger.info("Deleting All Users");
	 
	        personRepository.deleteAll();
	        return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
	    }
}
