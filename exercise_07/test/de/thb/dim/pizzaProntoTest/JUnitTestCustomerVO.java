package de.thb.dim.pizzaProntoTest;import static org.junit.jupiter.api.Assertions.assertEquals;import static org.junit.jupiter.api.Assertions.assertFalse;import static org.junit.jupiter.api.Assertions.assertNotEquals;import static org.junit.jupiter.api.Assertions.assertNotNull;import static org.junit.jupiter.api.Assertions.assertTrue;import java.lang.reflect.Field;import java.lang.reflect.Method;import java.lang.reflect.Modifier;import java.time.DateTimeException;import java.time.LocalDate;import java.time.LocalDateTime;import java.time.format.DateTimeFormatter;import org.junit.jupiter.api.Assertions;import org.junit.jupiter.api.BeforeAll;import org.junit.jupiter.api.BeforeEach;import org.junit.jupiter.api.DisplayName;import org.junit.jupiter.api.Test;import de.thb.dim.pizzaPronto.valueObjects.ChefVO;import de.thb.dim.pizzaPronto.valueObjects.CustomerVO;import de.thb.dim.pizzaPronto.valueObjects.Gender;import de.thb.dim.pizzaPronto.valueObjects.OrderVO;import de.thb.dim.pizzaPronto.valueObjects.StateOfOrderVO;/** * The methods of the class CustomerVO are tested. *  * Special assert statements are used for testing <br> *  * @author Gabriele Schmidt * @version 4.0 13.03.2020 */public class JUnitTestCustomerVO {	private static CustomerVO customerDefault, customerIniOnce;	private static CustomerVO customerX, customerY, customerZ;	private static Class<CustomerVO> myCustomerVOClass;	private static Field gender;		@BeforeAll	public static void initOnce() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {				Class<CustomerVO>  myCustomerClass = CustomerVO.class;		Field nextId = myCustomerClass.getDeclaredField("nextId");		nextId.setAccessible(true);		nextId.set(0, 0);				myCustomerVOClass = CustomerVO.class;		customerIniOnce = new CustomerVO("Nachname", "Vorname", "Street", 7,Gender.U, LocalDate.of(1980, 1, 31));		gender = myCustomerVOClass.getDeclaredField("gender");	}	@BeforeEach	public void initEach() {		try {			customerDefault = new CustomerVO();		} catch (NullPointerException e) {			throw new NullPointerException(					"NullPointerException when calling default constructor. Maybe method calculateAge doesn't check for dateOfBirth to be null.");		}				customerX = new CustomerVO("Nachname", "Vorname", LocalDate.of(1990, 5, 31));		customerY = new CustomerVO(customerX.getLastName(), customerX.getFirstName(), customerX.getDateOfBirth());		customerZ = new CustomerVO(customerX.getLastName(), customerX.getFirstName(), customerX.getDateOfBirth());	}	@Test	@DisplayName("Class has 5 attributes, Order is new")	public void test5Attributes() {		myCustomerVOClass = CustomerVO.class;				Field[] attributes = myCustomerVOClass.getDeclaredFields();		assertEquals(5, attributes.length);	}		@Test	@DisplayName("Field gender is of Type Gender")	public void testGenderDataType() {				System.out.println(gender.getType().toString());		assertTrue(gender.getType().toString().equals("class de.thb.dim.pizzaPronto.valueObjects.Gender"),				"gender in CustomerVO is type Gender");	}	@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Attributes are private")	public void testAttributesPrivate() {		myCustomerVOClass = CustomerVO.class;		Field[] attributes = myCustomerVOClass.getDeclaredFields();		int modifiersAttributes;		for (Field f : attributes) {			modifiersAttributes = f.getModifiers();			assertTrue(Modifier.isPrivate(modifiersAttributes));		}	}	@Test	@DisplayName("Default constructor initializes with default values")	public void testDefaultConstructor() {		assertEquals(null, customerDefault.getLastName());		assertEquals(null, customerDefault.getFirstName());		assertEquals(null, customerDefault.getDateOfBirth());		assertEquals(null, customerDefault.getGender());	}	@Test	@DisplayName("Initialization constructor with 3 parameters")	public void testIniConstructor3Param() {		String lastName = "BBB";		String firstName = "AAA";		LocalDate dateOfBirth = LocalDate.of(2000, 3, 15);		CustomerVO customer = new CustomerVO(lastName, firstName, dateOfBirth);		assertEquals(lastName, customer.getLastName());		assertEquals(firstName, customer.getFirstName());		assertEquals(dateOfBirth, customer.getDateOfBirth());	}	@Test	@DisplayName("Initialization constructor with 6 parameters")	public void testIniConstructor6Param() {		String lastName = "BBB";		String firstName = "AAA";		String street = "sss";		int number = 8;		Gender gender = Gender.D;		LocalDate dateOfBirth = LocalDate.of(1988, 6, 18);		CustomerVO customer = new CustomerVO(lastName, firstName, street, number,gender, dateOfBirth);		assertEquals(lastName, customer.getLastName());		assertEquals(firstName, customer.getFirstName());		assertEquals(street, customer.getStreet());		assertTrue(number == customer.getHouseNumber());		assertEquals(gender, customer.getGender());		assertEquals(dateOfBirth, customer.getDateOfBirth());	}		@Test	@DisplayName("calculateAge return data type is short")	public void testShortCalculateAge() {		Method[] methods = myCustomerVOClass.getDeclaredMethods();		boolean returnsShort = false;		for (Method m : methods) {			if (m.toString().equals("public short de.thb.dim.pizzaPronto.valueObjects.CustomerVO.calculateAge()")) {				returnsShort = true;			}		}		assertEquals( true, returnsShort,  customerDefault.getClass() + " calculateAge returns short");	}	@Test	@DisplayName("No attribute for age, since is derived. ")	public void testCustomerVONoAttributeAge() {		Field[] fields = myCustomerVOClass.getDeclaredFields();		boolean attributeAge = false;		for (Field f : fields) {			if (f.toString().equals("private short de.thb.dim.pizzaPronto.CustomerVO.age")) {				attributeAge = true;			}		}		assertEquals( false, attributeAge, customerDefault.getClass() + " field age");	}	@Test	@DisplayName("id starts with 0.")	public void testIdStart0() {		assertEquals( 0, customerIniOnce.getId(), customerIniOnce.getClass() + " has the ID  0");		// IDs are increased correctly, next ten customer	}		@Test	@DisplayName("id is not changed when new customers are created and nextId is increased.")	public void testIdofObjectNoChange() {		int nextId1, nextId2;		nextId1 = CustomerVO.getNextId();		assertEquals( 0, customerIniOnce.getId(), customerIniOnce.getClass() + " has the ID  0");		// IDs are increased correctly, next ten customer		new CustomerVO();		nextId2 = CustomerVO.getNextId();		assertTrue(nextId2 == nextId1 + 1);		// the once given ID can't be changed		assertEquals( 0, customerIniOnce.getId(),customerIniOnce.getClass() + "  has the ID  0");	}		@Test	@DisplayName("id starts with 0 and increases correctly")	public void testIdsIncreasing() {		int nextId1;		CustomerVO customerTest;		nextId1 = CustomerVO.getNextId();		// IDs are increased correctly, next ten customer		for (int i = nextId1; i < nextId1 + 10; i++) {			customerTest = new CustomerVO();			assertEquals( i, customerTest.getId(), customerTest.getClass() + " has the ID  " + i);		}		// the once giben ID can't be changed		assertEquals( 0, customerIniOnce.getId(),customerIniOnce.getClass() + "  has the ID  0");	}		@Test	@DisplayName("dobToString is private")	public void dobToStringPrivate() {		Method[] methods = myCustomerVOClass.getDeclaredMethods();		boolean returnsShort = false;		for (Method m : methods) {			if (m.toString().equals("private java.lang.String de.thb.dim.pizzaPronto.valueObjects.CustomerVO.dobToString()")) {				returnsShort = true;			}		}		assertEquals( true, returnsShort,  customerDefault.getClass() + " calculateAge returns short");	}	@Test	@DisplayName("Test calculateAge: date of birth is null, alter -1")	public void calculateAgeDoBNull() {		// date of birth is null, alter -1		customerDefault.setDateOfBirth(null);		assertEquals( -1, customerDefault.calculateAge(), customerDefault.getClass() + " has the age null");		assertEquals( null,				customerDefault.getDateOfBirth(), customerDefault.getClass() + " Geburtsdaum wurde auf null gesetzt ");	}		@Test	@DisplayName("Test calculateAge:  date of birth for 16 years old.")	public void calculateDoBFor16Years() {		int alter;		LocalDate today = LocalDate.now();				int year = today.getYear();		int month = today.getMonthValue();		int day = today.getDayOfMonth();		// 16 years old		alter = 16;		customerDefault.setDateOfBirth(LocalDate.of(year - alter, month, day));		assertEquals( -1, customerDefault.calculateAge(), customerDefault.getClass() + " has the age " + alter);		assertEquals( null,				customerDefault.getDateOfBirth(),customerDefault.getClass() + " Geburtsdaum wurde auf null gesetzt ");	}		@Test	@DisplayName("Test calculateAge:  date of birth for 18 years old.")	public void calculateDoBFor18Years() {		int alter;		LocalDate today = LocalDate.now();		int year = today.getYear();		int month = today.getMonthValue();		int day = today.getDayOfMonth();		// 18 years old		alter = 18;		customerDefault.setDateOfBirth(LocalDate.of(year - alter, month, day));		assertEquals( 18, customerDefault.calculateAge(), customerDefault.getClass() + " has the age " + alter);	}		@Test	@DisplayName("Test calculateAge:  date of birth for 18 years old minus 1 day.")	public void calculateDoBFor17Years() {		LocalDate today = LocalDate.now();		LocalDate myDate;			myDate = today.minusYears(18);		customerDefault.setDateOfBirth(myDate.plusDays(1));		assertEquals( -1, customerDefault.calculateAge(), customerDefault.getClass() + " has the age of 1 day before the 18th birthday.");	}		@Test	@DisplayName("Test calculateAge:  date of birth for 60 years old.")	public void calculateDoBFor60Years() {		int alter;		LocalDate today = LocalDate.now();		int year = today.getYear();		int month = today.getMonthValue();		int day = today.getDayOfMonth();		// 60 years old		alter = 60;		customerDefault.setDateOfBirth(LocalDate.of(year - alter, month, day));		assertEquals( alter, customerDefault.calculateAge(), customerDefault.getClass() + " has the age " + alter);	}		//Java standard operations		@Test	@DisplayName("equals is tested with null")	public void equalsNull() {				//For any non-null reference value x, x.equals(null) should return false. 		assertFalse(customerX.equals(null),"For any non-null reference value x, x.equals(null) should return false.");	}			@Test	@DisplayName("equals is tested with 2 objects created by default constructor.")	public void equalsDefaultConstructors() {				CustomerVO default1, default2;		default1 = new CustomerVO();		default2 = new CustomerVO();		assertTrue(default1.equals(default2));	}		@Test	@DisplayName("equals is tested with 1 object created by initalizing construct and 1 object created by default construct and.")	public void equalsIniAndDefaultConstructors() {				CustomerVO ini, default2;		ini = new CustomerVO("Nachname", "Vorname","SSS",994, Gender.M, LocalDate.of(1980, 1, 31));		default2 = new CustomerVO();		assertFalse(ini.equals(default2));	}		@Test	@DisplayName("equals is tested with two equal/similar objects, i.e different adresses and similar attributes. ")	public void equals2EqualObjects() {				assertFalse( customerY == customerX);			assertTrue(customerX.equals(customerY));	}		@Test	@DisplayName("equals is tested with  identical objects. ")	public void equals2IdenticalObjects() {			assertTrue(customerY.equals(customerY));	}			@Test	@DisplayName("equals is tested with three equal objects. Is it reflexive, symmetric and transitive according to the contract ")	public void equals3EqualObjects() {				//It is reflexive: for any non-null reference value x, x.equals(x) should return true. 		assertTrue(customerY.equals(customerY),"It is reflexive: for any non-null reference value x, x.equals(x) should return true. ");				//It is symmetric: for any non-null reference values x and y, x.equals(y) should return true if and only if y.equals(x) returns true.  		assertTrue(customerX.equals(customerY) == customerY.equals(customerX),"It is symmetric: for any non-null reference values x and y, x.equals(y) should return true if and only if y.equals(x) returns true. ");					//It is transitive: for any non-null reference values x, y, and z, if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) should return true.   		assertTrue((customerX.equals(customerY) && customerY.equals(customerZ)) ? customerX.equals(customerZ): false,"It is transitive: for any non-null reference values x, y, and z, if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) should return true. ");		}		@Test	@DisplayName("equals is tested with other's lastname is null. ")	public void equalsOtherNoLastname() {		CustomerVO customer1 = new  CustomerVO("Nachname", "Vorname",LocalDate.of(1989, 7, 30));		CustomerVO customer2 = new  CustomerVO(null, "Vorname",LocalDate.of(1989, 7, 30));		assertFalse(customer1.equals(customer2));	}		@Test	@DisplayName("equals is tested with this lastname is null. ")	public void equalsThisNoLastname() {		CustomerVO customer1 = new  CustomerVO(null, "Vorname",LocalDate.of(1989, 7, 30));		CustomerVO customer2 = new  CustomerVO("Nachname", "Vorname",LocalDate.of(1989, 7, 30));		assertFalse(customer1.equals(customer2));	}		@Test	@DisplayName("equals is tested with other's firstname is null. ")	public void equalsOtherNoFirstname() {		CustomerVO customer1 = new  CustomerVO("Nachname", null,LocalDate.of(1989, 7, 30));		CustomerVO customer2 = new  CustomerVO("Nachname", "Vorname",LocalDate.of(1989, 7, 30));		assertFalse(customer1.equals(customer2));	}		@Test	@DisplayName("equals is tested with this lastname of apron is null. ")	public void equalsThisNoFirstname() {		CustomerVO customer1 = new  CustomerVO("Nachname", "Vorname",LocalDate.of(1989, 7, 30));		CustomerVO customer2 = new  CustomerVO("Nachname", null,LocalDate.of(1989, 7, 30));		assertFalse(customer1.equals(customer2));	}		@Test	@DisplayName("equals is tested with other's date of Birth is null. ")	public void equalsOtherNoDob() {		CustomerVO customer1 = new  CustomerVO("Nachname", "Vorname",null);		CustomerVO customer2 = new  CustomerVO("Nachname", "Vorname",LocalDate.of(1989, 7, 30));		assertFalse(customer1.equals(customer2));	}		@Test	@DisplayName("equals is tested with this date of Birth is null. ")	public void equalsThisNoDob() {		CustomerVO customer1 = new  CustomerVO("Nachname", "Vorname",LocalDate.of(1989, 7, 30));		CustomerVO customer2 = new  CustomerVO("Nachname", "null",null);		assertFalse(customer1.equals(customer2));	}		@Test	@DisplayName("equals doesn't test gender. ")	public void equalsOtherNoGender() {		CustomerVO customer1 = new  CustomerVO("Nachname", "Vorname","S",5,Gender.M,LocalDate.of(1989, 7, 30));		CustomerVO customer2 = new  CustomerVO("Nachname", "Vorname","S",5,Gender.F,LocalDate.of(1989, 7, 30) );		assertTrue(customer1.equals(customer2));	}				@Test	@DisplayName("equals is tested different objects.")	public void equalsDifferentObjects() {					customerZ.setLastName("Anders");		assertFalse(customerZ.equals(customerX),customerZ.getClass() + " equals is correct when using diffenrent objects of the same class");	}		@Test		@DisplayName("equals is tested different objects from different classes.")		public void equalsDifferentObjectsDifferentClasses() {					assertFalse(customerZ.equals(new ChefVO()),customerZ.getClass() + " equals is not correct when using objects from differnent class." );	}		@Test	@DisplayName("Simliar objects provide similar hashcode.")	public void hashCodeTest() {				//Simliar objects provide similar hashcode		assertTrue(customerX.equals(customerY) == ( customerX.hashCode() == customerY.hashCode()));		}		@Test	@DisplayName("Test toString: Contains all attributes")	public void toStringTest() {		String lastName = "BBB";		String firstName = "AAA";		String street = "road";		int number = 66;		Gender gender = Gender.U;		int year = 1988;		int month = 6;		int day = 18;		LocalDate dateOfBirth = LocalDate.of(year, month, day);		CustomerVO customer = new CustomerVO(lastName, firstName,street,number, gender, dateOfBirth);				String actualString = customer.toString();		 	    assertTrue(actualString.contains(lastName));	    assertTrue(actualString.contains(firstName));	    assertTrue(actualString.contains(street));	    assertTrue(actualString.contains(String.valueOf(number)));	    assertTrue(actualString.contains(gender.toString()));	    assertTrue(actualString.contains(customer.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd MMM yyyy"))));	    assertTrue(actualString.contains(String.valueOf(customer.getId())));	    assertTrue(actualString.contains(String.valueOf(customer.calculateAge())));	    assertFalse(customer.hasOrder());	}			@Test	@DisplayName("Order is set in Customer and hasOrder returns true")	public void hasOrderTrue() {		OrderVO order = new OrderVO(1,StateOfOrderVO.STARTED,LocalDateTime.now(),customerDefault);		customerDefault.setOrder(order);		assertTrue(customerDefault.hasOrder());	}		@Test	@DisplayName("Order is null in customer and hasOrder returns false")	public void hasOrderFalse() {		OrderVO order = new OrderVO(1,StateOfOrderVO.STARTED,LocalDateTime.now(),customerDefault);		assertFalse(customerDefault.hasOrder());	}			//setter getter		@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): No setter for id and nextId")	public void testNoSettersForIds() {		Method[] methods = myCustomerVOClass.getDeclaredMethods();		for (Method m : methods) {			assertNotEquals( m.toString(), customerDefault.getClass() + " has no setter for id");			assertNotEquals( m.toString(), customerDefault.getClass() + " has no setter for nextId");		}	}		@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Static getter for nextId")	public void testStaticGetterForNextId() {		Method[] methods = myCustomerVOClass.getDeclaredMethods();		boolean staticGetterForNextId = false;		for (Method m : methods) {			if (m.toString().equals("public static int de.thb.dim.pizzaPronto.valueObjects.CustomerVO.getNextId()")) {				staticGetterForNextId = true;			}		}		assertEquals(true, staticGetterForNextId,  customerDefault.getClass() + " has a static getter for nextId" );	}		@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Test getter for id")	public void getIdTest() {		Method[] methods = myCustomerVOClass.getDeclaredMethods();		boolean getterForId = false;		for (Method m : methods) {			if (m.toString().equals("public int de.thb.dim.pizzaPronto.valueObjects.CustomerVO.getId()")) {				getterForId = true;			}		}		assertEquals(true, getterForId,  customerDefault.getClass() + " has a non static getter for id" );	}	@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter/getter lastname")	public void setGetLastname() {		String lastName = "BBB";		customerDefault.setLastName(lastName);		assertEquals(lastName, customerDefault.getLastName());	}	@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter/getter firstname")	public void setGetFirstName() {		String firstName = "BBB";		customerDefault.setFirstName(firstName);		assertEquals(firstName, customerDefault.getFirstName());	}	@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter/getter gender")	public void setGetGender() {		Gender gender = Gender.U;		customerDefault.setGender(gender);		assertEquals(gender, customerDefault.getGender());	}	@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter/getter date of birth")	public void setGetDateOfBirth() {		LocalDate dateOfBirth = LocalDate.of(2000, 12, 24);		customerDefault.setDateOfBirth(dateOfBirth);		assertEquals(dateOfBirth, customerDefault.getDateOfBirth());	}		@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter/getter order")	public void setGetOrder() {		OrderVO order = new OrderVO(1,StateOfOrderVO.STARTED,LocalDateTime.now(),customerDefault);		customerDefault.setOrder(order);		assertEquals(order, customerDefault.getOrder());	}	@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter test: Class LocalDate throws DateTimeException for 30.2.1967")	public void setDateOfBirthWithException() {		Assertions.assertThrows(DateTimeException.class, () -> customerDefault.setDateOfBirth(LocalDate.of(1967, 2, 30)));	}			// set date null	@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter test: Class LocalDate accepts null")	public void setDateOfBirthNull() {		customerDefault.setDateOfBirth(null);	}		@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter test: setDateOfBirth wirh correct date")	public void setDateOfBirthCorrectDate() {		int age = 31;		LocalDate today = LocalDate.now();		int year = today.getYear();		int month = today.getMonthValue();		int day = today.getDayOfMonth();		customerDefault.setDateOfBirth(LocalDate.of(year - age, month, day));		assertEquals(year - age,				customerDefault.getDateOfBirth().getYear(),  customerDefault.getClass() + " has day of birth in the year " + (year - age) );				assertEquals( month,				customerDefault.getDateOfBirth().getMonthValue(), customerDefault.getClass() + " has day of birth in the month " + month);		assertEquals( day,				customerDefault.getDateOfBirth().getDayOfMonth(), customerDefault.getClass() + " has day of birth on the day " + day);	}		@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter test: setDateOfBirth with age too young")	public void setDateOfBirthAgeTooYoung() {		// set age too young		int age = 12;		LocalDate today = LocalDate.now();		int year = today.getYear();		int month = today.getMonthValue();		int day = today.getDayOfMonth();		customerDefault.setDateOfBirth(LocalDate.of(year - age, month, day));		assertEquals( null, customerDefault.getDateOfBirth(), customerDefault.getClass() + " has day of birth in the year " + (year - age)				+ ", is to young and since no object");	}			@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter test: setDateOfBirth with age 60")	public void setDateOfBirthAge60() {		// set age too young		int age = 60;		LocalDate today = LocalDate.now();		int year = today.getYear();		int month = today.getMonthValue();		int day = today.getDayOfMonth();		customerDefault.setDateOfBirth(LocalDate.of(year - age, month, day));		assertEquals(LocalDate.of(year - age, month, day), customerDefault.getDateOfBirth(), customerDefault.getClass() + " has day of birth in the year" + (year - age)				+ ", is to old and since no object");	}	// set age leap year	@Test	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter test: setDateOfBirth with date leap year")	public void setDateOfBirthLeapYear() {		customerDefault.setDateOfBirth(LocalDate.of(1964, 2, 29));		assertNotNull(				customerDefault.getDateOfBirth(), customerDefault.getClass() + " has day of birth on 29th of february 1964 and is since an object");	}}