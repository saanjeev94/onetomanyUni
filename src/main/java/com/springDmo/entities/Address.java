package com.springDmo.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Address {
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column (name = "Add_id")
	private int add_id;
	
//	@ManyToOne (fetch = FetchType.LAZY , cascade = CascadeType.ALL)
//	@JoinColumn (name = "id")
//	private Person person;
	
	@Column (name = "City")
	private String city;
	
	@Column (name = "Street")
	private String street;

	
	public Address(){}
	public Address( String city, String street) {
		super();
//		this.person = person;
		this.city = city;
		this.street = street;
	}



//	public Person getPerson() {
//		return person;
//	}
//
//
//
//	public void setPerson(Person person) {
//		this.person = person;
//	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getStreet() {
		return street;
	}



	public void setStreet(String street) {
		this.street = street;
	}



	public int getAdd_id() {
		return add_id;
	}



	@Override
	public String toString() {
		return "Address [add_id=" + add_id + ", city=" + city + ", street=" + street + "]";
	}




	
}
