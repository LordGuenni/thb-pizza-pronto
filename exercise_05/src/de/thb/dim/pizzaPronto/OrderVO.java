package de.thb.dim.pizzaPronto;

import java.time.LocalDateTime;
import java.util.Objects;

public class OrderVO {

	private final static int MAX_DISHES = 10;
	private static int nextOrderNo = 0;
	private int index;
	private int orderNo;
	private String state;
	private LocalDateTime timestampStartedOrder;
	private LocalDateTime timestampDeliveredOrder;
	private DishVO[] shoppingBasket;
	private CustomerVO customer;
	

	public OrderVO(LocalDateTime timestampStartedOrder, CustomerVO customer) {
		this.setTimestampStartedOrder(timestampStartedOrder);
		this.customer = customer;
		this.shoppingBasket = new DishVO[MAX_DISHES];
		this.index = 0;

		// Jahres Concatination

		int yearNum = (nextOrderNo - (nextOrderNo % 100000)) / 100000;
		int thisYear = LocalDateTime.now().getYear();
		if (nextOrderNo == 0 || yearNum != thisYear) {
			this.orderNo = thisYear * 100000;
			nextOrderNo = orderNo + 1;
		} else {
			orderNo = nextOrderNo++;
		}

		this.setState("started");

	}

	public OrderVO(){
		this(null,null);

	}


	// Getter und Setter

	

    public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public int getIndex() {
		return index;
	}

	
	public int getOrderNo() {
		return orderNo;
	}

	public int getNumberOfDishes() {
		int zähler = 0;
		for (int i = 0; i < shoppingBasket.length; i++) {

			if (shoppingBasket[i] != null) {
				zähler++;

			}

		}

		return zähler;
	}

	public static int getMAX_DISHES() {

		return MAX_DISHES;
	}

	public DishVO getDish(int x) {
		if (x > MAX_DISHES) {
			return null;
		}
		return shoppingBasket[x];
	}

	public CustomerVO getCustomer() {

		return customer;
	}

	public static int getNextOrderNo() {
		return nextOrderNo;
	}

	public void setNextOrderNo(int nextOrderNo) {
		OrderVO.nextOrderNo = nextOrderNo;
	}

	public void setShoppingBasket(DishVO[] dishes) {

		shoppingBasket = dishes;
	}

	public DishVO[] getShoppingBasket() {
		return shoppingBasket;
	}

	public LocalDateTime getTimestampStartedOrder() {
		return timestampStartedOrder;
	}

	public void setTimestampStartedOrder(LocalDateTime timestamp) {
		this.timestampStartedOrder = timestamp;
	}

	public LocalDateTime getTimestampDeliveredOrder() {
		return timestampDeliveredOrder;
	}

	public void setTimestampDeliveredOrder(LocalDateTime timestamp) {
		this.timestampDeliveredOrder = timestamp;
	}

	

	public void deleteDish() {
		if (getNumberOfDishes() >= 1) {
			shoppingBasket[index] = null;
			index--;
		}

	}

	public void addDish(DishVO dish) {

		if (getNumberOfDishes() < MAX_DISHES) {
			shoppingBasket[index] = dish;
			index++;

		}

	}

	public boolean equals(Object orderlocal) {

		if (this == orderlocal) {
			return true;
		}
		if (orderlocal == null) {
			return false;
		}
		if (this.getClass() != orderlocal.getClass()) {
			return false;
		}
		OrderVO order = (OrderVO) orderlocal;

		return Objects.equals(
				Objects.hash(order.shoppingBasket, order.timestampStartedOrder, order.timestampDeliveredOrder),
				hashCode());

	}

	public int hashCode() {
		return Objects.hash(shoppingBasket, timestampStartedOrder, timestampDeliveredOrder);

	}

	public String toString() {

		StringBuilder s = new StringBuilder();

		for (int i = 0; i < shoppingBasket.length; i++) {
			if (this.getShoppingBasket()[i] != null) {
				s.append(getShoppingBasket()[i]);
			}
		}
		s.append("\n");
		s.append("Started: " + this.getTimestampStartedOrder() + "\n");
		s.append("Devlivered: " + this.getTimestampDeliveredOrder() + "\n");
		s.append("OrderNo: " + this.getOrderNo() + "\n");
		s.append("Customer: " + this.getCustomer() + "\n");
		s.append("\n");
		return s.toString();

	}

	public float calculatePriceDishes(){

		float gesamtPreis = 0.0f;
		for (int i = 0; i < shoppingBasket.length; i++) {

			if (shoppingBasket[i] != null) {
				float add = shoppingBasket[i].getPrice();
				gesamtPreis = gesamtPreis + add;
			}

		}
		return gesamtPreis;

		}

		
	}


