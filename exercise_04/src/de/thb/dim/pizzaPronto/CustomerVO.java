package de.thb.dim.pizzaPronto;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class CustomerVO extends PersonVO {

	private String gender;
	private LocalDate dateOfBirth;
	private int id;
	private static int nextId = 0;
	private OrderVO order;

	public CustomerVO(String lastName, String firstName, String street, int houseNumber, String gender,
			LocalDate dateOfBirth) {
		this.id = nextId++;
		super.setLastName(lastName);
		super.setFirstName(firstName);
		super.setStreet(street);
		super.setHouseNumber(houseNumber);
		this.setGender(gender);
		this.setDateOfBirth(dateOfBirth);

	}

	public CustomerVO(String lastName, String firstName, LocalDate dateOfBirth) {
		this.id = nextId++;
		super.setLastName(lastName);
		super.setFirstName(firstName);
		this.setDateOfBirth(dateOfBirth);
	}

	public CustomerVO() {
		this(null, null, null, 0, null, null);
	}

	private String dobToString() {
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
		if (calculateAge() < 18) {
			this.dateOfBirth = null;
		}

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;

		CustomerVO other = (CustomerVO) obj;
		boolean Customer = Objects.equals(this.dateOfBirth, other.dateOfBirth) && Objects.equals(order, other.order);
		boolean Person = super.equals(obj);

		if (Customer && Person == true) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dateOfBirth, gender, order);
		return result;
	}

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

	public int getId() {
		return id;
	}

	public static int getNextId() {
		return nextId;
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

	public void setOrder(OrderVO order) {
		this.order = order;

	}

	public OrderVO getOrder() {

		return order;
	}
}
