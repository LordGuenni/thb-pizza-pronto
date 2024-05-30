package de.thb.dim.pizzaProntoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.thb.dim.pizzaPronto.businessObjects.exceptions.NoCustomerException;
import de.thb.dim.pizzaPronto.businessObjects.exceptions.NoOrderException;
import de.thb.dim.pizzaPronto.valueObjects.exceptions.CustomerNoDateOfBirthException;
import de.thb.dim.pizzaPronto.valueObjects.exceptions.CustomerTooYoungException;


/**
 * Exceptions are tested. <br>
 * 
 * @author Gabriele Schmidt
 * @version 2.0 04.05.2019
 */
public class JUnitTestExceptions {

	@Test
	@DisplayName("test CustomerNoDateOfBirthException is a checked Exception")
	public void isCheckedExceptionCustomerNoDateOfBirthException() {

		Class<CustomerNoDateOfBirthException> noDateOfBirthException = CustomerNoDateOfBirthException.class;
		assertTrue(
				noDateOfBirthException.getSuperclass().toString().equals("class java.lang.Exception"), "CustomerNoDateOfBirthException ist eine checked Exception");
	}

	@Test
	@DisplayName("test CustomerTooYoungException is a checked Exception")
	public void isCheckedExceptionCustomerTooYoungException() {
		Class<CustomerTooYoungException> customerTooYoungException = CustomerTooYoungException.class;
		assertTrue(
				customerTooYoungException.getSuperclass().toString().equals("class java.lang.Exception"), "CustomerTooYoungException ist eine checked Exception");
	}

	@Test
	@DisplayName("test NoOrderException is a checked Exception")
	public void isCheckedExceptionNoOrderException() {

		Class<NoOrderException> noOrderException = NoOrderException.class;
		assertTrue(
				noOrderException.getSuperclass().toString().equals("class java.lang.Exception"), "NoOrderException ist eine checked Exception");
	}

	@Test
	@DisplayName("test NoCustomerException is a checked Exception")
	public void isCheckedExceptionNoCustomerException() {
		Class<NoCustomerException> noCustomerException = NoCustomerException.class;
		assertTrue(
				noCustomerException.getSuperclass().toString().equals("class java.lang.Exception"), "NoCustomerException ist eine checked Exception");
	}

	@Test
	@DisplayName("test super-call in initialition constructor in the CustomerNoDateOfBirthException")
	public void superCallInCustomerNoDateOfBirthException() {
		String text = "Test";

		CustomerNoDateOfBirthException customerNoDateOfBirthException = new CustomerNoDateOfBirthException(text);
		assertEquals(text,
				customerNoDateOfBirthException.getMessage(), "super-call in initialition constructor in CustomerNoDateOfBirthException");
	}

	@Test
	@DisplayName("testsuper-call in initialition constructor in the CustomerTooYoungException")
	public void superCallInCustomerTooYoungException() {
		String text = "Test";

		CustomerTooYoungException customerZuJungException = new CustomerTooYoungException(text);
		assertEquals( text,
				customerZuJungException.getMessage(),"super-call in initialition constructor in CustomerTooYoungException");
	}

	@Test
	@DisplayName("test super-call in initialition constructor in the NoOrderException")
	public void superCallInNoOrderException() {
		String text = "Test";
		NoOrderException noOrderException = new NoOrderException(text);
		assertEquals(text, noOrderException.getMessage(),"super-call in initialition constructor in NoOrderException");
	}

	@Test
	@DisplayName("test super-call in initialition constructor in the NoCustomerException")
	public void superCallInNoCustomerException() {
		String text = "Test";
		NoCustomerException noCustomerException = new NoCustomerException(text);
		assertEquals(text,
				noCustomerException.getMessage(),"super-call in initialition constructor in NoCustomerException");

	}

}
