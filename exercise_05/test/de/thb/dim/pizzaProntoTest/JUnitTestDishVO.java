package de.thb.dim.pizzaProntoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.thb.dim.pizzaPronto.DessertVO;
import de.thb.dim.pizzaPronto.DishVO;
import de.thb.dim.pizzaPronto.PastaVO;
import de.thb.dim.pizzaPronto.PizzaVO;


/**
 * DishVO, PastaVO, PizzaVO and Dessert are tested.
 * 
 * Special assert statements are used for testing <br>
 * 
 * @author Gabriele Schmidt
 * @version 2.0 04.03.2019
 */
public class JUnitTestDishVO {
	
	@Test
	@DisplayName("DishVO implements ingredientsToString() correctly separated with comma, e.g. \"Schinken, Spinat, Champignon, Ei\" ")
	public void testIngredientsToString() {
		String name =  "Popeye";
		DishVO pizza = new PizzaVO(30, name, new String[] { "Schinken",
				"Spinat", "Champignon", "Ei" }, 8.90f, 1);
		assertTrue(((DishVO)pizza).ingredientsToString().contains("Schinken, Spinat, Champignon, Ei"));
	}
	
	
	@Test
	@DisplayName("DishVO implements ingredientsToString() correctly with an empty String \"\", if ingredients are set null")
	public void testIngredientsToStringNull() {
		String name =  "Popeye";
		DishVO pizza = new PizzaVO(30, name, null, 8.90f, 1);
	//	System.out.println(pizza.ingredientsToString());
		assertEquals("",((DishVO)pizza).ingredientsToString(),"an empty String \"\" ist expected.");
	}
	
	@Test
	@DisplayName("DishVO toString() ruft getNameOfDish() auf.")
	public void testToString() {
		String name =  "Popeye";
		DishVO pizza = new PizzaVO(30, name, new String[] { "Schinken","Spinat", "Champignon", "Ei" }, 8.90f, 1);
		assertTrue(((DishVO)pizza).toString().contains(pizza.getNameOfDish()));
	}
	
	@Test
	@DisplayName("Number of PizzaVO")
	public void testPizzaVONumber() {
		int number = 30;
		PizzaVO pizza = new PizzaVO(number, "Popeye", new String[] { "Schinken",
				"Spinat", "Champignon", "Ei" }, 8.90f, 2);
		assertEquals(number,pizza.getNumber(),"Number of pizza");
	}
	
	@Test
	@DisplayName("Size of PizzaVO is 1")
	public void testPizzaVOSize1() {
		int size = 1;
		PizzaVO pizza = new PizzaVO(30, "Popeye", new String[] { "Schinken",
				"Spinat", "Champignon", "Ei" }, 8.90f, size);
		assertEquals( size,pizza.getSize(),"Size of pizza");
	}
	
	@Test
	@DisplayName("Size of PizzaVO is 2")
	public void testPizzaVOSize2() {
		int size = 2;
		PizzaVO pizza = new PizzaVO(30, "Popeye", new String[] { "Schinken",
				"Spinat", "Champignon", "Ei" }, 8.90f, size);
		assertEquals( size,pizza.getSize(),"Size of pizza");
	}
	
	@Test
	@DisplayName("Number of dish in PizzaVO with size 1")
	public void testPizzaVONumberOfDishSize1() {
		int number = 30;
		int size = 1;
		int numberOfDish = number * 10 + size;
		PizzaVO pizza = new PizzaVO(number, "Popeye", new String[] { "Schinken",
				"Spinat", "Champignon", "Ei" }, 8.90f, size);
		assertEquals( numberOfDish,pizza.getNumberOfDish(),"Number of dish in Pizza");
	}
	
	@Test
	@DisplayName("Number of dish in PizzaVO with size 2")
	public void testPizzaVONumberOfDishSize2() {
		int number = 31;
		int size = 2;
		int numberOfDish = number * 10 + size;
		PizzaVO pizza = new PizzaVO(number, "Popeye", new String[] { "Schinken",
				"Spinat", "Champignon", "Ei" }, 8.90f, size);
		assertEquals( numberOfDish,pizza.getNumberOfDish(),"Number of dish in Pizza");
	}
	
	@Test
	@DisplayName("Name of PizzaVO")
	public void testPizzaVOName() {
		String name =  "Popeye";
		PizzaVO pizza = new PizzaVO(30, name, new String[] { "Schinken",
				"Spinat", "Champignon", "Ei" }, 8.90f, 2);
		assertEquals(name,pizza.getName(),"Name of Pizza");
	}
	
