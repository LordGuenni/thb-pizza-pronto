package de.thb.dim.pizzaPronto.valueObjects;

import java.util.Objects;

public abstract class EmployeeVO extends PersonVO {

	protected String personnelNo;
	protected float salary;
	protected int vacationDays;

	public EmployeeVO(String personnelNo, String lastName, String firstName) {
		super(lastName, firstName);
		this.setPersonnelNo(personnelNo);
	}

	public EmployeeVO() {
		this(null, null, null);
	}

	// Java standard operations

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Objects.hash(personnelNo);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		EmployeeVO other = (EmployeeVO) obj;
		return Objects.equals(personnelNo, other.personnelNo);

	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();

		s.append("Personnel No: " + personnelNo + "\n");
		s.append(super.toString());
		s.append("Salary: " + salary + "\n");
		s.append("Vacation Days: " + vacationDays + "\n");
		return s.toString();

	}

	// Getter and Setter
	
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

	

}