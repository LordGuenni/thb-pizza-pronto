package de.thb.dim.pizzaPronto;

import java.util.Objects;

public class DessertVO extends DishVO{

	
	public DessertVO(int number, String name, float price) {
		this.setNumber(number);
		this.setName(name);
		this.setPrice(price);
		
	}

	public DessertVO() {
		super();
	}

	public String[] getIngredients(){
		return super.getIngredients();
	}

	public void setIngredients(String[] ingredients){
		super.setIngredients(ingredients);
	}


	public String getName() {
		return super.getName();
	}

	public void setName(String name) {
		super.setName(name);
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

	public boolean equals(Object DessertLocal) {

		if (this == DessertLocal) {
			return true;
		}
		if (DessertLocal == null) {
			return false;
		}
		if (this.getClass() != DessertLocal.getClass()) {
			return false;
		}
		DessertVO other = (DessertVO) DessertLocal;

		return Objects.equals(this.getPrice(), other.price) && Objects.equals(getName(), other.name);
				
	}

	public int hashCode() {
		return Objects.hash(getNumber(), getPrice(), getName());

	}

	public String toString() {
		
		return super.toString();

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
		return getName();
	}

	@Override
	public int getNumberOfDish() {
		return getNumber();
	}

	
 
}
