package de.thb.dim.pizzaPronto;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class CustomerVO extends PersonVO {

	private int id;
	private static int nextId = 0;

	private String gender;
	private LocalDate dateOfBirth;
	
	private OrderVO order;

	public CustomerVO(String lastName, String firstName, String street, int houseNumber, String gender,LocalDate dateOfBirth) {
		
		super(lastName, firstName, street, houseNumber);
		
		id = nextId;
		nextId++;
		this.setGender(gender);
		this.setDateOfBirth(dateOfBirth);

	}

	public CustomerVO(String lastName, String firstName, LocalDate dateOfBirth) {
		this(lastName, firstName, null, 0, null, dateOfBirth);
		
	}

	public CustomerVO() {
		this(null, null, null);
	}

	public short calculateAge() {
		short age = -1;
		LocalDate today = LocalDate.now();
		if (dateOfBirth != null && dateOfBirth.isBefore(today)) {
			age = (short) Period.between(dateOfBirth, today).getYears();
		}
		return age;
	}

	public boolean hasOrder() {

		if (getOrder() == null) {
			return false;
		} else {
			return true;
		}

	}

	private String dobToString() {
		return dateOfBirth.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));

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
		return Objects.equals(dateOfBirth, other.dateOfBirth);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dateOfBirth);
		return result;
	}

	@Override
	public String toString() {
		String person_string = super.toString();

		StringBuilder s = new StringBuilder();
		s.append("Id: " + this.getId() + "\n");
		s.append("Gender: " + this.getGender() + "\n");
		s.append("Date of Birth: " + this.dobToString() + "\n");
		s.append("Age: " + this.calculateAge() + "\n");
		String customer_string = person_string + s.toString();
		return customer_string;

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
		if (calculateAge() < 18) {
			this.dateOfBirth = null;
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
