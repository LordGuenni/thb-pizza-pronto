package de.thb.dim.pizzaPronto.valueObjects;

import java.util.Objects;

public abstract class PersonVO {

	protected String lastName;
	protected String firstName;
	protected String street;
	protected int houseNumber;

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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	//

	public PersonVO(String lastName, String firstName, String street, int houseNumber) {
		this.setLastName(lastName);
		this.setFirstName(firstName);
		this.setStreet(street);
		this.setHouseNumber(houseNumber);
	}

	public PersonVO(String lastName, String firstName) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}

	public PersonVO() {
		this(null, null, null, 0);
	}

	public int hashCode() {
		return Objects.hash(firstName, houseNumber, lastName, street);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonVO other = (PersonVO) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(houseNumber, other.houseNumber)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(street, other.street);
	}

	public String toString() {

		StringBuilder s = new StringBuilder();
		s.append("PersonVO [lastName=");
		s.append(lastName);
		s.append(", firstName=");
		s.append(firstName);
		s.append(", street=");
		s.append(street);
		s.append(", houseNumber=");
		s.append(houseNumber);
		s.append("]");
		
		String personVO_string = s.toString();
		return personVO_string;

	}

}
