package de.thb.dim.pizzaPronto;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class CustomerVO {
	
	private String lastName;
	private String firstName;
	private String gender;
	private LocalDate dateOfBirth;
	private int id;
	private static int nextId=0;
	
	public CustomerVO(String lastName, String firstName, String gender, LocalDate dateOfBirth) {
		this.id=nextId++;
		this.setLastName(lastName);
		this.setFirstName(firstName);
		this.setGender(gender);
		this.setDateOfBirth(dateOfBirth);
		
	}
	
	public CustomerVO(String lastName, String firstName, LocalDate dateOfBirth) {
		this.id=nextId++;
		this.lastName = lastName;
		this.setLastName(lastName);
		this.setFirstName(firstName);
		this.setGender(gender);
		this.setDateOfBirth(dateOfBirth);
	}
	
	public CustomerVO() {
		this(null, null, null,null);
	}
	
	private String dobToString(){
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
		if(calculateAge()<18) {
			this.dateOfBirth = null;
		}
		
	}
	


public boolean equals(Object CustomerLocal) {
		
		if(this == CustomerLocal) {
			return true;
		}
		if(CustomerLocal == null){
			return false;
		}
		if(this.getClass() != CustomerLocal.getClass()) {
			return false;
		}
		CustomerVO customer = (CustomerVO) CustomerLocal;
		
		return Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(dateOfBirth, customer.dateOfBirth);
		
	}
   
	public int hashCode() {
		return Objects.hash(firstName,lastName,dateOfBirth);
	
	}
	
	public String toString() {
		String ret="Customer:"+getId()+"\n";
        ret+="\t Last Name:\t"+((lastName==null)?"":lastName)+"\n";
        ret+="\t First Name:\t"+((firstName==null)?"":firstName)+"\n";
        ret+="\t Gender:\t"+((lastName==null)?"":gender)+"\n";
        ret+="\t Date o Birth:\t"+((dateOfBirth==null)?"":dobToString())+"\n";
        ret+="\t Age:\t\t"+calculateAge()+"\n";
        return ret;
		
		
	}
	
	public int getId() {
		return id;
	}
	public static int getNextId() {
		return nextId;
	}
	public short calculateAge() {
		short age = -1;
		LocalDate today = LocalDate.now();
		if(dateOfBirth != null && dateOfBirth.isBefore(today)) {
			age =(short) Period.between(dateOfBirth, today).getYears();
		}
		return age;
	}
}
