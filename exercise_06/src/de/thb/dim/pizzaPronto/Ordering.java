package de.thb.dim.pizzaPronto;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
			if (nextId == 0 || nextId / 100000 < LocalDate.now().getYear()) {
				nextId = (LocalDate.now().getYear() * 100000) + 1;
			} else
				nextId++;
			currentOrder = new OrderVO(nextId, "started", LocalDateTime.now(), customer);
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
		if (currentOrder != null && currentOrder.getState().equals("started"))
			currentOrder.addDish(dish);
		if (currentOrder != null && !currentOrder.getState().equals("started")) {
			System.out.println("Your order is complete, you can not add any dishes.");
		}
	}

	@Override
	public void deleteDish() {
		if (currentOrder == null) {
			System.out.println("There is no order.");
		}
		if (currentOrder != null && currentOrder.getState().equals("started"))
			currentOrder.deleteDish();

		if (currentOrder != null && !currentOrder.getState().equals("started")) {
			System.out.println("Your order is complete, you can not delete any dishes.");
		}
	}

    @Override
	public void confirmOrder() {
		if (currentOrder == null) {
			System.out.println("There is no order.");
		}
		if (currentOrder != null && currentOrder.getState().equals("started")) {
			currentOrder.setState("confirmed");
			startService();
		} else {
			System.out.println("Your order can not be confirmed.");
		}

	}

    public void startService() {
		if (currentOrder == null) {
			System.out.println("There is no order.");
		}

		if (currentOrder != null && currentOrder.getState().equals("started")) {
			System.out.println("Your order can not be processed.");
		}

		if (currentOrder != null && currentOrder.getState().equals("confirmed")) {
			String s = kitchen.startService(currentOrder);
			System.out.println(s);
		}

		if (currentOrder != null && currentOrder.getState().equals("ready")) {
			String s = delivery.startService(currentOrder);
			System.out.println(s);
		}

		if (currentOrder != null && currentOrder.getState().equals("delivered")) {
			currentOrder.setTimestampDeliveredOrder(LocalDateTime.now());
			currentOrder.setState("finished");
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
