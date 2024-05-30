package de.thb.dim.pizzaProntoTest;import static org.junit.jupiter.api.Assertions.assertArrayEquals;import static org.junit.jupiter.api.Assertions.assertEquals;import static org.junit.jupiter.api.Assertions.assertFalse;import static org.junit.jupiter.api.Assertions.assertTrue;import static org.junit.jupiter.api.Assertions.fail;import java.lang.reflect.Field;import java.lang.reflect.Method;import java.lang.reflect.Modifier;import org.junit.jupiter.api.BeforeEach;import org.junit.jupiter.api.DisplayName;import org.junit.jupiter.api.Test;import de.thb.dim.pizzaPronto.ChefVO;import de.thb.dim.pizzaPronto.PizzaVO;/** * The methods of the class PizzaVO are tested. *  * Special assert statements are used for testing <br> *  * @author Gabriele Schmidt * @version 2.0 04.02.2017 */public class JUnitTestPizzaVO {	private static Class<PizzaVO> myPizzaVOClass;	private static PizzaVO pizzaIni;	private static PizzaVO pizzaX, pizzaY, pizzaZ;	@BeforeEach	public void initEach() {		pizzaIni = new PizzaVO("Marghrita", new String[] { "Tomaten", "Käse", "Basilikum" }, 8.0f);		pizzaX = new PizzaVO("Tonno", new String[] { "Tomaten", "Käse", "Thunfisch" }, 10.50f);		pizzaY = new PizzaVO(pizzaX.getName(), pizzaX.getIngredients(), pizzaX.getPrice());		pizzaZ = new PizzaVO(pizzaX.getName(), pizzaX.getIngredients(), pizzaX.getPrice());	}	@Test	@DisplayName("Class has 3 instance attributes")	public void test3Attributes() {		myPizzaVOClass = PizzaVO.class;		Field[] attributes = myPizzaVOClass.getDeclaredFields();		assertEquals(3, attributes.length);	}	@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Attributes are private")	public void testAttributesPrivate() {		myPizzaVOClass = PizzaVO.class;		Field[] attributes = myPizzaVOClass.getDeclaredFields();		int modifiersAttributes;		for (Field f : attributes) {			modifiersAttributes = f.getModifiers();			assertTrue(Modifier.isPrivate(modifiersAttributes));		}	}	@Test	@DisplayName("All Methods are public")	public void testMethodsPublic() {		myPizzaVOClass = PizzaVO.class;		Method[] methods = myPizzaVOClass.getDeclaredMethods();		int modifiersMethods;		for (Method m : methods) {			modifiersMethods = m.getModifiers();			assertTrue(Modifier.isPublic(modifiersMethods));		}	}	@Test	@DisplayName("Default constructor initializes with default values")	public void testDefaultConstructor() {		PizzaVO pizzaWithout = new PizzaVO();		assertEquals(null, pizzaWithout.getName());		assertEquals(0.0f, pizzaWithout.getPrice());		assertEquals(null, pizzaWithout.getIngredients());	}	@Test	@DisplayName("Initialization constructor with 3 parameters")	public void testIniConstructor() {		float price = 2.020f;		String name = "tonno prima";		String[] ingredis = { "Tomaten", "Käse", "Basilikum" };		PizzaVO pizzaIni = new PizzaVO(name, ingredis, price);		assertEquals(name, pizzaIni.getName());		assertEquals(ingredis, pizzaIni.getIngredients());		assertEquals(price, pizzaIni.getPrice());	}	@Test	@DisplayName("Initializing Constructor: Price should not be negative and is set to 0.")	public void testIniConstructorPriceNegative() {		PizzaVO pizzaIni = new PizzaVO("Marghrita", new String[] { "Tomaten", "Käse", "Basilikum" }, -8.0f);		assertTrue(pizzaIni.getPrice() >= 0, pizzaIni.getClass() + " not a negative price ");	}	@Test	@DisplayName("Initializing Constructor: Price is 0.")	public void testIniConstructorPrice0() {		PizzaVO pizzaIni = new PizzaVO("Marghrita", new String[] { "Tomaten", "Käse", "Basilikum" }, .0f);		assertTrue(pizzaIni.getPrice() >= 0, pizzaIni.getClass() + " not a negative price ");	}	// Java default opertions	@Test	@DisplayName("Clone is tested with equals, i.e. the equals tests should be correct.")	public void cloneTest() {		PizzaVO pizzaClone;		pizzaClone = pizzaIni.clone();		assertTrue(pizzaIni != pizzaClone);		assertTrue(pizzaIni.equals(pizzaClone));	}	@Test	@DisplayName("equals is tested with null")	public void equalsNull() {		// For any non-null reference value x, x.equals(null) should return false.		assertFalse(pizzaX.equals(null), "For any non-null reference value x, x.equals(null) should return false.");	}	@Test	@DisplayName("equals is tested with two equal/similar objects, i.e different adresses and similar attributes. ")	public void equals2EqualObjects() {		assertFalse(pizzaY == pizzaX);		assertTrue(pizzaY.equals(pizzaX));	}	@Test	@DisplayName("equals is tested with  identical objects. ")	public void equals2IdenticalObjects() {		assertTrue(pizzaY.equals(pizzaY));	}	@Test	@DisplayName("equals is tested with three equal objects. Is it reflexive, symmetric and transitive according to the contract ")	public void equals3EqualObjects() {		// It is reflexive: for any non-null reference value x, x.equals(x) should		// return true.		assertTrue(pizzaY.equals(pizzaY),				"It is reflexive: for any non-null reference value x, x.equals(x) should return true. ");		// It is symmetric: for any non-null reference values x and y, x.equals(y)		// should return true if and only if y.equals(x) returns true.		assertTrue(pizzaX.equals(pizzaY) == pizzaY.equals(pizzaX),				"It is symmetric: for any non-null reference values x and y, x.equals(y) should return true if and only if y.equals(x) returns true. ");		// It is transitive: for any non-null reference values x, y, and z, if		// x.equals(y) returns true and y.equals(z) returns true, then x.equals(z)		// should return true.		assertTrue((pizzaX.equals(pizzaY) && pizzaY.equals(pizzaZ)) ? pizzaX.equals(pizzaZ) : false,				"It is transitive: for any non-null reference values x, y, and z, if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) should return true. ");	}	@Test	@DisplayName("equals is tested different objects.")	public void equalsDifferentObjects() {		pizzaZ.setName("Anders");		assertFalse(pizzaZ.equals(pizzaX),				pizzaZ.getClass() + " equals is correct when using diffenrent objects of the same class");	}	@Test	@DisplayName("equals is tested different objects from different classes.")	public void equalsDifferentObjectsDifferentClasses() {		assertFalse(pizzaZ.equals(new ChefVO()),				pizzaZ.getClass() + " equals is not correct when using objects from differnent class.");	}	@Test	@DisplayName("equals is tested with 2 objects created by default constructor.")	public void equalsDefaultConstructors() {		PizzaVO default1, default2;		default1 = new PizzaVO();		default2 = new PizzaVO();		assertTrue(default1.equals(default2));	}	@Test	@DisplayName("equals is tested with other object created by initalizing construct and this object created by default construct and.")	public void equalsIniAndDefaultConstructors() {		PizzaVO ini, default2;		ini = new PizzaVO("Marghrita", new String[] { "Tomaten", "Käse", "Basilikum" }, 8.0f);		default2 = new PizzaVO();		assertFalse(ini.equals(default2));	}	@Test	@DisplayName("equals is tested with this name and other other name is null.")	public void equalsOtherNameNull() {		PizzaVO ini, other;		ini = new PizzaVO("Marghrita", new String[] { "Tomaten", "Käse", "Basilikum" }, 8.0f);		other = new PizzaVO(null, new String[] { "Tomaten", "Käse", "Basilikum" }, 8.0f);		assertFalse(ini.equals(other));	}	@Test	@DisplayName("equals is tested with this name is null and other  name.")	public void equalsThisNameNull() {		PizzaVO ini, other;		ini = new PizzaVO(null, new String[] { "Tomaten", "Käse", "Basilikum" }, 8.0f);		other = new PizzaVO("Marghrita", new String[] { "Tomaten", "Käse", "Basilikum" }, 8.0f);		try {			assertFalse(ini.equals(other));		} catch (NullPointerException e) {			fail("equals is tested with this name is null and other not. Should not throw a NullPointerException");		}	}	@Test	@DisplayName("equals is tested with this price and other price is 0.")	public void equalsOtherPrice0() {		PizzaVO ini, other;		ini = new PizzaVO("Marghrita", new String[] { "Tomaten", "Käse", "Basilikum" }, 8.0f);		other = new PizzaVO("Marghrita", new String[] { "Tomaten", "Käse", "Basilikum" }, .0f);		assertFalse(ini.equals(other));	}	@Test	@DisplayName("equals is tested with this price is  and other price.")	public void equalsThisPrice0() {		PizzaVO ini, other;		ini = new PizzaVO("Marghrita", new String[] { "Tomaten", "Käse", "Basilikum" }, .0f);		other = new PizzaVO("Marghrita", new String[] { "Tomaten", "Käse", "Basilikum" }, 8.0f);		assertFalse(ini.equals(other));	}	@Test	public void testPizzaVOEqualsHashCode() {		// Simliar objects provide similar hashcode		assertTrue(pizzaX.equals(pizzaY) == (pizzaX.hashCode() == pizzaY.hashCode()),				"Gleiche Objekte liefern den gleichen HashCode");	}	@Test	@DisplayName("Test toString: Contains all attributes")	public void toStringTest() {		float price = 13.95f;		String name = "BBB";		String[] ingredients = { "Tomaten", "Käse", "Basilikum", "Pfeffer" };		PizzaVO pizza = new PizzaVO();		pizza.setName(name);		pizza.setIngredients(ingredients);		pizza.setPrice(price);		String actualString = pizza.toString();		assertTrue(actualString.contains(name),"Doen't contains name: " + name);		for (String i : ingredients) {			assertTrue(actualString.contains(i), "Doen't contains ingredient: " + i);		}		assertTrue(actualString.contains(String.valueOf(price)), "Doen't contains price: " + price);	}		@Test	@DisplayName("Test toString with this ingredients is null")	public void toStringIgredientsNull() {		float price = 13.95f;		String name = "BBB";		String[] ingredients = null;		PizzaVO pizza = new PizzaVO();		pizza.setName(name);		pizza.setIngredients(ingredients);		pizza.setPrice(price);		String actualString = pizza.toString();		System.out.println(actualString);		assertTrue(actualString.contains(name), "Doen't contains name: " + name);		assertTrue(actualString.contains("null"), "Doen't contains null for ingredients");				assertTrue(actualString.contains(String.valueOf(price)), "Doen't contains price: " + price);	}	// Setter/Getter	@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter/getter name")	public void setGetName() {		String name = "BBB";		PizzaVO pizzaWithout = new PizzaVO();		pizzaWithout.setName(name);		assertEquals(name, pizzaWithout.getName());	}	@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter/getter ingredients")	public void setGetIngredients() {		String[] ingredients = { "Tomaten", "Käse", "Basilikum", "Pfeffer" };		PizzaVO pizzaWithout = new PizzaVO();		pizzaWithout.setIngredients(ingredients);		assertArrayEquals(ingredients, pizzaWithout.getIngredients());	}	@Test	@DisplayName("Test ingredients with constructor")	public void testConstructorWithIngredients() {		PizzaVO pizzaIni = new PizzaVO("Marghrita", new String[] { "Tomaten", "Käse", "Basilikum" }, 8.0f);		assertArrayEquals(new String[] { "Tomaten", "Käse", "Basilikum" }, pizzaIni.getIngredients(),				pizzaIni.getClass() + " checks the ingredients");	}	@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Test ingredients with setter")	public void setIngredientsTest() {		String s[] = { "Tomaten", "Käse", "Anchovis" };		PizzaVO pizzaWithout = new PizzaVO();		pizzaWithout.setIngredients(s);		assertArrayEquals(s, pizzaWithout.getIngredients(),				pizzaWithout.getClass() + " checks the setter of ingredients");	}	@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter/getter price")	public void setGetPrice() {		float price = 2020.2020f;		PizzaVO pizzaWithout = new PizzaVO();		pizzaWithout.setPrice(price);		;		assertEquals(price, pizzaWithout.getPrice());	}	@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter: Price should not be negative and is set to 0.")	public void setPriceNegative() {		PizzaVO pizzaWithout = new PizzaVO();		pizzaWithout.setPrice(-10.5f);		assertEquals(0f, pizzaWithout.getPrice(),				pizzaWithout.getClass() + " checks the setter of price sets no negative price");	}	@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter: Price is 0.")	public void setPrice0() {		PizzaVO pizzaWithout = new PizzaVO();		pizzaWithout.setPrice(.0f);		assertEquals(0f, pizzaWithout.getPrice(),				pizzaWithout.getClass() + " checks the setter of price sets no negative price");	}}