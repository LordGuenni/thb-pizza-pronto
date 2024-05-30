package de.thb.dim.pizzaPronto.valueObjects;

import java.awt.Color;

public class ChefVO extends EmployeeVO{
	
	private Color colorApron;

	public ChefVO(String personellNo,String lastName,String firstName) {
		super(personellNo,lastName,firstName);
		
	}
	
	public ChefVO() {
		this(null,null,null);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nChef:\n");
		sb.append(super.toString());
		if(colorApron != null)
			sb.append("\nApron " + colorApron.toString());

		return sb.toString();
	}

	public Color getColorApron() {
		return colorApron;
	}

	public void setColorApron(Color colorApron) {
		this.colorApron = colorApron;
	}
	
}
	
