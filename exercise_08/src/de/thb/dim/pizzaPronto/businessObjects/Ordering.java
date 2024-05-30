package de.thb.dim.pizzaPronto.businessObjects;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import de.thb.dim.pizzaPronto.businessObjects.exceptions.NoCustomerException;
import de.thb.dim.pizzaPronto.businessObjects.exceptions.NoOrderException;

import de.thb.dim.pizzaPronto.controller.IOrdering;
import de.thb.dim.pizzaPronto.valueObjects.CustomerVO;
import de.thb.dim.pizzaPronto.valueObjects.DishVO;
import de.thb.dim.pizzaPronto.valueObjects.MenuVO;
import de.thb.dim.pizzaPronto.valueObjects.OrderVO;
import de.thb.dim.pizzaPronto.valueObjects.StateOfOrderVO;


public class Ordering implements IOrdering{

    private static MenuVO menu;

	private OrderVO currentOrder;
	private CustomerVO currentCustomer;
	private IService kitchen;
	private IService delivery;

	private static int nextId = 0;

	public Ordering() {

		if (menu == null)
			menu = new MenuVO();

		currentOrder = null;
		currentCustomer = null;
		kitchen = new Kitchen();
		delivery = new Delivery();

	}

    @Override
	public OrderVO startNewOrder(CustomerVO customer) throws NullPointerException {
    	if (menu == null)
			menu = new MenuVO();

		Objects.requireNonNull(customer, "Customer must not be null.");

		if (nextId == 0 || nextId / 100000 < LocalDate.now().getYear()) {
			nextId = (LocalDate.now().getYear() * 100000) + 1;
		} else
			nextId++;
		currentOrder = new OrderVO(nextId, StateOfOrderVO.STARTED, LocalDateTime.now(), customer);
		currentCustomer = customer;
		currentCustomer.setOrder(currentOrder);

		return currentOrder;
	}


    @Override
	public void addDish(DishVO dish) throws NoOrderException, IllegalStateException {
		if (currentOrder == null) {
			throw new NoOrderException("There is no order.");
		}
		if (currentOrder.getState() == StateOfOrderVO.STARTED)
			currentOrder.addDish(dish);

		if (currentOrder.getState() != StateOfOrderVO.STARTED) {
			throw new IllegalStateException("Your order is complete, you can not add any dishes.");
		}
	}

	
	@Override
	public void deleteDish(DishVO dish) throws NoOrderException, IllegalStateException {
    	if (currentOrder == null) {
        	throw new NoOrderException("There is no order.");
    	}
		
		else if(currentOrder.getState() != StateOfOrderVO.STARTED) {
        	throw new IllegalStateException("Your order is complete, you can not delete any dishes.");
    	} 
		
		if (currentOrder.getState() == StateOfOrderVO.STARTED)
			currentOrder.deleteDish(dish);
	
	}


	@Override	
    public void confirmOrder() throws NoOrderException, NoCustomerException, IllegalStateException {
		if (currentOrder == null) {
			throw new NoOrderException("There is no order.");
		}
		if (currentOrder.getState() == StateOfOrderVO.STARTED) {
			currentOrder.setState(StateOfOrderVO.CONFIRMED);
			try { 
				startService();
			} catch(IllegalStateException | NoCustomerException | NoOrderException e) {
				System.err.println("Internal error by processing an order: " + e.getMessage());
			}
		} else {
			throw new IllegalStateException("Your order can not be confirmed.");
		}

	}

	private void startService() throws NoOrderException, NoCustomerException, IllegalStateException {
		if (currentOrder == null) {
			throw new NoOrderException("There is no order.");
		}

		if (currentOrder.getState() == StateOfOrderVO.STARTED) {
			throw new IllegalStateException("The order can not be processed.");
		}

		if (currentOrder.getState() == StateOfOrderVO.CONFIRMED) {
			kitchen.startService(currentOrder);

		}

		if (currentOrder.getState() == StateOfOrderVO.READY) {
			delivery.startService(currentOrder);

		}

		if (currentOrder.getState() == StateOfOrderVO.DELIVERED) {
			currentOrder.setTimestampDeliveredOrder(LocalDateTime.now());
			currentOrder.setState(StateOfOrderVO.FINISHED);
			System.out.println("\nOrder completed: " + currentOrder.toString());

		}

	}



    @Override
	public float calculateTotalPrice() throws NoOrderException {
		float price = 0f;
		if (currentOrder == null) {
			throw new NoOrderException("There is no order.");
		}
		price = currentOrder.calculatePriceDishes();
		return price;
	}

	@Override
	public List<DishVO> sortShoppingBasket() throws NoOrderException {
    	if (currentOrder == null) {
			throw new NoOrderException("There is no order.");
		}
		List<DishVO> shoppingBasket = currentOrder.getShoppingBasket();
		Collections.sort(shoppingBasket);
		return shoppingBasket;
	}


	@Override
	public List<DishVO> sortShoppingBasketByNumber() throws NoOrderException{
		if (currentOrder == null) {
			throw new NoOrderException("There is no order.");
		}

    	List<DishVO> shoppingBasket = currentOrder.getShoppingBasket();
		Collections.sort(shoppingBasket,new Comparator<DishVO>() {
			public int compare(DishVO d1, DishVO d2) {
				return Integer.compare(d1.getNumberOfDish(), d2.getNumberOfDish());
			}
		});
		return shoppingBasket;
	
	}

	@Override
	public List<DishVO> sortShoppingBasketByPrice() throws NoOrderException {
		if (currentOrder == null) {
			throw new NoOrderException("There is no order.");
		}
		List<DishVO> shoppingBasket = currentOrder.getShoppingBasket();
		shoppingBasket.sort((DishVO d1, DishVO d2)->Float.compare(d1.getPrice(), d2.getPrice()));
		return shoppingBasket;
	}





    // Getter und Setter

    public OrderVO getCurrentOrder() {
		return currentOrder;
	}

	public void setCurrentOrder(OrderVO currentOrder) {
		this.currentOrder = currentOrder;
	}

	public CustomerVO getCurrentCustomer() {
		return currentCustomer;
	}

	public void setCurrentCustomer(CustomerVO currentCusomer) {
		this.currentCustomer = currentCusomer;
	}

	public static MenuVO getMenu() {
		return menu;
	}

	public IService getKitchen() {
		return kitchen;
	}

	public void setKitchen(IService kitchen) {
		this.kitchen = kitchen;
	}

	public IService getDelivery() {
		return delivery;
	}

	public void setDelivery(IService delivery) {
		this.delivery = delivery;
	}

	public static int getNextId() {
		return nextId;
	}

}
