package de.thb.dim.pizzaPronto;

import java.util.Objects;

public class PizzaVO extends DishVO{

	private int size;

	public PizzaVO(int number, String name, String[] ingredients, float price, int size) {
		super(number, name, ingredients, price);
		this.setSize(size);

	}

	public PizzaVO() {
		this(0, null, null, 0.0f,1);
	}

	// Standart Methoden

	@Override
		public int hashCode() {
			final int prime = 31;
			int result = super.hashCode();
			result = prime * result + Objects.hash(size);
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
			PizzaVO other = (PizzaVO) obj;
			return size == other.size;
		}

	@Override
	public String getNameOfDish() {

		StringBuilder s = new StringBuilder();
			s.append("Pizza " + this.getName());
			switch(getSize() ){
				case 1:
				s.append(" - Normal");
				break;
				
				case 2:
				s.append(" - Grande");
				break;

				default:
				s.append(" - 404 Size Not Found");
				break;
	
			}	

		String NameOfDish = s.toString();
		return NameOfDish;
	}


	@Override
	public int getNumberOfDish() {
		return getNumber() * 10 + getSize();
	}

	// Getter und Setter
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
