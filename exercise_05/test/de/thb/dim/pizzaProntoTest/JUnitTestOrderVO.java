package de.thb.dim.pizzaProntoTest;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.thb.dim.pizzaPronto.CustomerVO;
import de.thb.dim.pizzaPronto.DessertVO;
import de.thb.dim.pizzaPronto.DishVO;
import de.thb.dim.pizzaPronto.OrderVO;
import de.thb.dim.pizzaPronto.PastaVO;
import de.thb.dim.pizzaPronto.PizzaVO;


/**
 * EmployeeVO and work are tested.
 * 
 * 
 * Special assert statements are used for testing <br>
 * 
 * @author Gabriele Schmidt
 * @version 4.0 22.04.2021
 */
public class JUnitTestOrderVO {

	private static Class<OrderVO> myOrderClass;

	private static OrderVO myOrder;
	
	private static CustomerVO customer;
	
	private static DishVO[] dishes = new DishVO[18];

	
	private static Field shoppingBasket;
	
	private static Field MAX_DISHES;
	
	private static int modifiersMAX_DISHES;
	
	private static Field nextOrderNo;
	
	private static int modifiersNextOrderNo;
	
	@BeforeAll
	public static void initOnce() throws NoSuchFieldException, SecurityException {
		int i = 0;
		
		myOrderClass = OrderVO.class;
		shoppingBasket = myOrderClass.getDeclaredField("shoppingBasket");
		
		nextOrderNo = myOrderClass.getDeclaredField("nextOrderNo");
		modifiersNextOrderNo = nextOrderNo.getModifiers();
		
		MAX_DISHES = myOrderClass.getDeclaredField("MAX_DISHES");
		modifiersMAX_DISHES = MAX_DISHES.getModifiers();
	 

		dishes[i++] = new PizzaVO(30, "Popeye", new String[] { "Schinken", "Spinat", "Champignon", "Ei" }, 7.00f, 1);
		dishes[i++] = new PizzaVO(30, "Popeye", new String[] { "Schinken",
				"Spinat", "Champignon", "Ei" }, 8.90f, 2);
		dishes[i++] = new PizzaVO(31, "Hawaii", new String[] { "Schinken", "Ananas", "Curry" }, 5.80f, 1);
		dishes[i++] = new PizzaVO(31, "Hawaii", new String[] { "Schinken", "Ananas", "Curry" }, 7.40f, 2);

		dishes[i++] = new PizzaVO(32, "Prima", new String[] { "Schinken", "Salami", "Zwiebeln", "Ei" }, 7.00f, 1);
		dishes[i++] = new PizzaVO(32, "Prima", new String[] { "Schinken", "Salami", "Zwiebeln", "Ei" }, 8.90f, 2);

		dishes[i++] = new PastaVO(11, "Napoli", new String[] { "Tomatensauce" }, 5.60f, 4);
		dishes[i++] = new PastaVO(11, "Napoli", new String[] { "Tomatensauce" }, 5.60f, 5);
		dishes[i++] = new PastaVO(11, "Napoli", new String[] { "Tomatensauce" }, 5.60f, 6);
		dishes[i++] = new PastaVO(12, "Bolognese", new String[] { "Hackfleischsauce" }, 6.40f, 4);
		dishes[i++] = new PastaVO(12, "Bolognese", new String[] { "Hackfleischsauce" }, 6.40f, 5);
		dishes[i++] = new PastaVO(12, "Bolognese", new String[] { "Hackfleischsauce" }, 6.40f, 6);
		dishes[i++] = new PastaVO(13, "alla Panna", new String[] { "Schinken", "Sahne" }, 6.40f, 4);
		dishes[i++] = new PastaVO(13, "alla Panna", new String[] { "Schinken", "Sahne" }, 6.40f, 5);
		dishes[i++] = new PastaVO(13, "alla Panna", new String[] { "Schinken", "Sahne" }, 6.40f, 6);

		dishes[i++] = new DessertVO(21, "Hausgemachter Obstsalat", 2.30f);
		dishes[i++] = new DessertVO(22, "Hausgemachte Pannacotta", 2.60f);
		dishes[i++] = new DessertVO(23, "Hausgemachtes Tiramisu", 2.80f);
	}
	

