package de.thb.dim.pizzaPronto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerVO {
	
	private String lastName;
	private String firstName;
	private String gender;
	private LocalDate dateOfBirth;
	

	
	public CustomerVO(String lastName, String firstName, String gender, LocalDate dateOfBirth) {
		
		this.setLastName(lastName);
		this.setFirstName(firstName);
		this.setGender(gender);
		this.setDateOfBirth(dateOfBirth);
		
	}
	
	public CustomerVO(String lastName, String firstName, LocalDate dateOfBirth) {
	
		this.setLastName(lastName);
		this.setFirstName(firstName);
		this.setDateOfBirth(dateOfBirth);
	}
	
	public CustomerVO() {
		this(null, null, null,null);
	}
	
	public String dobToString(){
		return dateOfBirth.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
		
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		
	
		this.dateOfBirth = dateOfBirth;
		}
		
	}

