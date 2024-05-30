package de.thb.dim.pizzaPronto;

import java.util.Arrays;
import java.util.Objects;

public class PizzaVO extends DishVO{

	private int size;

	public PizzaVO(int number, String name, String[] ingredients, float price, int size) {
		this.setNumber(number);;
		this.setName(name);;
		this.setIngredients(ingredients);;
		this.setPrice(price);
		this.setSize(size);

	}

	public PizzaVO(int number, String name, String[] ingredients, float price) {
		this.setNumber(number);;
		this.setName(name);;
		this.setIngredients(ingredients);;
		this.setPrice(price);

	}

	public PizzaVO() {
		this(0, null, null, 0,0);
	}

	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getName() {
		return super.getName();
	}

	public void setName(String name) {
		super.setName(name);
	}

	public String[] getIngredients() {
		return super.getIngredients();
	}

	public void setIngredients(String[] ingredients) {
		super.setIngredients(ingredients);
	}

	public float getPrice() {
		return super.getPrice();
	}

	public void setPrice(float price) {
		if (price < 0.0f) {
			super.setPrice(0.0f);
		} else {
			super.setPrice(price);
		}

	}

	public boolean equals(Object PizzaLocal) {

		if (this == PizzaLocal) {
			return true;
		}
		if (PizzaLocal == null) {
			return false;
		}
		if (this.getClass() != PizzaLocal.getClass()) {
			return false;
		}
		PizzaVO other = (PizzaVO) PizzaLocal;

		if (!Arrays.equals(ingredients, other.getIngredients())) {
			return false;
		}
		return Objects.equals(this.getPrice(), other.price) && Objects.equals(getName(), other.name)
				&& Arrays.equals(getIngredients(), other.getIngredients());

	}

	public int hashCode() {
		return Objects.hash(getPrice(), getName(), getIngredients());

	}

	public String toString() {
		if (ingredients != null) {

			StringBuilder s = new StringBuilder();

			s.append(super.toString());

			switch(getSize() ){
				case 1:
				s.append("Size: Normal" + "\n");
				break;
				
				case 2:
				s.append("Size: Grande" + "\n");
				break;
				
				default:
				s.append("Size: 404 Size Not Found" + "\n");
				break;
	
			}		
			String pizza_string = s.toString();
			return pizza_string;

		} else {
			StringBuilder s = new StringBuilder();
			s.append("Price: " + this.getPrice() + "\n");
			s.append("Name: " + this.getName() + "\n");
			s.append("Size: " + this.getSize()+ "\n");

			String pizza_string = s.toString();
			return pizza_string;

		}

	}
/*	public PizzaVO clone() {
 * 		PizzaVO localPizza;
		localPizza = new PizzaVO(this.name, this.getIngredients().clone(), this.price);
		return localPizza;
		}
 * 		
 * 
 * 
 */

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

}
