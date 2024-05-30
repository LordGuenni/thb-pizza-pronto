package de.thb.dim.pizzaPronto.controller;

import java.time.LocalDate;

import java.util.Random;

import de.thb.dim.pizzaPronto.businessObjects.Ordering;

import de.thb.dim.pizzaPronto.valueObjects.MenuVO;


import de.thb.dim.pizzaPronto.valueObjects.CustomerVO;
import de.thb.dim.pizzaPronto.valueObjects.Gender;

class TestDriver {

	public static void main(String[] args) {

		MenuVO menu;
		CustomerVO customer1,customer2;
		Ordering ordering1, ordering2;
		Random zufall = new Random();

		

		// Kunde1
		customer1 = new CustomerVO("Mampf", "Manfred", "Essensstra�e", 42,
				Gender.M, LocalDate.of(1990, 6, 28));
		ordering1 = new Ordering();
		ordering1.startNewOrder(customer1);
		
		menu = Ordering.getMenu();
		// zuf�llige Testbestellung f�r Kunde1 speichern aus Speisekarte
		for (int i = 0; i < 5; i++) {
			ordering1.addDish(menu.getDish(zufall
					.nextInt(18)));
		}

		
	

		// Kunde2 
		customer2 = new CustomerVO("Genuss", "Gini", "Haribostra�e", 32, Gender.F,
				LocalDate.of(1995, 8, 8));
		ordering2 = new Ordering();
		ordering2.startNewOrder(customer2);

		// zuf�llige Testbestellung f�r Kunde1 speichern aus Speisekarte
		for (int i = 0; i < 3; i++) {
			ordering2.addDish(menu.getDish(zufall
					.nextInt(19)));
		}
		

		// Speisekarte ausgeben
		System.out.println(menu.toString());
		
		//Bestellen
		ordering1.confirmOrder();
		
		ordering1.confirmOrder();
		
		System.out.println(customer1);
		System.out.println(customer2);

		
		System.out.println(ordering1.getCurrentOrder().getShoppingBasket());
		
	} 
}
