package com.springDmo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column ( name = "First_name")
	private String fname;
	
	@Column (name = "Last_name")
	private String lname;
	
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id", referencedColumnName = "id")
	private List<Address> listAdress;

	public Person(){
		
	}
	
	

	public Person(String fname, String lname, List<Address> listAdress) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.listAdress = listAdress;
	}



	public String getFname() {
		return fname;
	}

	public void setFname(String fame) {
		this.fname = fame;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	

	

	public List<Address> getListAdress() {
		return listAdress;
	}



	public void setListAdress(List<Address> listAdress) {
		this.listAdress = listAdress;
	}



	public int getId() {
		return id;
	}



	@Override
	public String toString() {
		return "Person [id=" + id + ", fname=" + fname + ", lname=" + lname + ", listAdress=" + listAdress + "]";
	}
	
	
	
}
