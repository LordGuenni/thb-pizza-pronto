package de.thb.dim.pizzaPronto.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import de.thb.dim.pizzaPronto.businessObjects.Ordering;

import de.thb.dim.pizzaPronto.valueObjects.MenuVO;


import de.thb.dim.pizzaPronto.valueObjects.CustomerVO;
import de.thb.dim.pizzaPronto.valueObjects.DishVO;
import de.thb.dim.pizzaPronto.valueObjects.Gender;

class TestDriverOrdering {

	public static void main(String[] args) {

		MenuVO menu;
		CustomerVO customer1;
		Ordering ordering1;
		Random zufall = new Random();
		List<DishVO> sB;

		

		// Kunde1
		customer1 = new CustomerVO("Mampf", "Manfred", "Essensstra�e", 42,
				Gender.M, LocalDate.of(1990, 6, 28));
		ordering1 = new Ordering();
		ordering1.startNewOrder(customer1);
		
		menu = Ordering.getMenu();
		// zuf�llige Testbestellung f�r Kunde1 speichern aus Speisekarte
		for (int i = 0; i < 10; i++) {
			ordering1.addDish(menu.getDish(zufall
					.nextInt(18)));
		}
		
		sB = ordering1.getCurrentOrder().getShoppingBasket();
		System.out.println(sB);

	
		System.out.println("Natürliche Ordnung");
		sB = ordering1.sortShoppingBasket();
		System.out.println(sB);
		

		System.out.println("Number of Dish");
		sB = ordering1.sortShoppingBasketByNumber();
		System.out.println(sB);
		

		System.out.println("Price");
		sB = ordering1.sortShoppingBasketByPrice();
		System.out.println(sB);
		
	}
}
