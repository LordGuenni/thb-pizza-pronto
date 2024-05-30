package de.thb.dim.pizzaProntoTest;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.thb.dim.pizzaPronto.ChefVO;
import de.thb.dim.pizzaPronto.DessertVO;

/**
 * The methods of the class DessertVO are tested.
 * 
 * Special assert statements are used for testing <br>
 * 
 * @author Gabriele Schmidt
 * @version 2.0 04.02.2017
 */

public class JUnitTestDessertVO {

	private static Class<DessertVO> myDessertVOClass;
	private static DessertVO dessertX, dessertY, dessertZ;


	@BeforeEach
	public void initEach() {
		dessertX = new DessertVO(21, "Hausgemachter Obstsalat", 7.30f);
		dessertY =  new DessertVO(dessertX.getNumber(),dessertX.getName(),dessertX.getPrice() );
		dessertZ =  new DessertVO(dessertX.getNumber(),dessertX.getName(),dessertX.getPrice() );
	}
	
	
	@Test
	@DisplayName("Class has 0 instance attributes")
	public void test3Attributes() {

		myDessertVOClass = DessertVO.class;
		Field[] attributes = myDessertVOClass.getDeclaredFields();
		assertEquals(0, attributes.length);
	}
	
	@Test
	@DisplayName("Information hiding principle (Geheimnisprinzip): Attributes are private")
	public void testAttributesPrivate() {
		myDessertVOClass = DessertVO.class;
		Field[] attributes = myDessertVOClass.getDeclaredFields();
		int modifiersAttributes;
		for (Field f : attributes) {
			modifiersAttributes = f.getModifiers();
			assertTrue(Modifier.isPrivate(modifiersAttributes));
		}
	}
	
	@Test
	@DisplayName("All Methods are public")
	public void testMethodsPublic() {
		myDessertVOClass = DessertVO.class;
		Method[] methods = myDessertVOClass.getDeclaredMethods();
		int modifiersMethods;
		for (Method m : methods) {
			modifiersMethods = m.getModifiers();
			assertTrue(Modifier.isPublic(modifiersMethods));
		}
	}
	
	@Test
	@DisplayName("Default constructor initializes with default values")
	public void testDefaultConstructor() {
		DessertVO dessertWithout = new DessertVO();
		assertEquals(null, dessertWithout.getName());
		assertEquals(0.0f, dessertWithout.getPrice());
		assertEquals(null, dessertWithout.getIngredients());
	}
	
	@Test
	@DisplayName("Initialization constructor with 5 parameters")
	public void testIniConstructor() {
		float price = 2.020f;
		String name = "tonno prima";
		String [] ingredis = {"Tomaten","Käse","Basilikum"};
		int no = 23;
		
		DessertVO dessertIni = new DessertVO(no, name, price);
		dessertIni.setIngredients(ingredis);
		assertEquals(no, dessertIni.getNumber());
		assertEquals(name, dessertIni.getName());
		assertEquals(ingredis, dessertIni.getIngredients());
		assertEquals(price, dessertIni.getPrice());
	}
	


	@Test
	@DisplayName("Initializing Constructor: Price should not be negative and is set to 0.")
	public void testIniConstructorPriceNegative() {
		DessertVO dessertIni =  new DessertVO(32, "Pudding", -7.00f);
		assertTrue(
				dessertIni.getPrice() >= 0,dessertIni.getClass() + " not a negative price ");
		
	}
	
	@Test
	@DisplayName("Initializing Constructor: Price is 0.")
	public void testIniConstructorPrice0() {
		DessertVO dessertIni = new DessertVO(32, "Pudding", 0.00f);;
		assertTrue(
				dessertIni.getPrice() >= 0,dessertIni.getClass() + " not a negative price ");
		
	}
	
	//Java default opertions
//	
//	@Test
//	@DisplayName("Clone is tested with equals, i.e. the equals tests should be correct.")
//	public void cloneTest() {
//		DessertVO dessertClone;
//		dessertClone = dessertIni.clone();
//		
//		assertTrue(dessertIni != dessertClone);
//		assertTrue(dessertIni.equals(dessertClone));
//		
//	}

	@Test
	@DisplayName("equals is tested with null")
	public void equalsNull() {	
		//For any non-null reference value x, x.equals(null) should return false. 
		assertFalse( dessertX.equals(null),"For any non-null reference value x, x.equals(null) should return false.");
	}
	
	@Test
	@DisplayName("equals is tested with two equal/similar objects, i.e different adresses and similar attributes. ")
	public void equals2EqualObjects() {
		assertFalse(dessertY == dessertX);
		assertTrue( dessertY.equals(dessertX));
	}

	@Test
	@DisplayName("equals is tested with  identical objects. ")
	public void equals2IdenticalObjects() {	
		assertTrue(dessertY.equals(dessertY));
	}
		
	@Test
	@DisplayName("equals is tested with three equal objects. Is it reflexive, symmetric and transitive according to the contract ")
	public void equals3EqualObjects() {
		
		//It is reflexive: for any non-null reference value x, x.equals(x) should return true. 
		assertTrue(dessertY.equals(dessertY),"It is reflexive: for any non-null reference value x, x.equals(x) should return true. ");
		
		//It is symmetric: for any non-null reference values x and y, x.equals(y) should return true if and only if y.equals(x) returns true.  
		assertTrue(dessertX.equals(dessertY) == dessertY.equals(dessertX),"It is symmetric: for any non-null reference values x and y, x.equals(y) should return true if and only if y.equals(x) returns true. ");
	
		
		//It is transitive: for any non-null reference values x, y, and z, if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) should return true.   
		assertTrue((dessertX.equals(dessertY) && dessertY.equals(dessertZ)) ? dessertX.equals(dessertZ): false,"It is transitive: for any non-null reference values x, y, and z, if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) should return true. ");
	
	}
	
	
	@Test
	@DisplayName("equals is tested different objects.")
	public void equalsDifferentObjects() {			
		dessertZ.setName("Anders");
		assertFalse(dessertZ.equals(dessertX),dessertZ.getClass() + " equals is correct when using diffenrent objects of the same class");
	}
	
