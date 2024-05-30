package de.thb.dim.pizzaProntoTest;import static org.junit.jupiter.api.Assertions.assertArrayEquals;import static org.junit.jupiter.api.Assertions.assertEquals;import static org.junit.jupiter.api.Assertions.assertFalse;import static org.junit.jupiter.api.Assertions.assertTrue;import java.lang.reflect.Field;import java.lang.reflect.Method;import java.lang.reflect.Modifier;import org.junit.jupiter.api.BeforeEach;import org.junit.jupiter.api.DisplayName;import org.junit.jupiter.api.Test;import de.thb.dim.pizzaPronto.valueObjects.ChefVO;import de.thb.dim.pizzaPronto.valueObjects.PizzaVO;/** * The methods of the class PizzaVO are tested. *  * Special assert statements are used for testing <br> *  * @author Gabriele Schmidt * @version 2.0 04.02.2017 */public class JUnitTestPizzaVO {	private static Class<PizzaVO> myPizzaVOClass;	private static PizzaVO pizzaX, pizzaY, pizzaZ;	@BeforeEach	public void initEach() {		pizzaX = new PizzaVO(31, "Hawaii", new String[] { "Schinken", "Ananas", "Curry" }, 5.80f, 2);		pizzaY =  new PizzaVO(pizzaX.getNumber(),pizzaX.getName(),pizzaX.getIngredients(),pizzaX.getPrice(),pizzaX.getSize() );		pizzaZ =  new PizzaVO(pizzaX.getNumber(),pizzaX.getName(),pizzaX.getIngredients(),pizzaX.getPrice(),pizzaX.getSize() );	}			@Test	@DisplayName("Class has 2 instance attributes")	public void test2Attributes() {		myPizzaVOClass = PizzaVO.class;		Field[] attributes = myPizzaVOClass.getDeclaredFields();		assertEquals(2, attributes.length);	}		@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Attributes are private")	public void testAttributesPrivate() {		myPizzaVOClass = PizzaVO.class;		Field[] attributes = myPizzaVOClass.getDeclaredFields();		int modifiersAttributes;		for (Field f : attributes) {			modifiersAttributes = f.getModifiers();			assertTrue(Modifier.isPrivate(modifiersAttributes));		}	}		@Test	@DisplayName("All Methods are public")	public void testMethodsPublic() {		myPizzaVOClass = PizzaVO.class;		Method[] methods = myPizzaVOClass.getDeclaredMethods();		int modifiersMethods;		for (Method m : methods) {			modifiersMethods = m.getModifiers();			assertTrue(Modifier.isPublic(modifiersMethods));		}	}		@Test	@DisplayName("Default constructor initializes with default values")	public void testDefaultConstructor() {		PizzaVO pizzaWithout = new PizzaVO();		assertEquals(null, pizzaWithout.getName());		assertEquals(0.0f, pizzaWithout.getPrice());		assertEquals(null, pizzaWithout.getIngredients());	}		@Test	@DisplayName("Initialization constructor with 5 parameters")	public void testIniConstructor() {		float price = 2.020f;		String name = "tonno prima";		String [] ingredis = {"Tomaten","Käse","Basilikum"};		int size = 1;		int no = 23;				PizzaVO pizzaIni = new PizzaVO(no, name, ingredis, price, size);		assertEquals(no, pizzaIni.getNumber());		assertEquals(name, pizzaIni.getName());		assertEquals(ingredis, pizzaIni.getIngredients());		assertEquals(price, pizzaIni.getPrice());		assertEquals(size, pizzaIni.getSize());	}		@Test	@DisplayName("Initializing Constructor: Price should not be negative and is set to 0.")	public void testIniConstructorPriceNegative() {		PizzaVO pizzaIni =  new PizzaVO(32, "Prima", new String[] { "Schinken", "Salami", "Zwiebeln", "Ei" }, -7.00f, 1);		assertTrue(				pizzaIni.getPrice() >= 0,pizzaIni.getClass() + " not a negative price ");			}		@Test	@DisplayName("Initializing Constructor: Price is 0.")	public void testIniConstructorPrice0() {		PizzaVO pizzaIni = new PizzaVO(32, "Prima", new String[] { "Schinken", "Salami", "Zwiebeln", "Ei" }, 0.00f, 1);;		assertTrue(				pizzaIni.getPrice() >= 0,pizzaIni.getClass() + " not a negative price ");			}		@Test	@DisplayName("compareTo is tested < 0 and equals false")	public void compareToLess0() {			PizzaVO pizzaA = new PizzaVO(32, "A", new String[] { "Schinken", "Salami", "Zwiebeln", "Ei" }, 0.00f, 1);		PizzaVO pizzaB =new PizzaVO(32, "B", new String[] { "Schinken", "Salami", "Zwiebeln", "Ei" }, 0.00f, 1);		assertTrue(pizzaA.compareTo(pizzaB) < 0);		assertFalse(pizzaA.equals(pizzaB));	}		@Test	@DisplayName("compareTo is tested == 0 and equals true")	public void compareToEquals0() {			PizzaVO pizzaA = new PizzaVO(32, "A", new String[] { "Schinken", "Salami", "Zwiebeln", "Ei" }, 0.00f, 1);		PizzaVO pizzaB = new PizzaVO(32, "A", new String[] { "Schinken", "Salami", "Zwiebeln", "Ei" }, 0.00f, 1);		assertTrue(pizzaA.compareTo(pizzaB) == 0);		assertTrue(pizzaA.equals(pizzaB));	}		@Test	@DisplayName("compareTo is tested > 0 and equals false")	public void compareToBigger0() {			PizzaVO pizzaA = new PizzaVO(32, "A", new String[] { "Schinken", "Salami", "Zwiebeln", "Ei" }, 0.00f, 1);		PizzaVO pizzaB = new PizzaVO(32, "B", new String[] { "Schinken", "Salami", "Zwiebeln", "Ei" }, 0.00f, 1);		assertTrue(pizzaB.compareTo(pizzaA) > 0);		assertFalse(pizzaA.equals(pizzaB));	}		//Java default opertions//	//	@Test//	@DisplayName("Clone is tested with equals, i.e. the equals tests should be correct.")//	public void cloneTest() {//		PizzaVO pizzaClone;//		pizzaClone = pizzaIni.clone();//		//		assertTrue(pizzaIni != pizzaClone);//		assertTrue(pizzaIni.equals(pizzaClone));//		//	}	@Test	@DisplayName("equals is tested with null")	public void equalsNull() {			//For any non-null reference value x, x.equals(null) should return false. 		assertFalse( pizzaX.equals(null),"For any non-null reference value x, x.equals(null) should return false.");	}		@Test	@DisplayName("equals is tested with two equal/similar objects, i.e different adresses and similar attributes. ")	public void equals2EqualObjects() {		assertFalse(pizzaY == pizzaX);		assertTrue( pizzaY.equals(pizzaX));	}	@Test	@DisplayName("equals is tested with  identical objects. ")	public void equals2IdenticalObjects() {			assertTrue(pizzaY.equals(pizzaY));	}			@Test	@DisplayName("equals is tested with three equal objects. Is it reflexive, symmetric and transitive according to the contract ")	public void equals3EqualObjects() {				//It is reflexive: for any non-null reference value x, x.equals(x) should return true. 		assertTrue(pizzaY.equals(pizzaY),"It is reflexive: for any non-null reference value x, x.equals(x) should return true. ");				//It is symmetric: for any non-null reference values x and y, x.equals(y) should return true if and only if y.equals(x) returns true.  		assertTrue(pizzaX.equals(pizzaY) == pizzaY.equals(pizzaX),"It is symmetric: for any non-null reference values x and y, x.equals(y) should return true if and only if y.equals(x) returns true. ");					//It is transitive: for any non-null reference values x, y, and z, if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) should return true.   		assertTrue((pizzaX.equals(pizzaY) && pizzaY.equals(pizzaZ)) ? pizzaX.equals(pizzaZ): false,"It is transitive: for any non-null reference values x, y, and z, if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) should return true. ");		}			@Test	@DisplayName("equals is tested different objects.")	public void equalsDifferentObjects() {					pizzaZ.setName("Anders");		assertFalse(pizzaZ.equals(pizzaX),pizzaZ.getClass() + " equals is correct when using diffenrent objects of the same class");	}		@Test		@DisplayName("equals is tested different objects from different classes.")		public void equalsDifferentObjectsDifferentClasses() {					assertFalse(pizzaZ.equals(new ChefVO()),pizzaZ.getClass() + " equals is not correct when using objects from differnent class.");	}		@Test	@DisplayName("equals is tested with 2 objects created by default constructor.")	public void equalsDefaultConstructors() {				PizzaVO default1, default2;		default1 = new PizzaVO();		default2 = new PizzaVO();		assertTrue(default1.equals(default2));	}		@Test	@DisplayName("equals is tested with other object created by initalizing construct and this object created by default construct and.")	public void equalsIniAndDefaultConstructors() {				PizzaVO ini, default2;		ini =  new PizzaVO(32, "Prima", new String[] { "Schinken", "Salami", "Zwiebeln", "Ei" }, 7.00f, 1);;		default2 = new PizzaVO();		assertFalse(ini.equals(default2));	}		@Test	@DisplayName("equals is tested with this name and other other name is null.")	public void equalsOtherNameNull() {				PizzaVO ini, other;		ini =  new PizzaVO(32, "Prima", new String[] { "Schinken", "Salami", "Zwiebeln", "Ei" }, 7.00f, 1);		other =  new PizzaVO(32, null, new String[] { "Schinken", "Salami", "Zwiebeln", "Ei" }, 7.00f, 1);		assertFalse(ini .equals(other));	}		@Test	@DisplayName("equals is tested with this name is null and other  name.")	public void equalsThisNameNull() {				PizzaVO ini, other;		ini =  new PizzaVO(32, null, new String[] { "Schinken", "Salami", "Zwiebeln", "Ei" }, 7.00f, 1);		other =  new PizzaVO(32, "Prima", new String[] { "Schinken", "Salami", "Zwiebeln", "Ei" }, 7.00f, 1);		assertFalse(ini .equals(other));	}		@Test	@DisplayName("equals is tested with this price and other price is 0.")	public void equalsOtherPrice0() {				PizzaVO ini, other;		ini =  new PizzaVO(32, "Prima", new String[] { "Schinken", "Salami", "Zwiebeln", "Ei" }, 7.00f, 1);		other = new PizzaVO(32, "Prima", new String[] { "Schinken", "Salami", "Zwiebeln", "Ei" }, 0.00f, 1);		assertFalse(ini .equals(other));	}		@Test	@DisplayName("equals is tested with this price is 0 and other price.")	public void equalsThisPrice0() {				PizzaVO ini, other;		ini =  new PizzaVO(32, "Prima", new String[] { "Schinken", "Salami", "Zwiebeln", "Ei" }, 0.00f, 1);		other = new PizzaVO(32, "Prima", new String[] { "Schinken", "Salami", "Zwiebeln", "Ei" }, 7.00f, 1);		assertFalse(ini .equals(other));	}		@Test	public void testPizzaVOEqualsHashCode() {		//Simliar objects provide similar hashcode		assertTrue(pizzaX.equals(pizzaY) == ( pizzaX.hashCode() == pizzaY.hashCode()),"Gleiche Objekte liefern den gleichen HashCode");		}		@Test	@DisplayName("Clone is tested with equals, i.e. the equals tests should be correct.")	public void cloneSimple() {		PizzaVO pizzaClone;		PizzaVO pizzaIni = new PizzaVO(32, "Prima", new String[] { "Schinken", "Salami", "Zwiebeln", "Ei" }, 0.00f, 1);		pizzaClone = (PizzaVO) pizzaIni.clone();				assertTrue(pizzaIni != pizzaClone);		assertTrue(pizzaIni.equals(pizzaClone));			}		@Test	@DisplayName("clone is tested with 2 objects, equals should be correct.")	public void cloneDetailed() {		pizzaX = new PizzaVO(32, "Prima", new String[] { "Schinken", "Salami", "Zwiebeln", "Ei" }, 0.00f, 1);		pizzaY = (PizzaVO) pizzaX.clone();				//For any non-null reference value x, x.equals(null) should return false. 		assertFalse( pizzaX.equals(null),"For any non-null reference value x, x.equals(null) should return false.");						//Two Objects		assertTrue( pizzaY != pizzaX);				//Equals		assertTrue( pizzaY.equals(pizzaX));				assertTrue( pizzaY.equals(pizzaX));			}		@Test		@DisplayName("clone is tested with 2 objects. Is should be a deep copy")	public void cloneDeepCopy() {		pizzaX =  new PizzaVO(12, "Bolognese",new String[] { "Hackfleischsauce" }, 6.40f, 4);		pizzaY = (PizzaVO) pizzaX.clone();				assertEquals( pizzaY.getName(),pizzaX.getName());		assertEquals( pizzaY.getNameOfDish(),pizzaX.getNameOfDish());		assertEquals( pizzaY.getNumber(),pizzaX.getNumber());		assertEquals( pizzaY.getNumberOfDish(),pizzaX.getNumberOfDish());		assertEquals( pizzaY.getPrice(),pizzaX.getPrice());		assertEquals(((PizzaVO)pizzaY).getSize(),((PizzaVO)pizzaY).getSize());		assertArrayEquals( pizzaY.getIngredients(),pizzaX.getIngredients());				}		@Test	@DisplayName("test toString: Contains all attributes")	public void toStringTest() {		float price = 13.95f;		String name = "BBB";		String [] ingredients = {"Tomaten","Käse","Basilikum","Pfeffer"};		PizzaVO pizza = new PizzaVO();		pizza.setName(name);		pizza.setIngredients(ingredients);		pizza.setPrice(price);		pizza.setSize(1);				String actualString = pizza.toString();		 	    assertTrue(actualString.contains(name));	    for(String i : ingredients) {	    	assertTrue(actualString.contains(i));	    }	    assertTrue(actualString.contains(String.valueOf(13)));	    assertTrue(actualString.contains(String.valueOf(95)));	    assertTrue(actualString.contains("Pizza"));	    assertTrue(actualString.contains(name));	    assertTrue(actualString.contains("Normal"));	}			//Setter/Getter	@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter/getter name")	public void setGetName() {		String name = "BBB";		PizzaVO pizzaWithout = new PizzaVO();		pizzaWithout.setName(name);		assertEquals(name, pizzaWithout.getName());	}		@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter/getter ingredients")	public void setGetIngredients() {		String [] ingredients = {"Tomaten","Käse","Basilikum","Pfeffer"};		PizzaVO pizzaWithout = new PizzaVO();		pizzaWithout.setIngredients(ingredients);		assertArrayEquals(ingredients, pizzaWithout.getIngredients());	}				@Test	@DisplayName("Test ingredients with constructor")	public void testConstructorWithIngredients() {		PizzaVO pizzaIni =  new PizzaVO(32, "Prima", new String[] { "Schinken", "Salami", "Zwiebeln", "Ei" }, 7.00f, 1);			assertArrayEquals( new String [] { "Schinken", "Salami", "Zwiebeln", "Ei" },pizzaIni.getIngredients(),				pizzaIni.getClass()				+ " checks the ingredients");	}		@Test	@DisplayName("Test ingredients with setter")	public void setIngredientsTest() {		String s [] = {"Tomaten","Käse","Anchovis"};		PizzaVO pizzaWithout = new PizzaVO();		pizzaWithout.setIngredients(s);				assertArrayEquals(s, pizzaWithout.getIngredients(),				pizzaWithout.getClass()				+ " checks the setter of ingredients");	}		@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter/getter price")	public void setGetPrice() {		float price = 2020.2020f;		PizzaVO pizzaWithout = new PizzaVO();		pizzaWithout.setPrice(price);		assertEquals(price, pizzaWithout.getPrice());	}			@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter: Price should not be negative and is set to 0.")	public void setPriceNegative() {		PizzaVO pizzaWithout = new PizzaVO();		pizzaWithout.setPrice(-10.5f);		assertEquals(0f,pizzaWithout.getPrice(), pizzaWithout.getClass()				+ " checks the setter of price sets no negative price");	}		@Test	@DisplayName("Information hiding principle (Geheimnisprinzip):Setter: Price is 0.")	public void setPrice0() {		PizzaVO pizzaWithout = new PizzaVO();		pizzaWithout.setPrice(.0f);		assertEquals(0f,pizzaWithout.getPrice(), pizzaWithout.getClass()				+ " checks the setter of price sets no negative price");	}}