	@Test
	@DisplayName("Name of PizzaVO with \"Pizza\" and Grande")
	public void testPizzaVONameOfDishGrande() {
		String name =  "Popeye";
		PizzaVO pizza = new PizzaVO(30, name, new String[] { "Schinken","Spinat", "Champignon", "Ei" }, 8.90f, 2);
		assertTrue(pizza.getNameOfDish().contains(name),"Dish name of pizza contains correct name");
		assertTrue(pizza.getNameOfDish().contains("Grande"),"Dish name of pizza contains grande");
		assertTrue(pizza.getNameOfDish().contains("Pizza"),"Dish name of pizza contains Pizza");
	}
	
	@Test
	@DisplayName("Name of PizzaVO with \"Pizza\" and Normal")
	public void testPizzaVONameOfDishNormal() {
		String name =  "Popeye";
		PizzaVO pizza = new PizzaVO(30, name, new String[] { "Schinken",
				"Spinat", "Champignon", "Ei" }, 8.90f, 1);
		assertTrue(pizza.getNameOfDish().contains(name),"Dish name of pizza contains correct name");
		assertTrue(pizza.getNameOfDish().contains("Normal"),"Dish name of pizza contains normal");
		assertTrue(pizza.getNameOfDish().contains("Pizza"),"Dish name of pizza contains Pizza");
	}
	
	
	@Test
	@DisplayName("equals of PizzaVO ist ok and equals  calls equals of DishVO")
	public void testPizzaVOEquals() {
		String name =  "Popeye";
		PizzaVO pizza1 = new PizzaVO(30, name, new String[] { "Schinken",
				"Spinat", "Champignon", "Ei" }, 8.90f, 1);
		PizzaVO pizza2 = new PizzaVO(30, name, new String[] { "Schinken",
				"Spinat", "Champignon", "Ei" }, 8.90f, 1);
		
		assertEquals(pizza1,pizza2,"equals ist ok ");
		
		 pizza2 = new PizzaVO(30, "Anders", new String[] { "Schinken",
				"Spinat", "Champignon", "Ei" }, 8.90f, 1);
		assertNotEquals(pizza1,pizza2,"equals  calls equals of DishVO");
		
	}
	
	
	@Test
	@DisplayName("Number of PastaVO")
	public void testPastaVONumber() {
		int number = 12;
		PastaVO pasta =new PastaVO(number, "Bolognese",
				new String[] { "Hackfleischsauce" }, 6.40f, 4);
		assertEquals(number,pasta.getNumber(),"Number of dish in Pasta");
	}
	
	@Test
	@DisplayName("Type of dish in PastaVO is 4")
	public void testPastaVOType4() {
		int type = 4;
		PastaVO pasta =new PastaVO(12, "Bolognese",
				new String[] { "Hackfleischsauce" }, 6.40f, type);
		assertEquals(type,pasta.getTypeOfPasta(), "Type of dish in Pasta");
	}
	
	@Test
	@DisplayName("Type of dish in PastaVO is 5")
	public void testPastaVOType5() {
		int type = 5;
		PastaVO pasta =new PastaVO(12, "Bolognese",
				new String[] { "Hackfleischsauce" }, 6.40f, type);
		assertEquals(type,pasta.getTypeOfPasta(), "Type of dish in Pasta");
	}
	
	@Test
	@DisplayName("Type of dish in PastaVO is 6")
	public void testPastaVOType6() {
		int type = 6;
		PastaVO pasta =new PastaVO(12, "Bolognese",
				new String[] { "Hackfleischsauce" }, 6.40f, type);
		assertEquals(type,pasta.getTypeOfPasta(), "Type of dish in Pasta");
	}
	
	@Test
	@DisplayName("Number of dish in PastaVO")
	public void testPastaVONumberOfDish() {
		int number = 12;
		int type = 4;
		int numberOfDish = type * 100 + number;
		PastaVO pasta =new PastaVO(number, "Bolognese",
				new String[] { "Hackfleischsauce" }, 6.40f, type);
		assertEquals(numberOfDish,pasta.getNumberOfDish(),"Number of dish in Pasta");
	}
	
	@Test
	@DisplayName("Name of PastaVO")
	public void testPastaVOName() {
		String name =  "Bolognese";
		PastaVO pasta =new PastaVO(12, name,
				new String[] { "Hackfleischsauce" }, 6.40f, 4);
		assertEquals(name,pasta.getName(),"Name of Pasta");
	}
	
