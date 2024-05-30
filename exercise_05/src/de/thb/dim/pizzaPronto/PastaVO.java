package de.thb.dim.pizzaPronto;

import java.util.Arrays;
import java.util.Objects;

public class PastaVO extends DishVO{

	private int pastaType;

	public PastaVO(int number, String name, String[] ingredients, float price, int pastaType) {
		this.setNumber(number);;
		this.setName(name);;
		this.setIngredients(ingredients);;
		this.setPrice(price);
		this.setTypeOfPasta(pastaType);

	}

	public PastaVO() {
		this(0, null, null, 0,0);
	}

	
	public int getTypeOfPasta() {
		return pastaType;
	}

	public void setTypeOfPasta(int pastaType) {
		this.pastaType = pastaType;
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

	public boolean equals(Object PastaLocal) {

		if (this == PastaLocal) {
			return true;
		}
		if (PastaLocal == null) {
			return false;
		}
		if (this.getClass() != PastaLocal.getClass()) {
			return false;
		}
		PastaVO other = (PastaVO) PastaLocal;

		if (!Arrays.equals(ingredients, other.getIngredients())) {
			return false;
		}
		return Objects.equals(this.getPrice(), other.price) && Objects.equals(getName(), other.name)
				&& Arrays.equals(getIngredients(), other.getIngredients());

	}

	public int hashCode() {
		return Objects.hash(getPrice(), getName(), getIngredients(), getTypeOfPasta());

	}

	public String toString() {
		if (ingredients != null) {

			StringBuilder s = new StringBuilder();
			s.append("Pasta"+ "\n");
			s.append("Price: " + this.getPrice() + "\n");
			s.append("Name: " + this.getName() + "\n");
			s.append("Ingredients " + Arrays.toString(this.getIngredients()) + "\n");
			
            switch(getTypeOfPasta()){
                case 4:
                s.append("PastaType: Spaghetti" + "\n");
                break;

                case 5:
                s.append("PastaType: Tortellini" + "\n");
                break;

                case 6:
                s.append("PastaType: Gnocchi" + "\n");
                break;
            }

			String Pasta_string = s.toString();
			return Pasta_string;

		}else {
			StringBuilder s = new StringBuilder();
			s.append("Price: " + this.getPrice() + "\n");
			s.append("Name: " + this.getName() + "\n");

			switch(getTypeOfPasta()){
                case 4:
                s.append("PastaType: Spaghetti" + "\n");
                break;

                case 5:
                s.append("PastaType: Tortellini" + "\n");
                break;

                case 6:
                s.append("PastaType: Gnocchi" + "\n");
                break;
		}

        String pizza_string = s.toString();
		return pizza_string;

	}

    }
    



/*	public PastaVO clone() {
 * 		PastaVO localPasta;
		localPasta = new PastaVO(this.name, this.getIngredients().clone(), this.price);
		return localPasta;
		}
 * 		
 * 
 * 
 */

	@Override
	public String getNameOfDish() {

		StringBuilder s = new StringBuilder();
			s.append("Pasta " + this.getName());
			s.append(typeToString());

		String NameOfDish = s.toString();
		return NameOfDish;
	}

	@Override
	public int getNumberOfDish() {
		return getTypeOfPasta() * 100 + getNumber();
	}

	public String typeToString(){

		
		switch(getTypeOfPasta() ){
			case 4:
			return "Spaghetti";
			
			case 5:
			return "Tortellini";
			
			case 6:
			return "Gnocchi";

			default:
			return "ungültiger Type";

		}
		
		
	}

	
 
}
