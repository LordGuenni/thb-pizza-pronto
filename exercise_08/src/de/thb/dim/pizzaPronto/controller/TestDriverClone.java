package de.thb.dim.pizzaPronto.controller;

import java.time.LocalDate;

import java.util.Random;

import de.thb.dim.pizzaPronto.businessObjects.Ordering;
import de.thb.dim.pizzaPronto.businessObjects.exceptions.NoCustomerException;
import de.thb.dim.pizzaPronto.businessObjects.exceptions.NoOrderException;
import de.thb.dim.pizzaPronto.valueObjects.MenuVO;
import de.thb.dim.pizzaPronto.valueObjects.PastaVO;
import de.thb.dim.pizzaPronto.valueObjects.PizzaVO;
import de.thb.dim.pizzaPronto.valueObjects.exceptions.CustomerTooYoungException;
import de.thb.dim.pizzaPronto.valueObjects.CustomerVO;
import de.thb.dim.pizzaPronto.valueObjects.Gender;

class TestDriverClone {

	public static void main(String[] args) {

		MenuVO menu = new MenuVO();
		PizzaVO pizza, pizzaClone;
		PastaVO pasta, pastaClone;
		
		// Speisekarte ausgeben
		System.out.println(menu.toString());
		
		pizza = (PizzaVO) menu.getDish(0);
		pizzaClone = (PizzaVO) pizza.clone();
		System.out.println(pizzaClone.toString());
		
		pasta = (PastaVO) menu.getDish(6);
		pastaClone = (PastaVO) pasta.clone();
		System.out.println(pastaClone.toString());

		
		
		}

		
		
}
