package de.thb.dim.pizzaProntoTest;import static org.junit.jupiter.api.Assertions.assertEquals;import static org.junit.jupiter.api.Assertions.assertFalse;import static org.junit.jupiter.api.Assertions.assertTrue;import static org.junit.jupiter.api.Assertions.fail;import java.lang.reflect.Field;import java.lang.reflect.Method;import java.lang.reflect.Modifier;import org.junit.jupiter.api.BeforeEach;import org.junit.jupiter.api.DisplayName;import org.junit.jupiter.api.Test;import de.thb.dim.pizzaPronto.PersonVO;import de.thb.dim.pizzaPronto.PizzaVO;/** * The methods of the class PersonVO are tested. *  * Special assert statements are used for testing <br> *  * @author Gabriele Schmidt * @version 4.0 27.02.2020 */public class JUnitTestPersonVO {	private static PersonVO personX, personY, personZ;	private static Class<PersonVO> myPersonVOClass;	@BeforeEach	public void initEach() {		personX = new PersonVO("Nachname", "Vorname", "Allee", 42);		personY = new PersonVO(personX.getLastName(), personX.getFirstName(), personX.getStreet(),				personX.getHouseNumber());		personZ = new PersonVO(personX.getLastName(), personX.getFirstName(), personX.getStreet(),				personX.getHouseNumber());	}	@Test	@DisplayName("Class has 4 instance attributes")	public void test4Attributes() {		myPersonVOClass = PersonVO.class;		Field[] attributes = myPersonVOClass.getDeclaredFields();		assertEquals(4, attributes.length);	}	@Test	@DisplayName("Information hiding principle and Inheritance: Attributes are protected")	public void testAttributesPrivate() {		myPersonVOClass = PersonVO.class;		Field[] attributes = myPersonVOClass.getDeclaredFields();		int modifiersAttributes;		for (Field f : attributes) {			modifiersAttributes = f.getModifiers();			assertTrue(Modifier.isProtected(modifiersAttributes));		}	}	@Test	@DisplayName("All Methods are public")	public void testMethodsPublic() {		myPersonVOClass = PersonVO.class;		Method[] methods = myPersonVOClass.getDeclaredMethods();		int modifiersMethods;		for (Method m : methods) {			modifiersMethods = m.getModifiers();			assertTrue(Modifier.isPublic(modifiersMethods));		}	}	@Test	@DisplayName("Default constructor initializes with default values")	public void testDefaultConstructor() {		personX = new PersonVO();		assertEquals(null, personX.getLastName());		assertEquals(null, personX.getFirstName());		assertEquals(null, personX.getStreet());		assertEquals(0, personX.getHouseNumber());	}	@Test	@DisplayName("Initialization constructor with 4 parameters")	public void testIniConstructor4() {		String lastName = "BBB";		String firstName = "AAA";		String street = "CCC";		int number = 666;		personX = new PersonVO(lastName, firstName, street, number);		assertEquals(lastName, personX.getLastName());		assertEquals(firstName, personX.getFirstName());		assertEquals(street, personX.getStreet());		assertEquals(number, personX.getHouseNumber());	}	@Test	@DisplayName("Initialization constructor with 2 parameters")	public void testIniConstructor2() {		String lastName = "BBB";		String firstName = "AAA";		personX = new PersonVO(lastName, firstName);		assertEquals(lastName, personX.getLastName());		assertEquals(firstName, personX.getFirstName());		assertEquals(null, personX.getStreet());		assertEquals(0, personX.getHouseNumber());	}	// Java default opertions	@Test	@DisplayName("equals is tested with null")	public void equalsNull() {		// For any non-null reference value x, x.equals(null) should return false.		try {			assertFalse(personX.equals(null),					"For any non-null reference value x, x.equals(null) should return false.");		} catch (NullPointerException e) {			fail("Cannot invoke equals because one attribute is null. Should not throw a NullPointerException");		}	}	@Test	@DisplayName("equals is tested with 2 objects created by default constructor.")	public void equalsDefaultConstructors() {		PersonVO default1, default2;		default1 = new PersonVO();		default2 = new PersonVO();		assertTrue(default1.equals(default2));	}	@Test	@DisplayName("equals is tested with 1 object created by initalizing construct and 1 object created by default construct and.")	public void equalsIniAndDefaultConstructors() {		PersonVO ini, default2;		ini = new PersonVO("Nachname", "Vorname", "SSSS", 7);		default2 = new PersonVO();		assertFalse(ini.equals(default2));	}	@Test	@DisplayName("equals is tested with two equal/similar objects, i.e different adresses and similar attributes. ")	public void equals2EqualObjects() {		assertFalse(personY == personX);		assertTrue(personX.equals(personY));	}	@Test	@DisplayName("equals is tested with  identical objects. ")	public void equals2IdenticalObjects() {		assertTrue(personY.equals(personY));	}	@Test	@DisplayName("equals is tested with  identical objects, but no street and house number. ")	public void equals2IdenticalObjectsNoStreet() {		PersonVO person = new PersonVO("Nachname", "Vorname");		assertTrue(person.equals(person));	}	@Test	@DisplayName("equals is tested with other's lastname is null. ")	public void equalsOtherNoLastname() {		PersonVO person1 = new PersonVO("Nachname", "Vorname");		PersonVO person2 = new PersonVO(null, "Vorname");		assertFalse(person1.equals(person2));	}	@Test	@DisplayName("equals is tested with this lastname  is null. ")	public void equalsThisNoLastname() {		PersonVO person1 = new PersonVO(null, "Vorname");		PersonVO person2 = new PersonVO("Nachname", "Vorname");		assertFalse(person1.equals(person2));	}	@Test	@DisplayName("equals is tested with other's firstname is null. ")	public void equalsOtherNoFirstname() {		PersonVO person1 = new PersonVO("Nachname", "Vorname");		PersonVO person2 = new PersonVO("Nachname", null);		assertFalse(person1.equals(person2));	}	@Test	@DisplayName("equals is tested with this lastname is null. ")	public void equalsThisNoFirstname() {		PersonVO person1 = new PersonVO("Nachname", null);		PersonVO person2 = new PersonVO("Nachname", "Vorname");		assertFalse(person1.equals(person2));	}	@Test	@DisplayName("equals is tested with other's street is null. ")	public void equalsOtherNoStreet() {		PersonVO person1 = new PersonVO("Nachname", "Vorname", "SS", 8);		PersonVO person2 = new PersonVO("Nachname", "Vorname", null, 8);		assertFalse(person1.equals(person2));	}	@Test	@DisplayName("equals is tested with this street is null. ")	public void equalsThisNoStreet() {		PersonVO person1 = new PersonVO("Nachname", "Vorname", null, 8);		PersonVO person2 = new PersonVO("Nachname", "Vorname", "SS", 8);		assertFalse(person1.equals(person2));	}	@Test	@DisplayName("equals is tested with three equal objects. Is it reflexive, symmetric and transitive according to the contract ")	public void equals3EqualObjects() {		// It is reflexive: for any non-null reference value x, x.equals(x) should		// return true.		assertTrue(personY.equals(personY),				"It is reflexive: for any non-null reference value x, x.equals(x) should return true. ");		// It is symmetric: for any non-null reference values x and y, x.equals(y)		// should return true if and only if y.equals(x) returns true.		assertTrue(personX.equals(personY) == personY.equals(personX),				"It is symmetric: for any non-null reference values x and y, x.equals(y) should return true if and only if y.equals(x) returns true. ");		// It is transitive: for any non-null reference values x, y, and z, if		// x.equals(y) returns true and y.equals(z) returns true, then x.equals(z)		// should return true.		assertTrue((personX.equals(personY) && personY.equals(personZ)) ? personX.equals(personZ) : false,				"It is transitive: for any non-null reference values x, y, and z, if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) should return true. ");	}	@Test	@DisplayName("equals is tested different objects.")	public void equalsDifferentObjects() {		personZ.setLastName("Anders");		assertFalse(personZ.equals(personX),				personZ.getClass() + " equals is correct when using diffenrent objects of the same class");	}	@Test	@DisplayName("equals is tested different objects from different classes.")	public void equalsDifferentObjectsDifferentClasses() {		assertFalse(personZ.equals(this),				personZ.getClass() + " equals is not correct when using objects from differnent class.");	}	@Test	@DisplayName("Simliar objects provide similar hashcode.")	public void hashCodeTest() {		// Simliar objects provide similar hashcode		assertTrue(personX.equals(personY) == (personX.hashCode() == personY.hashCode()));	}	@Test	@DisplayName("test toString: Contains all attributes")	public void toStringTest() {		String lastName = "BBB";		String firstName = "AAA";		String street = "CCC";		int number = 1;		PersonVO person = new PersonVO(lastName, firstName, street, number);		String actualString = person.toString();		assertTrue(actualString.contains(lastName));		assertTrue(actualString.contains(firstName));		assertTrue(actualString.contains(street));		assertTrue(actualString.contains(String.valueOf(number)));	}	// Setter/Getter	@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter/getter lastname")	public void setGetLastname() {		String lastName = "BBB";		personX = new PersonVO();		personX.setLastName(lastName);		assertEquals(lastName, personX.getLastName());	}	@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter/getter firstname")	public void setGetFirstName() {		String firstName = "BBB";		personX = new PersonVO();		personX.setFirstName(firstName);		assertEquals(firstName, personX.getFirstName());	}	@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter/getter street")	public void setGetStreet() {		String street = "BBB";		personX = new PersonVO();		personX.setStreet(street);		assertEquals(street, personX.getStreet());	}	@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter/getter house number")	public void setGetNumber() {		int number = 42;		personX = new PersonVO();		personX.setHouseNumber(number);		assertEquals(number, personX.getHouseNumber());	}}