	@Test
	@DisplayName("Type 4: Dish name of PastaVO contains correct name, contains \"Spaghetti\" and \"Pasta\"")
	public void testPastaVONameOfDish4() {
		String name =  "Bolognese";
		PastaVO pasta =new PastaVO(12, name,
				new String[] { "Hackfleischsauce" }, 6.40f, 4);
		assertTrue(pasta.getNameOfDish().contains(name),"Dish name of pasta contains correct name");
		assertTrue(pasta.getNameOfDish().contains("Spaghetti"),"Dish name of pasta contains Spaghetti");
		assertTrue(pasta.getNameOfDish().contains("Pasta"),"Dish name of pasta contains Pasta");
	}
	
	@Test
	@DisplayName("Type 5: Dish name of PastaVO contains correct name, contains \"Tortellini\" and \"Pasta\"")
	public void testPastaVONameOfDish5() {
		String name =  "Bolognese";
		PastaVO pasta =new PastaVO(12, name,
				new String[] { "Hackfleischsauce" }, 6.40f, 5);
		assertTrue(pasta.getNameOfDish().contains(name),"Dish name of pasta contains correct name");
		assertTrue(pasta.getNameOfDish().contains("Tortellini"),"Dish name of pasta contains Tortellini");
		assertTrue(pasta.getNameOfDish().contains("Pasta"),"Dish name of pasta contains Pasta");
	}
	
	@Test
	@DisplayName("Type 6: Dish name of PastaVO contains correct name, contains \"Gnocchi\" and \"Pasta\"")
	public void testPastaVONameOfDish6() {
		String name =  "Bolognese";
		PastaVO pasta =new PastaVO(12, name,
				new String[] { "Hackfleischsauce" }, 6.40f, 6);
		assertTrue(pasta.getNameOfDish().contains(name),"Dish name of pasta contains correct name");
		assertTrue(pasta.getNameOfDish().contains("Gnocchi"),"Dish name of pasta contains Gnocchi");
		assertTrue(pasta.getNameOfDish().contains("Pasta"),"Dish name of pasta contains Pasta");
	}
	
	@Test
	@DisplayName("Type 7: Dish name of PastaVO contains correct name, does not contain \"Gnocchi\",\"Spaghetti\" or \"Tortellini\" but \"Pasta\"")
	public void testPastaVONameOfDish7() {
		String name =  "Bolognese";
		PastaVO pasta =new PastaVO(12, name,
				new String[] { "Hackfleischsauce" }, 6.40f, 7);
		assertTrue(pasta.getNameOfDish().contains(name),"Dish name of pasta contains correct name");
		assertFalse(pasta.getNameOfDish().contains("Gnocchi"),"Dish name of pasta contains Gnocchi");
		assertFalse(pasta.getNameOfDish().contains("Spaghetti"),"Dish name of pasta contains Spaghetti");
		assertFalse(pasta.getNameOfDish().contains("Tortellini"),"Dish name of pasta contains Tortellini");
		assertTrue(pasta.getNameOfDish().contains("Pasta"),"Dish name of pasta contains Pasta");
	}
	
	@Test
	@DisplayName("Number of DessertVO")
	public void testDessertVONumber() {
		int number = 12;
		DessertVO dessert = new DessertVO(number, "Hausgemachter Obstsalat", 2.30f);
		assertEquals(number,dessert.getNumber(),"Number of dessert");
	}
		
	
	@Test
	@DisplayName("Dish number of DessertVO is identical to its number")
	public void testDessertVONumberOfDish() {
		DessertVO dessert = new DessertVO(21, "Hausgemachter Obstsalat", 2.30f);
		assertEquals(dessert.getNumber(), dessert.getNumberOfDish(),"Dish number of dessert is identical to its number");
	}
	
	@Test
	@DisplayName( "Name of DessertVO")
	public void testDessertVOName() {
		String name =  "Hausgemachter Obstsalat";
		DessertVO dessert = new DessertVO(21, name, 2.30f);
		assertEquals( name,dessert.getName(), "Name of dessert");
	}
	
	@Test
	@DisplayName("Dish name of DessertVO is identical to its name")
	public void testDessertVONameOfDish() {
		String name =  "Hausgemachter Obstsalat";
		DessertVO dessert = new DessertVO(21, name, 2.30f);
		assertTrue(dessert.getNameOfDish().contains(name),"Dish name of dessert is identical to its name");
	}
	
}
