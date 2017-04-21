package com.springDmo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springDmo.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{

	
	
	public default boolean isUserExist(Person person){
			List<Person> listPerson;
			listPerson = findAll();
			for(Person p : listPerson)
			{
				if(person.getFname().equalsIgnoreCase(p.getFname())
						&& person.getLname().equalsIgnoreCase(p.getLname()))
				{
					return true;
				}
			}
		        return false;
		    
		
	}

}
