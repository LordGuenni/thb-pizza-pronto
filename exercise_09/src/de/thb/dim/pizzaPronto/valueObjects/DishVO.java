package de.thb.dim.pizzaPronto.valueObjects;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public abstract class DishVO implements Comparable<DishVO>, Cloneable, Serializable {

    private static final long serialVersionUID = 1L;
    protected int number;
    protected String name;
    protected String[] ingredients;
    protected float price;


    public DishVO(int number, String name, String[] ingredients, float price) {
        this.setNumber(number);
        this.setName(name);
        this.setIngredients(ingredients);
        this.setPrice(price);
    }

    public DishVO(int number, String name, float price) {
        this(number,name,null,price);
    }

    public DishVO(){
        this(0,null,null,0.0f);
    }

// Standardmethoden



	public String toString() {

		StringBuilder s = new StringBuilder();

		
		s.append("Number: " + this.getNumberOfDish() + "\n");
		s.append("Name: " + this.getNameOfDish() + "\n");
        if(getIngredients() != null){
            s.append("Ingredients: " + ingredientsToString() + "\n");
        }
        s.append("Price: " + this.getPrice() + "\n");
	
		return s.toString();

	}


    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(ingredients);
		result = prime * result + Objects.hash(name, number, price);
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
		DishVO other = (DishVO) obj;
		return Arrays.equals(ingredients, other.ingredients) && Objects.equals(name, other.name)
				&& number == other.number && Float.floatToIntBits(price) == Float.floatToIntBits(other.price);
	}


    public Object clone()  {
		
		DishVO dish = null;
        
		try {
			dish = (DishVO) super.clone();
		} catch (CloneNotSupportedException e) {
		       // Should not happen because of Cloneable      
			throw new InternalError();
		}
		return dish;
	}

// Andere Methoden

public String ingredientsToString() {
    if(ingredients != null){
        return Arrays.toString(ingredients);
        
    }else{
        return "";
    }
   
}

@Override
    public int compareTo(DishVO otherDish) {
        return this.getNameOfDish().compareTo(otherDish.getNameOfDish());
    }

// Abstract Methods

public abstract String getNameOfDish();
        
public abstract int getNumberOfDish();


// Getter und Setter
    
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
        this.price = (price > 0f) ? price : 0f;
    }
       
}