	@BeforeEach
	public void initEach() {
		
		

		// create customer
		customer = new CustomerVO("Genuss", "Gini", "Haribo street", 7,"weiblich", LocalDate.of(1995, 8, 8));

		// create order
		myOrder = new OrderVO(LocalDateTime.now(), customer);

//		customer.setOrder(myOrder);

	}

	// OrderVO
	
	@Test
	@DisplayName("Default constructor sets state \"started\"")
	public void testConstructor() {
			assertEquals("started",myOrder.getState());
	}
	
	@Test
	@DisplayName("Information hiding principle (Geheimnisprinzip): All Attributes are private")
	public void testAttributesPrivate() {

		Field[] attributes = myOrderClass.getDeclaredFields();
		int modifiersAttributes;
		for (Field f : attributes) {
			modifiersAttributes = f.getModifiers();
			assertTrue(Modifier.isPrivate(modifiersAttributes));
		}
	}
	
	@Test
	@DisplayName("Class has 9 attributes")
	public void test9Attributes() {

		Field[] attributes = myOrderClass.getDeclaredFields();
		assertEquals(9, attributes.length);
	}
	

	@Test
	@DisplayName("test MAX_DISHES is static")
	public void testMAX_DISHESStatic() {
		assertTrue( Modifier.isStatic(modifiersMAX_DISHES),"MAX_DISHES is static" );
	}
	
	@Test
	@DisplayName("Test MAX_DISHES is final")
	public void testMAX_DISHESFinal() {
		assertTrue( Modifier.isFinal(modifiersMAX_DISHES),"MAX_DISHES is final" );
	}
	
	@Test
	@DisplayName("Test nextOrderNo is static")
	public void testnextOrderNoPrivate() {
		assertTrue( Modifier.isStatic(modifiersNextOrderNo),"NextOrderNo is private" );
	}
	

	@Test
	@DisplayName("Test shoppingBasket is an array with data type DishVO")
	public void testShoppingBasketDataType() {
		assertTrue(shoppingBasket.getType().toString().equals("class [Lde.thb.dim.pizzaPronto.DishVO;"),
				"shoppingBasket in OrderVO is an array of type DishVO");
	}


	@Test
	@DisplayName("Test orderNo: Number contains the actual year")
	public void testNumberNo() {
		int test = myOrder.getOrderNo();

		String s = Integer.toString(test);
		String year = Integer.toString(LocalDate.now().getYear());
		assertTrue(s.contains(year), "Correct year is part of the identifier");
	}

	@Test
	@DisplayName("addDish: one pizza is added and number of dishes is 1")
	public void addDishOneDish() {

		// add a dish 
		myOrder.addDish(dishes[0]);
		assertEquals(1, myOrder.getNumberOfDishes(), "Number of dishes in OrderVO: 1");
	}


	@Test
	@DisplayName("addDish: too many dishes are added and number of dishes and index are correct.")
	public void addDishTooManyDishes() {

		// add all dishes of pizzen and more
		for (int i = 0; i < OrderVO.getMAX_DISHES(); i++) {
			myOrder.addDish(dishes[i % dishes.length]);
		}
		assertEquals(OrderVO.getMAX_DISHES(), myOrder.getNumberOfDishes(), "Number of dishes in OrderVO: MAX_DISHES");
		// add one more dish 
		myOrder.addDish(dishes[0]);
		assertEquals(

				myOrder.getNumberOfDishes(), myOrder.getIndex(), "Index in OrderVO equals  Number of dishes");
	}

	@Test
	@DisplayName("addDish: all dishes, which are possible, are added and number of dishes and index are correct.")
	public void addDishAllDishes() {

		
		// add all dishes of pizzen and more
				for (int i = 0; i < OrderVO.getMAX_DISHES(); i++) {
					myOrder.addDish(dishes[i % dishes.length]);
				}
		assertEquals(OrderVO.getMAX_DISHES(), myOrder.getNumberOfDishes(), "Number of dishes in OrderVO: MAX_DISHES");
		for (int i = 0; i < myOrder.getNumberOfDishes(); i++) {
			assertEquals(

					myOrder.getDish(i), dishes[i % dishes.length], "Dishes in OrderVO equal the given dishes");
		}
	}

