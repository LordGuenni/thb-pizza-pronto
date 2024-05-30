package de.thb.dim.pizzaPronto.businessObjects;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

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
	public OrderVO startNewOrder(CustomerVO customer) {
    	if (menu == null)
        	menu = new MenuVO();

    	if (customer != null) {
        	int currentYear = LocalDate.now().getYear();
        	if (nextId == 0 || nextId / 100000 < currentYear) {
            	nextId = (currentYear * 100000) + 1;
        	} else {
            	nextId++;
        	}
        	currentOrder = new OrderVO(nextId, StateOfOrderVO.STARTED, LocalDateTime.now(), customer);
        	currentCustomer = customer;
        	currentCustomer.setOrder(currentOrder);
    }
    return currentOrder;
}


    @Override
	public void addDish(DishVO dish) {
		if (currentOrder == null) {
			System.out.println("There is no order.");
		}
		if (currentOrder != null && currentOrder.getState() == StateOfOrderVO.STARTED)
			currentOrder.addDish(dish);
		if (currentOrder != null && currentOrder.getState() != StateOfOrderVO.STARTED) {
			System.out.println("Your order is complete, you can not add any dishes.");
		}
	}

	
	@Override
	public void deleteDish(DishVO dish) {
    	if (currentOrder == null) {
        	System.out.println("There is no order.");
    	}
		
		else if(currentOrder.getState() == StateOfOrderVO.CONFIRMED) {
        	System.out.println("Your order is complete, you can not delete any dishes.");
    	} 
		
		else {
        	currentOrder.deleteDish(dish);
		}	
	}



    public void confirmOrder() {
    if (currentOrder == null) {
        System.out.println("There is no order.");
    } else {
        currentOrder.setState(StateOfOrderVO.FINISHED);
        System.out.println("Order completed: " + currentOrder);
    }
}


    public void startService() {
		if (currentOrder == null) {
			System.out.println("There is no order.");
		}

		 if (currentOrder != null && currentOrder.getState() == StateOfOrderVO.STARTED) {
			System.out.println("Your order can not be processed.");
		}

		if (currentOrder != null && currentOrder.getState() == StateOfOrderVO.CONFIRMED) {
			String s = kitchen.startService(currentOrder);
			System.out.println(s);
		}

		 if (currentOrder != null && currentOrder.getState() == StateOfOrderVO.READY) {
			String s = delivery.startService(currentOrder);
			System.out.println(s);
		}

		if (currentOrder != null && currentOrder.getState() == StateOfOrderVO.DELIVERED) {
			currentOrder.setTimestampDeliveredOrder(LocalDateTime.now());
			currentOrder.setState(StateOfOrderVO.FINISHED);
			System.out.println("\nOrder completed: " + currentOrder.toString());

		}

	}

    @Override
	public float calculateTotalPrice() {
		float price = 0f;
		if (currentOrder == null) {
			System.out.println("There is no order.");
		}
		if (currentOrder != null)
			price = currentOrder.calculatePriceDishes();
		return price;
	}

	@Override
	public List<DishVO> sortShoppingBasket() {
    	List<DishVO> shoppingBasket = currentOrder.getShoppingBasket();
    	shoppingBasket.sort(null); // Verwendet die natürliche Ordnung von DishVO (Comparable - basierend auf dem Namen des Gerichts)
    	return shoppingBasket;
}


	@Override
	public List<DishVO> sortShoppingBasketByNumber() {
    	List<DishVO> shoppingBasket = currentOrder.getShoppingBasket();
    	shoppingBasket.sort(new Comparator<DishVO>() {
        @Override
        public int compare(DishVO dish1, DishVO dish2) {
            return Integer.compare(dish1.getNumberOfDish(), dish2.getNumberOfDish());
        }
    });
    return shoppingBasket;
	}

	@Override
	public List<DishVO> sortShoppingBasketByPrice() {
    	List<DishVO> shoppingBasket = currentOrder.getShoppingBasket();
    	shoppingBasket.sort((dish1, dish2) -> Float.compare(dish1.getPrice(), dish2.getPrice()));
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
