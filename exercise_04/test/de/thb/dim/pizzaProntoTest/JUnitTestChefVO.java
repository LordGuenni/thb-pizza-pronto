package de.thb.dim.pizzaProntoTest;import static org.junit.jupiter.api.Assertions.assertEquals;import static org.junit.jupiter.api.Assertions.assertFalse;import static org.junit.jupiter.api.Assertions.assertTrue;import static org.junit.jupiter.api.Assertions.fail;import java.awt.Color;import java.lang.reflect.Field;import java.lang.reflect.Method;import java.lang.reflect.Modifier;import org.junit.jupiter.api.BeforeEach;import org.junit.jupiter.api.DisplayName;import org.junit.jupiter.api.Test;import de.thb.dim.pizzaPronto.ChefVO;import de.thb.dim.pizzaPronto.DeliveryManVO;import de.thb.dim.pizzaPronto.EmployeeVO;import de.thb.dim.pizzaPronto.PersonVO;import de.thb.dim.pizzaPronto.PizzaVO;/** * The methods of the class ChefVO are tested. *  * Special assert statements are used for testing <br> *  * @author Gabriele Schmidt * @version 4.1 08.03.2021 */public class JUnitTestChefVO {	private static EmployeeVO chefX, chefY, chefZ;	private static Class<ChefVO> myChefVOClass;	@BeforeEach	public void initEach() {		chefX = new ChefVO("E23", "Nachname", "Vorname");		chefY = new ChefVO(chefX.getPersonnelNo(), chefX.getLastName(), chefX.getFirstName());		chefZ = new ChefVO(chefX.getPersonnelNo(), chefX.getLastName(), chefX.getFirstName());	}	@Test	@DisplayName("Class has 1 instance attributes, others are inherited")	public void test3Attributes() {		myChefVOClass = ChefVO.class;		Field[] attributes = myChefVOClass.getDeclaredFields();		assertEquals(1, attributes.length);	}	@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Attributes are private")	public void testAttributesPrivate() {		myChefVOClass = ChefVO.class;		Field[] attributes = myChefVOClass.getDeclaredFields();		int modifiersAttributes;		for (Field f : attributes) {			modifiersAttributes = f.getModifiers();			assertTrue(Modifier.isPrivate(modifiersAttributes));		}	}	@Test	@DisplayName("All Methods are public")	public void testMethodsPublic() {		myChefVOClass = ChefVO.class;		Method[] methods = myChefVOClass.getDeclaredMethods();		int modifiersMethods;		for (Method m : methods) {			modifiersMethods = m.getModifiers();			assertTrue(Modifier.isPublic(modifiersMethods));		}	}	@Test	@DisplayName("Default constructor initializes with default values")	public void testDefaultConstructor() {		chefX = new ChefVO();		assertEquals(null, chefX.getLastName());		assertEquals(null, chefX.getFirstName());		assertEquals(null, ((ChefVO) chefX).getColorApron());	}	@Test	@DisplayName("Initialization constructor with 3 parameters")	public void testIniConstructor() {		String pNo = "123456";		String lastName = "BBB";		String firstName = "AAA";		chefX = new ChefVO(pNo, lastName, firstName);		assertEquals(lastName, chefX.getLastName());		assertEquals(firstName, chefX.getFirstName());		assertEquals(pNo, chefX.getPersonnelNo());	}	// Java default opertions	@Test	@DisplayName("equals is tested with null")	public void equalsNull() {		// For any non-null reference value x, x.equals(null) should return false.		try {			assertFalse(chefX.equals(null), "For any non-null reference value x, x.equals(null) should return false.");		} catch (NullPointerException e) {			fail("Cannot invoke equals because one attribute is null. Should not throw a NullPointerException");		}	}	@Test	@DisplayName("equals is tested with 2 objects created by default constructor.")	public void equalsDefaultConstructors() {		ChefVO default1, default2;		default1 = new ChefVO();		default2 = new ChefVO();		try {			assertTrue(default1.equals(default2));		} catch (NullPointerException e) {			fail("Cannot invoke equals because one attribute is null. Should not throw a NullPointerException");		}	}	@Test	@DisplayName("equals is tested with 1 object created by initalizing construct and 1 object created by default construct and.")	public void equalsIniAndDefaultConstructors() {		ChefVO ini, default2;		ini = new ChefVO("E23", "Nachname", "Vorname");		default2 = new ChefVO();		assertFalse(ini.equals(default2));	}	@Test	@DisplayName("equals is tested with two equal/similar objects, i.e different adresses and similar attributes. ")	public void equals2EqualObjects() {		assertFalse(chefY == chefX);		assertTrue(chefX.equals(chefY));	}	@Test	@DisplayName("equals is tested with  identical objects. ")	public void equals2IdenticalObjects() {		assertTrue(chefY.equals(chefY));	}	@Test	@DisplayName("equals is tested with three equal objects. Is it reflexive, symmetric and transitive according to the contract ")	public void equals3EqualObjects() {		// It is reflexive: for any non-null reference value x, x.equals(x) should		// return true.		assertTrue(chefY.equals(chefY),				"It is reflexive: for any non-null reference value x, x.equals(x) should return true. ");		// It is symmetric: for any non-null reference values x and y, x.equals(y)		// should return true if and only if y.equals(x) returns true.		assertTrue(chefX.equals(chefY) == chefY.equals(chefX),				"It is symmetric: for any non-null reference values x and y, x.equals(y) should return true if and only if y.equals(x) returns true. ");		// It is transitive: for any non-null reference values x, y, and z, if		// x.equals(y) returns true and y.equals(z) returns true, then x.equals(z)		// should return true.		assertTrue((chefX.equals(chefY) && chefY.equals(chefZ)) ? chefX.equals(chefZ) : false,				"It is transitive: for any non-null reference values x, y, and z, if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) should return true. ");	}	@Test	@DisplayName("equals is implemented, only based on personnel number ")	public void testEqualsPersonnelNumber() {		ChefVO chef = new ChefVO("Bocuse01", "Bocuse", "Bruno");		ChefVO chef2 = new ChefVO("Bocuse01", "Bocuse", "Britta");		assertTrue(chef.equals(chef), "Equals is implemented");		assertTrue(chef.equals(chef2), "Equals is implemented, only based on personnel number");		assertTrue(((PersonVO) chef).equals((PersonVO) chef2),				"Equals is implemented, only based on personnel number of PersonVO");	}	@Test	@DisplayName("equals uses getClass() ")	public void testEqualsGetClass() {		ChefVO chef = new ChefVO("Bocuse01", "Bocuse", "Bruno");		DeliveryManVO deliveryMan = new DeliveryManVO("Bocuse01", "Lacy", "Lutz");		assertFalse(chef.equals(deliveryMan), "Equals uses getClass()");		assertFalse(((PersonVO) chef).equals((PersonVO) deliveryMan), "Equals uses getClass()");	}	@Test	@DisplayName("equals is tested different objects from different classes.")	public void equalsDifferentObjectsDifferentClasses() {		assertFalse(chefZ.equals(this),				chefZ.getClass() + " equals is not correct when using objects from differnent class.");	}	@Test	@DisplayName("Simliar objects provide similar hashcode.")	public void hashCodeTest() {		// Simliar objects provide similar hashcode		assertTrue(chefX.equals(chefY) == (chefX.hashCode() == chefY.hashCode()));	}	@Test	@DisplayName("test toString contains \"Chef\"")	public void toStringTestChef() {		ChefVO chef = new ChefVO();		String actualString = chef.toString();		assertTrue(actualString.contains("Chef"));	}	@Test	@DisplayName("test toString with color of apron null")	public void toStringTestApronNull() {		String pNo = "007";		String lastName = "BBB";		String firstName = "AAA";		ChefVO chef = new ChefVO(pNo, lastName, firstName);		String actualString = chef.toString();		assertTrue(actualString.contains("Chef"));		assertTrue(actualString.contains(pNo));		assertTrue(actualString.contains(lastName));		assertTrue(actualString.contains(firstName));	}	@Test	@DisplayName("test toString: Contains all attributes")	public void toStringTest() {		String pNo = "007";		String lastName = "BBB";		String firstName = "AAA";		Color apron = Color.WHITE;		ChefVO chef = new ChefVO(pNo, lastName, firstName);		chef.setColorApron(apron);		String actualString = chef.toString();		assertTrue(actualString.contains(pNo));		assertTrue(actualString.contains(lastName));		assertTrue(actualString.contains(firstName));		assertTrue(actualString.contains(apron.toString()));	}	// Setter/Getter	@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter/getter lastname")	public void setGetLastname() {		String lastName = "BBB";		chefX = new ChefVO();		chefX.setLastName(lastName);		assertEquals(lastName, chefX.getLastName());	}	@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter/getter firstname")	public void setGetFirstName() {		String firstName = "BBB";		chefX = new ChefVO();		chefX.setFirstName(firstName);		assertEquals(firstName, chefX.getFirstName());	}	@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter/getter color of apron")	public void setGetColorApron() {		Color apron = Color.BLUE;		chefX = new ChefVO();		((ChefVO) chefX).setColorApron(apron);		assertEquals(apron, ((ChefVO) chefX).getColorApron());	}}