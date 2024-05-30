package de.thb.dim.pizzaPronto;

import java.awt.Color;
import java.util.Objects;

public class ChefVO {

	
	private String lastName;
    private String firstName;
    private Color colorApron;
    
    public ChefVO(String lastName, String firstName, Color colorApron){
    	this.lastName = lastName;
    	this.firstName = firstName;
    	this.colorApron = colorApron;
    }
    
    public ChefVO() {
    	
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

	public Color getColorApron() {
		return colorApron;
	}

	public void setColorApron(Color colorApron) {
		this.colorApron = colorApron;
	}
	
	
	public boolean equals(Object ChefLocal) {
		
		if(this == ChefLocal) {
			return true;
		}
		if(ChefLocal == null){
			return false;
		}
		if(getClass() != ChefLocal.getClass()) {
			return false;
		}
		ChefVO other = (ChefVO) ChefLocal;
		
		return Objects.equals(colorApron, other.colorApron) && Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName);
		
	}
   
	public int hashCode() {
		return Objects.hash(colorApron,firstName,lastName);
	
	}
	
	public String toString() {
		return this.lastName + this.firstName + this.colorApron;
		
	}
}
	
