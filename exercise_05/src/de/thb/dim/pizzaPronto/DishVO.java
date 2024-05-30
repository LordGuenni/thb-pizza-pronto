package de.thb.dim.pizzaPronto;

import java.util.Arrays;

public abstract class DishVO {

    protected int number;
    protected String name;
    protected String[] ingredients;
    protected float price;


    public DishVO(int number, String name, String[] ingredients, float price) {
        this.number = number;
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
    }

    public DishVO(int number, String name, float price) {
        this.number = number;
        this.name = name;
        this.price = price;
    }

    public DishVO(){
        this(0,null,null,0.0f);
    }


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
        this.price = price;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + number;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + Arrays.hashCode(ingredients);
        result = prime * result + Float.floatToIntBits(price);
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
        if (number != other.number)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (!Arrays.equals(ingredients, other.ingredients))
            return false;
        if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
            return false;
        return true;
    }

    @Override
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

// Andere Methoden

    public String ingredientsToString() {
        if(ingredients != null){
            return Arrays.toString(ingredients);
            
        }else{
            return "";
        }
       
    }

    public abstract String getNameOfDish();
        
    public abstract int getNumberOfDish();


    
}

