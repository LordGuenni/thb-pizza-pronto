package de.thb.dim.pizzaPronto.valueObjects;

public class DessertVO extends DishVO{

	private static final long serialVersionUID = 1L;

	public DessertVO(int number, String name, float price) {
		super(number,name,price);
	}

	public DessertVO() {
		super(0,null,0.00f);
	}

	@Override
	public String getNameOfDish() {
		return this.name;
	}

	@Override
	public int getNumberOfDish() {
		return this.number;
	}

}