	@Test
	@DisplayName("Calculates total price correctly for MAX_DISHES.")
	public void testOrderVOCalculateTotalPriceMAX_DISHES() {
		float price = 7.00f;
		// add dishes
		for (int i = 0; i < OrderVO.getMAX_DISHES(); i++) {
			myOrder.addDish(
					new PizzaVO(30, "Popeye", new String[] { "Schinken", "Spinat", "Champignon", "Ei" }, price, 1));
		}
		assertTrue((price * OrderVO.getMAX_DISHES()) == myOrder.calculatePriceDishes(),
				" price is calculated correctly ");
	}
	
	@Test
	@DisplayName("Calculates total price correctly for 3 dishes.")
	public void testOrderVOCalculateTotalPrice3Dishes() {
		float price1 = 7.00f;
		float price2 = 7.03f;
		float price3 = 5.05f;
		// add dishes
		
			myOrder.addDish(
					new PizzaVO(30, "Popeye", new String[] { "Schinken", "Spinat", "Champignon", "Ei" }, price1, 1));
			myOrder.addDish(
					new PastaVO(30, "BBB", new String[] { "Sahne", "Ei" }, price2, 6));
			myOrder.addDish(
					new DessertVO(30, "Dessert", price3));
		
		assertTrue((price1 + price2 +price3) == myOrder.calculatePriceDishes(),
				" price is calculated correctly ");
	}



	@Test
	@DisplayName("deleteDish: one pizza is deleted and number of dishes is decreased.")
	public void deleteDishtest() {
		int index, newIndex;
		index = myOrder.getIndex();
		myOrder.addDish(dishes[0]);
		newIndex = myOrder.getIndex();
		assertTrue(newIndex == (index + 1));
		myOrder.deleteDish();
		assertEquals(index, myOrder.getIndex(), "after method call deleteDish() in OrderVO index is decreased");
	}

	@Test
	@DisplayName("deleteDish: one pizza too much is deleted and index remains 0.")
	public void deleteDishOneTooMuch() {
		int index;
		index = myOrder.getIndex();
		assertEquals( 0, index," Index isn't 0");
		myOrder.deleteDish();
		assertEquals(

				index, myOrder.getIndex(), "Delete last dish once too much but index remains 0");
	}

	@Test
	@DisplayName("getDish with wrong index")
	public void getDishWithWrongIndex() {
		assertEquals(myOrder.getDish(10000), null, "Dish at N index" + "isn't correct.");
	}
	
	@Test
	@DisplayName("getDish with no dish at index")
	public void getDishWithNoDishAtIndex() {
		int index;
		index = myOrder.getIndex();
		assertEquals( 0,index, " Index isn't 0");
		assertEquals(null,myOrder.getDish(index),"There schould be no dish at index" + index);
	}

	
	@Test
	@DisplayName("getDish for a certain index is ok")
	public void getDishTest() {
		int index;
		index = myOrder.getIndex();
		assertEquals( 0, index," Index isn't 0");
		myOrder.addDish(dishes[0]);
		index = myOrder.getIndex();
		assertEquals( 1, index," Index isn't 1");
		assertEquals(

				 dishes[0], myOrder.getDish(index - 1), "Dish at index" + (index - 1) + "is correct.");
	}
	
	//Java standards operations
	
	@Test
	@DisplayName("equals is tested with null")
	public void equalsNull() {		
		//For any non-null reference value x, x.equals(null) should return false. 
	try {
		assertFalse(myOrder.equals(null),"For any non-null reference value x, x.equals(null) should return false.");
		} catch (NullPointerException e) {
			fail("Cannot invoke equals because one attribute is null. Should not throw a NullPointerException");
		}
	}

	//Java standard operations
	@Test
	@DisplayName("equals with identical objects")
	public void equalsIdentical() {
		OrderVO otherOrder = myOrder; // Identität
		assertEquals(myOrder, otherOrder, "objects are identical");

	}
	
