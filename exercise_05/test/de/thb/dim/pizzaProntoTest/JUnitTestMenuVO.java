package de.thb.dim.pizzaProntoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.thb.dim.pizzaPronto.MenuVO;



/**
 * DishVO, PastaVO, PizzaVO and Dessert are tested.
 * 
 * Special assert statements are used for testing <br>
 * 
 * @author Gabriele Schmidt
 * @version 2.0 02.04.2020
 */
public class JUnitTestMenuVO {
	
	private static Class<MenuVO> myMenuClass;
	private static Field NUMBER_OF_DISHES;
	private static int modifiersNUMBER_OF_DISHES;
	private static Field dishes;
	private static Method initMenu;
	private static int modifiersInitMenu;
	

	
	
	@BeforeAll
	public static void initOnce() throws NoSuchFieldException, SecurityException, NoSuchMethodException{
		
		myMenuClass = MenuVO.class;
		NUMBER_OF_DISHES = myMenuClass.getDeclaredField("NUMBER_OF_DISHES");
		modifiersNUMBER_OF_DISHES = NUMBER_OF_DISHES.getModifiers();
		
		dishes = myMenuClass.getDeclaredField("dishes");
		
		initMenu = myMenuClass.getDeclaredMethod("initMenu");
		modifiersInitMenu = initMenu.getModifiers();
	}
	

	@Test
	@DisplayName("Methode initMenu of menuVO is private.")
	public void testInitMenuIsPrivate() throws NoSuchMethodException {
		 assertTrue(Modifier.isPrivate(modifiersInitMenu), "Methode initMenu of menuVO is private");

	}
	
	@Test
	@DisplayName("NUMBER_OF_DISHES in MenuVO is constant.")
	public void testNUMBER_OF_DISHESIsFinal() {	
		assertTrue(Modifier.isFinal(modifiersNUMBER_OF_DISHES),"NUMBER_OF_DISHES in MenuVO is constant");
	}
	
	@Test
	@DisplayName("NUMBER_OF_DISHES in MenuVO is static.")
	public void testNUMBER_OF_DISHESIsStatic() {	
		assertTrue( Modifier.isStatic(modifiersNUMBER_OF_DISHES),"NUMBER_OF_DISHES in MenuVO is class variable");
	}
	
	@Test
	@DisplayName("NUMBER_OF_DISHES in MenuVO is private.")
	public void testNUMBER_OF_DISHESIsPrivate() {	
		assertTrue( Modifier.isPrivate(modifiersNUMBER_OF_DISHES),"NUMBER_OF_DISHES in MenuVO is private");
	}
	
	
	@Test
	@DisplayName("NUMBER_OF_DISHES in MenuVO is 18.")
	public void testGetNUMBER_OF_DISHES_18() {
		MenuVO menu = new MenuVO();
		assertEquals( 18,menu.getNumberOfDishes(),"Methode getNumberOfDishes() in MenuVO is 18");
	}
	
	@Test
	@DisplayName("Dishes in MenuVO are an array and of type DishVO")
	public void testDishesDataType() {
		assertTrue(dishes.getType().toString().equals("class [Lde.thb.dim.pizzaPronto.DishVO;"),"dishes in MenuVO are an array and of type DishVO");
	}
	
	@Test
	@DisplayName("All dishes initialized")
	public void testgetDish() {
		MenuVO menu = new MenuVO();

		for(int i = 0; i < menu.getNumberOfDishes();i++)
			assertNotNull(menu.getDish(i),"All dishes initialized");
	}
	
}
