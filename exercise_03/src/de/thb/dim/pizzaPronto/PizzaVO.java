package de.thb.dim.pizzaPronto;

import java.util.Arrays;
import java.util.Objects;

public class PizzaVO {

	private String name;
	private String[] ingredients;
	private float price;
	
	public PizzaVO(String name, String[] ingredients, float price) {
		this.name = name;
		this.ingredients = ingredients;
		this.setPrice(price);
		
	}
	
	public PizzaVO() {
		this(null,null,0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getIngredients() {
		return ingredients;
	}

	public void setIngredients(String[] ingredients) {
		this.ingredients = ingredients;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		if(price < 0.0f) {
			this.price = 0.0f;
		}else {
			this.price = price;
		}
		
	}
	
public boolean equals(Object PizzaLocal) {
		
		if(this == PizzaLocal) {
			return true;
		}
		if(PizzaLocal == null){
			return false;
		}
		if(this.getClass() != PizzaLocal.getClass()) {
			return false;
		}
		PizzaVO other = (PizzaVO) PizzaLocal;
		
		
		
		if(!Arrays.equals(ingredients,other.getIngredients())) {
			return false;
		}
			return Objects.equals(price, other.price) && Objects.equals(name, other.name) && Arrays.equals(ingredients,other.getIngredients());
			
		
		
	}
 
	public int hashCode() {
		return Objects.hash(price,name,ingredients);
	
	}
	
	public String toString() {
		if(ingredients != null) {
			
			StringBuilder s = new StringBuilder();
	    	s.append("Price: " + this.getPrice() + "\n");
	    	s.append("Name: " + this.getName() + "\n");
	   		s.append("Ingredients: " + Arrays.toString(this.ingredients));
	   		String pizza_string = s.toString();
	   		return pizza_string;
		
		}
		else {
			StringBuilder s = new StringBuilder();
	    	s.append("Price: " + this.getPrice() + "\n");
	    	s.append("Name: " + this.getName() + "\n");
	    	s.append("Ingredients: "+ null);
	    	String pizza_string = s.toString();
	    	return pizza_string;
			
			
			
		
		}
		
	}
	
	public PizzaVO clone() {
		PizzaVO localPizza;
		localPizza = new PizzaVO(this.name,this.ingredients.clone(),this.price);
		return localPizza;
	}
	
}