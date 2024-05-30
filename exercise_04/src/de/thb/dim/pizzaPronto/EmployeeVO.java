package de.thb.dim.pizzaPronto;

import java.util.Objects;

public class EmployeeVO extends PersonVO {

	protected String personnelNo;
	protected float salary;
	protected int vacationDays;

	public EmployeeVO(String personnelNo, String lastName, String firstName) {
		this.setLastName(lastName);
		this.setFirstName(firstName);
		this.setPersonnelNo(personnelNo);
	}

	public EmployeeVO() {
		this(null, null, null);
	}

	public String getPersonnelNo() {
		return personnelNo;
	}

	public void setPersonnelNo(String personnelNo) {
		this.personnelNo = personnelNo;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public int getVacationDays() {
		return vacationDays;
	}

	public void setVacationDays(int vacationDays) {
		this.vacationDays = vacationDays;
	}

	public String getLastName() {
		return super.getLastName();
	}

	public void setLastName(String lastName) {
		super.setLastName(lastName);
	}

	public String getFirstName() {
		return super.getFirstName();
	}

	public void setFirstName(String firstName) {
		super.setFirstName(firstName);
	}

	public String getStreet() {
		return super.getStreet();
	}

	public void setStreet(String street) {
		super.setStreet(street);
	}

	public int getHouseNumber() {
		return super.getHouseNumber();
	}

	public void setHouseNumber(int houseNumber) {
		super.setHouseNumber(houseNumber);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(personnelNo);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeVO other = (EmployeeVO) obj;
		return Objects.equals(personnelNo, other.personnelNo);
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("EmployeeVO [personnelNo=");
		s.append(personnelNo);
		s.append(", salary=");
		s.append(salary);
		s.append(", vacationDays=");
		s.append(vacationDays);
		s.append(", lastName=");
		s.append(lastName);
		s.append(", firstName=");
		s.append(firstName);
		s.append(", street=");
		s.append(street);
		s.append(", houseNumber=");
		s.append(houseNumber);
		s.append("]");
		return s.toString();
	}

}