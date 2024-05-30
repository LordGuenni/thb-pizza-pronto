package de.thb.dim.pizzaProntoTest;import static org.junit.jupiter.api.Assertions.assertEquals;import static org.junit.jupiter.api.Assertions.assertTrue;import java.io.File;import java.io.FileNotFoundException;import de.thb.dim.pizzaPronto.businessObjects.io.OrderSerializer;import de.thb.dim.pizzaPronto.businessObjects.io.exceptions.OrderSerializerNoInitException;import de.thb.dim.pizzaPronto.valueObjects.CustomerVO;import de.thb.dim.pizzaPronto.valueObjects.Gender;import de.thb.dim.pizzaPronto.valueObjects.MenuVO;import de.thb.dim.pizzaPronto.valueObjects.OrderVO;import de.thb.dim.pizzaPronto.valueObjects.StateOfOrderVO;import de.thb.dim.pizzaPronto.valueObjects.exceptions.CustomerTooYoungException;import java.io.IOException;import java.time.LocalDate;import java.time.LocalDateTime;import org.junit.jupiter.api.Assertions;import org.junit.jupiter.api.BeforeEach;import org.junit.jupiter.api.DisplayName;import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;import org.junit.jupiter.api.Order;import org.junit.jupiter.api.Test;import org.junit.jupiter.api.TestMethodOrder;/** * IO-KLassen werden getestet. *  * Zum Testen werden spezielle Assert-Befehle und die Reflection-API eingesetzt * <br> *  * @author Gabriele Schmidt * @version 1.0 8.05.2015 */@TestMethodOrder(OrderAnnotation.class)public class JUnitTestIOOrderSerializer {			private static OrderSerializer seri;	private static String path;		@BeforeEach	public void initEach() {		seri = new OrderSerializer();		path = new String("test/");	}	@Test	@DisplayName("test OrderSerializer initInput with file null")	public void testinitInputNullPointerException() throws IOException {		OrderSerializer seri = new OrderSerializer();		Assertions.assertThrows(NullPointerException.class, () -> seri.initInput(null));	} 			@Test	@DisplayName("test OrderSerializer initOutput with file null")	public void testSerializerinitOutputNullPointerException() throws IOException {		OrderSerializer seri = new OrderSerializer();		Assertions.assertThrows(NullPointerException.class, () -> seri.initOutput(null));	} 		@Test	@DisplayName("test OrderSerializer initOutput with wrong file")	public void testSerializerinitInputWithWrongFileNullPointerException() throws IOException {		OrderSerializer seri = new OrderSerializer();			Assertions.assertThrows(FileNotFoundException.class, () -> seri.initInput("Foo"));	} 			@Test	@DisplayName("test OrderSerializer closeOutput with os is null")	public void testSerializerCloseOutputNoFileopen() throws IOException {		OrderSerializer seri = new OrderSerializer();				Exception exception = Assertions.assertThrows(OrderSerializerNoInitException.class, () -> seri.closeOutput());		assertEquals("Output was not initialized.", exception.getMessage());		} 		@Test	@DisplayName("test OrderSerializer closeInput with is is null")	public void testSerializerCloseInputNoFileopen() throws IOException {		OrderSerializer seri = new OrderSerializer();				Exception exception = Assertions.assertThrows(OrderSerializerNoInitException.class, () -> seri.closeInput());		assertEquals("Input was not initialized.", exception.getMessage());		} 			@Test	@DisplayName("test OrderSerializer serialize with is is null")	public void testSerializerSerializeFromFileNull() throws IOException {		CustomerVO customer = null;		OrderVO myOrder;				try {			customer = new CustomerVO("Mustermann", "Max", "Mustergasse", 7, Gender.M, LocalDate.of(1990, 1, 1));		} catch (NullPointerException | CustomerTooYoungException e) {			// TODO Auto-generated catch block			e.printStackTrace();		}		myOrder = new OrderVO(1, StateOfOrderVO.STARTED, LocalDateTime.now().minusHours(9), customer);		OrderSerializer seri = new OrderSerializer();		Exception exception = Assertions.assertThrows(OrderSerializerNoInitException.class, () -> seri.serializeOrder(myOrder));		assertEquals("Output was not initialized.", exception.getMessage());			} 		@Test	@DisplayName("test OrderSerializer deserialize with os is null")	public void testSerializerDeserializeOSNull() throws IOException {		OrderSerializer seri = new OrderSerializer();		Exception exception = Assertions.assertThrows(OrderSerializerNoInitException.class, () -> seri.deserializeOrder());		assertEquals("Input was not initialized.", exception.getMessage());	} 	@Test	@DisplayName("test OrderSerializer and Deserializer are correct and all data are deserialized, i.e. lastname of PersonVO")	public void testSerializerSerializeDeserializeOrderVO() throws NullPointerException, IOException, ClassNotFoundException {		int i;		String file = path+"myOrderFile.ser";		CustomerVO customer = null;		OrderVO myOrder, myOrderDeserialize;		MenuVO menu = new MenuVO();				try {			customer = new CustomerVO("Mustermann", "Max", "Mustergasse", 7, Gender.M, LocalDate.of(1990, 1, 1));		} catch (NullPointerException | CustomerTooYoungException e) {			// TODO Auto-generated catch block			e.printStackTrace();		}		myOrder = new OrderVO(1, StateOfOrderVO.STARTED, LocalDateTime.now().minusHours(9), customer);						seri.initOutput(file);			for (i = 0; i < 3; i++) {			myOrder.addDish(menu.getDish(i));		}		seri.serializeOrder(myOrder);		assertTrue(new File(file).exists(), "File " + file + " was created");				seri.closeOutput();				seri.initInput(file);		myOrderDeserialize = seri.deserializeOrder();		assertEquals(myOrder, myOrderDeserialize, "Deserialized Order equals serialized Order");		assertEquals(myOrder.getCustomer().getLastName(), myOrderDeserialize.getCustomer().getLastName(),				"Customer correctly deserialized");		seri.closeInput();	}	@Test	@Order(1)	@DisplayName("test OrderSerializer serialize in FileCorrect")	public void testSerializerSerializeInFile() throws NullPointerException, IOException {		int i;		String file = path + "FileCorrect.ser";		OrderSerializer seri; 		CustomerVO customer = null;		OrderVO myOrder;		MenuVO menu = new MenuVO();				try {			customer = new CustomerVO("Mustermann1", "Max", "Mustergasse", 7, Gender.M, LocalDate.of(1990, 1, 1));		} catch (NullPointerException | CustomerTooYoungException e) {			// TODO Auto-generated catch block			e.printStackTrace();		}		myOrder = new OrderVO(1, StateOfOrderVO.STARTED, LocalDateTime.now().minusHours(9), customer);					seri = new OrderSerializer();			seri.initOutput(file);			for (i = 0; i < 3; i++) {			myOrder.addDish(menu.getDish(i));		}		seri.serializeOrder(myOrder);		assertTrue(new File(file).exists(), " FileCorrect " + file + " was created");				seri.closeOutput();	}		@Test	@Order(2)	@DisplayName("test OrderSerializer deserialize from FileCorrect")	public void testSerializerDeserializeFromFileCorrect() throws NullPointerException, IOException, ClassNotFoundException {				String file = path + "FileCorrect.ser";		OrderSerializer seri; 		CustomerVO customer = null;		OrderVO myOrder, myOrderDeserialize;				try {			customer = new CustomerVO("Mustermann1", "Max", "Mustergasse", 7, Gender.M, LocalDate.of(1990, 1, 1));		} catch (NullPointerException | CustomerTooYoungException e) {			// TODO Auto-generated catch block			e.printStackTrace();		}		myOrder = new OrderVO(1, StateOfOrderVO.STARTED, LocalDateTime.now().minusHours(9), customer);					seri = new OrderSerializer();				seri.initInput(file);		myOrderDeserialize = seri.deserializeOrder();		assertEquals(myOrder, myOrderDeserialize, "Deserialized Order equals serialized Order");		assertEquals(myOrder.getCustomer().getLastName(), myOrderDeserialize.getCustomer().getLastName(),				"Customer correctly deserialized");		seri.closeInput();		}		@Test	@Order(3)	@DisplayName("test OrderSerializer serialize in FileUnclosed and does not close it")	public void testSerializerSerializeInFileUnclosed() throws NullPointerException, IOException {		int i;		String file = path + "FileUnclosed.ser";		OrderSerializer seri; 		CustomerVO customer = null;		OrderVO myOrder;		MenuVO menu = new MenuVO();				try {			customer = new CustomerVO("Mustermann2", "Max", "Mustergasse", 7, Gender.M, LocalDate.of(1990, 1, 1));		} catch (NullPointerException | CustomerTooYoungException e) {			// TODO Auto-generated catch block			e.printStackTrace();		}		myOrder = new OrderVO(1, StateOfOrderVO.STARTED, LocalDateTime.now().minusHours(9), customer);					seri = new OrderSerializer();			seri.initOutput(file);			for (i = 0; i < 3; i++) {			myOrder.addDish(menu.getDish(i));		}		seri.serializeOrder(myOrder);		assertTrue(new File(file).exists(), " FileCorrect " + file + " was created");					}		@Test	@Order(4)	@DisplayName("test OrderSerializer serialize in FileUnclosed, which is not closed")	public void testSerializerSerializeFromFileUnclosed() throws NullPointerException, IOException, ClassNotFoundException {				String file = path + "FileUnclosed.ser";		OrderSerializer seri; 		CustomerVO customer = null;		OrderVO myOrder, myOrderDeserialize;				try {			customer = new CustomerVO("Mustermann3", "Max", "Mustergasse", 7, Gender.M, LocalDate.of(1990, 1, 1));		} catch (NullPointerException | CustomerTooYoungException e) {			// TODO Auto-generated catch block			e.printStackTrace();		}		myOrder = new OrderVO(1, StateOfOrderVO.STARTED, LocalDateTime.now().minusHours(9), customer);					seri = new OrderSerializer();				seri.initOutput(file);				seri.serializeOrder(myOrder);		}		@Test	@Order(5)	@DisplayName("test OrderSerializer deserialize from FileUnclosed")	public void testSerializerDeserializeFromFileUnclosed() throws NullPointerException, IOException, ClassNotFoundException {				String file = path + "FileUnclosed.ser";		OrderSerializer seri; 		CustomerVO customer = null;		OrderVO myOrder, myOrderDeserialize;				try {			customer = new CustomerVO("Mustermann3", "Max", "Mustergasse", 7, Gender.M, LocalDate.of(1990, 1, 1));		} catch (NullPointerException | CustomerTooYoungException e) {			// TODO Auto-generated catch block			e.printStackTrace();		}		myOrder = new OrderVO(1, StateOfOrderVO.STARTED, LocalDateTime.now().minusHours(9), customer);					seri = new OrderSerializer();				seri.initInput(file);		myOrderDeserialize = seri.deserializeOrder();		assertEquals(myOrder, myOrderDeserialize, "Deserialized Order equals serialized Order");		assertEquals(myOrder.getCustomer().getLastName(), myOrderDeserialize.getCustomer().getLastName(),				"Customer correctly deserialized");		seri.closeInput();		}}