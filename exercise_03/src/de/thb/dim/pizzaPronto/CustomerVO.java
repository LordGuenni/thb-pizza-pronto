package de.thb.dim.pizzaPronto;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


public class CustomerVO{
	
	

	private String lastName;
	private String firstName;
	private String gender;
	private LocalDate dateOfBirth;
	private int id;
	private static int nextId=0;
	private OrderVO order;
	
	public CustomerVO(String lastName, String firstName,String gender, LocalDate dateOfBirth) {
		this.id=nextId++;
		this.setLastName(lastName);
		this.setFirstName(firstName);
		this.setGender(gender);
		this.setDateOfBirth(dateOfBirth);
		
	}
	
	public CustomerVO(String lastName, String firstName, LocalDate dateOfBirth) {
		this.id=nextId++;
		this.setLastName(lastName);
		this.setFirstName(firstName);
		this.setDateOfBirth(dateOfBirth);
	}
	
	public CustomerVO() {
		this(null, null, null);
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
	
	private String dobToString(){
		return dateOfBirth.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
		
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
    	StringBuilder s = new StringBuilder();
    	s.append("Id: "+ this.getId());
    	s.append("Firstname: " + this.getFirstName() + "\n");
   		s.append("Lastname: " + this.getLastName() + "\n");
   		s.append("Gender: " + this.getGender() + "\n");
    	s.append("Date of Birth: " + this.dobToString()+ "\n");
    	s.append("Age: " + this.calculateAge()+ "\n");
   		String final_string = s.toString();
   		return final_string;
		
		
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

	public boolean hasOrder() {
	
		if(getOrder() == null) {
			return false;
		}else {
			return true;
		}
		
	}

	public void setOrder(OrderVO order) {
		this.order = order;
		
	}

	public OrderVO getOrder() {
		
		return order;
	}
}
