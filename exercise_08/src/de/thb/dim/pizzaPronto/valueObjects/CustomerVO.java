package de.thb.dim.pizzaPronto.valueObjects;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import de.thb.dim.pizzaPronto.valueObjects.exceptions.CustomerNoDateOfBirthException;
import de.thb.dim.pizzaPronto.valueObjects.exceptions.CustomerTooYoungException;

public class CustomerVO extends PersonVO {

	private int id;
	private static int nextId = 0;

	private Gender gender;
	private LocalDate dateOfBirth;
	
	private OrderVO order;

	public CustomerVO(String lastName, String firstName, String street, int houseNumber, Gender gender, LocalDate dob) throws NullPointerException, CustomerTooYoungException {
		super(lastName, firstName, street, houseNumber);
		id = nextId;
		nextId++;
		setGender(gender);
		setDateOfBirth(dob);

	}

	
	public CustomerVO(String lastName, String firstName, LocalDate dob) throws NullPointerException, CustomerTooYoungException {
		this(lastName, firstName, null, 0, null, dob);

	}

	public short calculateAge() throws CustomerNoDateOfBirthException {
		short alter = -1;
		Period age;
		LocalDate today = LocalDate.now();
		
		if (dateOfBirth == null) {
			throw new CustomerNoDateOfBirthException("Internal error: No date of birth.");
		}

		if (dateOfBirth != null) {
			age = Period.between(dateOfBirth, today);
			alter = (short) age.getYears();
		}
		return alter;
	}

	public boolean hasOrder() {

		if (getOrder() == null) {
			return false;
		} else {
			return true;
		}

	}

	private String dobToString() throws CustomerNoDateOfBirthException {
		String s = null;
		if (dateOfBirth == null) {
			throw new CustomerNoDateOfBirthException("Internal error: No date of birth.");
		}
		else s = dateOfBirth.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
		return s;
	}

	// Stdanardmethoden

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerVO other = (CustomerVO) obj;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		return result;
	}

	@Override
	public String toString() {
	StringBuilder sb = new StringBuilder();
		
		sb.append("ID: " + getId());
		
		sb.append("\t" + super.toString());
		
		sb.append("\t" + this.getGender().toString());
		
		try {
			sb.append("\tDate of Birth: " + dobToString());
			sb.append("\tAge: " + calculateAge());
		} catch (CustomerNoDateOfBirthException e) {
			System.err.println(e.getMessage());
		}
		
		if (hasOrder()) {
			sb.append("\nOrder available: \n");
			sb.append(order.toString());
		}
		else sb.append("\nNo order available\n");
		
		return sb.toString();
	}






	

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dob) throws CustomerTooYoungException, NullPointerException{
		
		Objects.requireNonNull(dob, "dob must not be null");
		
		dateOfBirth = dob;
		
		try {
			if (this.calculateAge() < 18)
				throw new CustomerTooYoungException("Customer is not an adult.");
		} catch (CustomerNoDateOfBirthException e) {
			System.err.println(e.getMessage());
		}
	}

	
	public int getId() {
		return id;
	}

	public static int getNextId() {
		return nextId;
	}

	

	
	public void setOrder(OrderVO order) {
		this.order = order;

	}

	public OrderVO getOrder() {

		return order;
	}
}