	@Test
	@DisplayName("equals with different objects")
	public void equalsDifferentObjects() {
		OrderVO otherOrder = new OrderVO(myOrder.getTimestampStartedOrder(),myOrder.getCustomer()); //identical because of orderNo
		// add all 18 dishes
		for (int i = 0; i < OrderVO.getMAX_DISHES(); i++) {
			myOrder.addDish(dishes[i % dishes.length]);
			otherOrder.addDish(dishes[i % dishes.length]);
		}
		assertNotEquals(myOrder, otherOrder);
	}
	
	@Test
	@DisplayName("equals with identical objects and refilled shoppingbasket")
	public void equalsRefilledShoppingBasekt() {
		OrderVO otherOrder = myOrder; //identical because of orderNo
		// add all 18 dishes 
		for (int i = 0; i < OrderVO.getMAX_DISHES(); i++) {
			myOrder.addDish(dishes[i % dishes.length]);
			otherOrder.addDish(dishes[i % dishes.length]);
		}

		assertEquals(myOrder, otherOrder, " OrderVO equals new OrderVO");
	}
	
	@Test
	@DisplayName("equals is tested different objects from different classes.")
	public void equalsDifferentObjectsDifferentClasses() {	
	assertFalse(myOrder.equals(new PizzaVO()),myOrder.getClass() + " equals is not correct when using objects from differnent class." );
}
	
	@Test
	@DisplayName("Simliar objects provide similar hashcode.")
	public void hashCodeTest() {
		OrderVO otherOrder = myOrder;
		//Simliar objects provide similar hashcode
		assertTrue(myOrder.equals(otherOrder) == ( myOrder.hashCode() == otherOrder.hashCode()));
	
	}
	
	
	@Test
	@DisplayName("ToString does not contains empty elements of shopping baskets, i.e. null.")
	public void toStringNoEmptyElements() {

		myOrder.addDish(dishes[0]);
		myOrder.setTimestampDeliveredOrder(LocalDateTime.now());
		String s = myOrder.toString();
		assertFalse(s.contains("null"), "ToString() does not contains empty elements of shopping baskets, i.e. null.");
	}
	
	@Test
	@DisplayName("ToString does not contains empty elements of shopping baskets, although null was added twice.")
	public void toStringNoEmptyElementsBuitIndexIncreased() {

		myOrder.addDish(dishes[0]);
		myOrder.addDish(null);
		myOrder.addDish(null);
		myOrder.setTimestampDeliveredOrder(LocalDateTime.now());
		String s = myOrder.toString();
		assertFalse(s.contains("null"), "ToString() does not contains empty elements of shopping baskets, i.e. null.");
	}
	
	@Test
	@DisplayName("ToString does not contains shopping baskets, because it is null.")
	public void toStringShoppingbasketsNull() {
		String s = myOrder.toString();;
		assertFalse(s.contains("PizzaVO"), "ToString() does not contains shopping baskets,because it is null.");
	}
	
	
	//Setter/Getter
	@Test
	@DisplayName("Information hiding principle (Geheimnisprinzip): Test getter nextOrderNo ist static")
	public void getNextOrderNoTest() {
		OrderVO.getNextOrderNo();
	}
	
	
	@Test
	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter/getter  timestampStartedOrder")
	public void setGetTimestampStartedOrder() {
		LocalDateTime timestamp = LocalDateTime.now();
		myOrder.setTimestampStartedOrder(timestamp);
		assertEquals(timestamp,myOrder.getTimestampStartedOrder());
	}
	
	@Test
	@DisplayName("Information hiding principle (Geheimnisprinzip): Setter/getter  timestampStartedOrder")
	public void setGetTimestampDeliveredOrder() {
		LocalDateTime timestamp = LocalDateTime.now();
		myOrder.setTimestampDeliveredOrder(timestamp);
		assertEquals(timestamp,myOrder.getTimestampDeliveredOrder());
	}
	
	@Test
	@DisplayName("Information hiding principle (Geheimnisprinzip):Setter/getter shoppingBasket")
	public void setGetShopping() {
		myOrder.setShoppingBasket(dishes);
		assertEquals(dishes,myOrder.getShoppingBasket());
	}
	
	@Test
	@DisplayName("Information hiding principle (Geheimnisprinzip):Setter/getter state")
	public void setGetState() {
		myOrder.setState("unknown");
		assertEquals("unknown",myOrder.getState());
	}
	

}
