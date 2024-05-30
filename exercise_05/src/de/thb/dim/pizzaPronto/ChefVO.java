package de.thb.dim.pizzaPronto;

import java.awt.Color;
import java.util.Objects;

public class ChefVO extends EmployeeVO{
	

	private Color colorApron;

	public ChefVO(String personellNo,String lastName,String firstName) {
		super(personellNo,lastName,firstName);
		
	}
	
	public ChefVO() {
		this(null,null,null);
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

	public Color getColorApron() {
		return colorApron;
	}

	public void setColorApron(Color colorApron) {
		this.colorApron = colorApron;
	}
	public String getPersonnelNo() {
		return super.getPersonnelNo();
	}

	public void setPersonnelNo(String personnelNo) {
		super.setPersonnelNo(personnelNo);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(colorApron);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj==null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChefVO other = (ChefVO) obj;
		return Objects.equals(this.getPersonnelNo(),other.getPersonnelNo());
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("ChefVO [colorApron=");
		s.append(colorApron);
		s.append(", personnelNo=");
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
	
