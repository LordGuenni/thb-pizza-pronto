package de.thb.dim.pizzaPronto;

import java.util.Objects;

public class DeliveryManVO extends EmployeeVO {

	private String driverLicence;

	public DeliveryManVO(String personnelNo, String lastName, String firstName) {
		super(personnelNo, lastName, firstName);
	}

	public DeliveryManVO() {
		this(null, null, null);
	}

	@Override
	public String getPersonnelNo() {

		return super.getPersonnelNo();
	}

	@Override
	public void setPersonnelNo(String personnelNo) {

		super.setPersonnelNo(personnelNo);
	}

	@Override
	public float getSalary() {

		return super.getSalary();
	}

	@Override
	public void setSalary(float salary) {

		super.setSalary(salary);
	}

	@Override
	public int getVacationDays() {

		return super.getVacationDays();
	}

	@Override
	public void setVacationDays(int vacationDays) {

		super.setVacationDays(vacationDays);
	}

	@Override
	public String getLastName() {

		return super.getLastName();
	}

	@Override
	public void setLastName(String lastName) {

		super.setLastName(lastName);
	}

	@Override
	public String getFirstName() {

		return super.getFirstName();
	}

	@Override
	public void setFirstName(String firstName) {

		super.setFirstName(firstName);
	}

	@Override
	public String getStreet() {

		return super.getStreet();
	}

	@Override
	public void setStreet(String street) {

		super.setStreet(street);
	}

	@Override
	public int getHouseNumber() {

		return super.getHouseNumber();
	}

	@Override
	public void setHouseNumber(int houseNumber) {

		super.setHouseNumber(houseNumber);
	}

	public String getDriverLicence() {
		return driverLicence;
	}

	public void setDriverLicence(String driverLicence) {
		this.driverLicence = driverLicence;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(driverLicence);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeliveryManVO other = (DeliveryManVO) obj;
		System.out.println(this.getPersonnelNo() + "  -  " + other.getPersonnelNo() + "  -  "
				+ Objects.equals(this.getPersonnelNo(), other.getPersonnelNo()));
		return Objects.equals(this.getPersonnelNo(), other.getPersonnelNo());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeliveryManVO [driverLicence=");
		builder.append(driverLicence);
		builder.append(", personnelNo=");
		builder.append(personnelNo);
		builder.append(", salary=");
		builder.append(salary);
		builder.append(", vacationDays=");
		builder.append(vacationDays);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", street=");
		builder.append(street);
		builder.append(", houseNumber=");
		builder.append(houseNumber);
		builder.append("]");
		return builder.toString();
	}

}