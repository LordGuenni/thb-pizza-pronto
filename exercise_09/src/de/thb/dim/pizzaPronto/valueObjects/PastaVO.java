package de.thb.dim.pizzaPronto.valueObjects;

import java.util.Objects;

public class PastaVO extends DishVO{

	private static final long serialVersionUID = 1L;
	private int typeOfPasta;

	public PastaVO(int number, String name, String[] ingredients, float price, int pastaType) {
		super(number, name, ingredients, price);
		this.setTypeOfPasta(pastaType);

	}

	public PastaVO() {
		this(0, null, null, 0,0);
	}

//Standartmethoden

@Override
public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(typeOfPasta);
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
		PastaVO other = (PastaVO) obj;
		return typeOfPasta == other.typeOfPasta;
	}

	@Override
	public String getNameOfDish() {
		StringBuffer sb = new StringBuffer();
		sb.append("Pasta ");
		
		switch(typeOfPasta) {
			case 4 : 
				sb.append(getName() + " - Spaghetti");
				break;
			case 5 : 
				sb.append(getName() + " - Tortellini");
				break;
			case 6 : 
				sb.append(getName() + " - Gnocchi");
				break;
			default : 
				sb.append(getName());
				break;
		}
		
		return sb.toString();
	}	

@Override
	public int getNumberOfDish() {
		return getTypeOfPasta() * 100 + this.getNumber();
	}

// Getter und Setter

	public int getTypeOfPasta() {
		return typeOfPasta;
	}

	public void setTypeOfPasta(int pastaType) {
		this.typeOfPasta = pastaType;
	}

}