	@Test
		@DisplayName("equals is tested different objects from different classes.")
		public void equalsDifferentObjectsDifferentClasses() {	
		
		assertFalse(dessertZ.equals(new ChefVO()),dessertZ.getClass() + " equals is not correct when using objects from differnent class.");
	}
	
	@Test
	@DisplayName("equals is tested with 2 objects created by default constructor.")
	public void equalsDefaultConstructors() {		
		DessertVO default1, default2;
		default1 = new DessertVO();
		default2 = new DessertVO();
		assertTrue(default1.equals(default2));
	}
	
	@Test
	@DisplayName("equals is tested with other object created by initalizing construct and this object created by default construct and.")
	public void equalsIniAndDefaultConstructors() {		
		DessertVO ini, default2;
		ini =  new DessertVO(32, "Pudding", 7.00f);
		default2 = new DessertVO();
		assertFalse(ini.equals(default2));
	}
	
	@Test
	@DisplayName("equals is tested with this name and other other name is null.")
	public void equalsOtherNameNull() {		
		DessertVO ini, other;
		ini =  new DessertVO(32, "Pudding", 7.00f);
		other =  new DessertVO(32, null, 7.00f);
		assertFalse(ini .equals(other));
	}
	
	@Test
	@DisplayName("equals is tested with this name is null and other  name.")
	public void equalsThisNameNull() {		
		DessertVO ini, other;
		ini =  new DessertVO(32, null, 7.00f);
		other =  new DessertVO(32, "Pudding", 7.00f);
		assertFalse(ini .equals(other));
	}
	
	@Test
	@DisplayName("equals is tested with this price and other price is 0.")
	public void equalsOtherPrice0() {		
		DessertVO ini, other;
		ini = new DessertVO(32, "Pudding", 7.00f);
		other = new DessertVO(32, "Pudding", 0.00f);
		assertFalse(ini .equals(other));
	}
	
	@Test
	@DisplayName("equals is tested with this price is 0 and other price.")
	public void equalsThisPrice0() {		
		DessertVO ini, other;
		ini =  new DessertVO(32, "Pudding", 0.00f);
		other =new DessertVO(32, "Pudding", 7.00f);
		assertFalse(ini .equals(other));
	}
	
	@Test
	public void testDessertVOEqualsHashCode() {
		//Simliar objects provide similar hashcode
		assertTrue(dessertX.equals(dessertY) == ( dessertX.hashCode() == dessertY.hashCode()),"Gleiche Objekte liefern den gleichen HashCode");
	
	}
	
	@Test
	@DisplayName("test toString: Contains all attributes")
	public void toStringTest() {
		float price = 13.95f;
		String name = "BBB";
		String [] ingredients = {"Tomaten","Käse","Basilikum","Pfeffer"};
		DessertVO dessert = new DessertVO();
		dessert.setName(name);
		dessert.setIngredients(ingredients);
		dessert.setPrice(price);
		
		
		String actualString = dessert.toString();
		 
	    assertTrue(actualString.contains(name));
	    for(String i : ingredients) {
	    	assertTrue(actualString.contains(i));
	    }
	    assertTrue(actualString.contains(String.valueOf(13)));
	    assertTrue(actualString.contains(String.valueOf(95)));
	    assertTrue(actualString.contains(name));
	   
	}
	
	
	//Setter/Getter
	@Test
	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter/getter name")
	public void setGetName() {
		String name = "BBB";
		DessertVO dessertWithout = new DessertVO();
		dessertWithout.setName(name);
		assertEquals(name, dessertWithout.getName());
	}
	
	@Test
	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter/getter ingredients")
	public void setGetIngredients() {
		String [] ingredients = {"Milch", "Sahne", "Vanille", "Ei"};
		DessertVO dessertWithout = new DessertVO();
		dessertWithout.setIngredients(ingredients);
		assertArrayEquals(ingredients, dessertWithout.getIngredients());
	}
	
	
	
	@Test
	@DisplayName("Test ingredients with setter")
	public void setIngredientsTest() {
		String s [] = {"Milch", "Sahne", "Vanille", "Ei"};
		DessertVO dessertWithout = new DessertVO();
		dessertWithout.setIngredients(s);
		
		assertArrayEquals(s, dessertWithout.getIngredients(),
				dessertWithout.getClass()
				+ " checks the setter of ingredients");
	}
	
	@Test
	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter/getter price")
	public void setGetPrice() {
		float price = 2020.2020f;
		DessertVO dessertWithout = new DessertVO();
		dessertWithout.setPrice(price);
		assertEquals(price, dessertWithout.getPrice());
	}
	
	
	@Test
	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter: Price should not be negative and is set to 0.")
	public void setPriceNegative() {
		DessertVO dessertWithout = new DessertVO();
		dessertWithout.setPrice(-10.5f);
		assertEquals(0f,dessertWithout.getPrice(), dessertWithout.getClass()
				+ " checks the setter of price sets no negative price");
	}
	

	@Test
	@DisplayName("Information hiding principle (Geheimnisprinzip):Setter: Price is 0.")
	public void setPrice0() {
		DessertVO dessertWithout = new DessertVO();
		dessertWithout.setPrice(.0f);
		assertEquals(0f,dessertWithout.getPrice(), dessertWithout.getClass()
				+ " checks the setter of price sets no negative price");
	}

	
}