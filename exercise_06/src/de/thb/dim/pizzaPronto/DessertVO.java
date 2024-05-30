package de.thb.dim.pizzaPronto;

public class DessertVO extends DishVO{

	
	public DessertVO(int number, String name, float price) {
		super(number,name,price);
	}

	public DessertVO() {
		super(0,null,0.00f);
	}

	@Override
	public String getNameOfDish() {
		return getName();
	}

	@Override
	public int getNumberOfDish() {
		return getNumber();
	}

}
