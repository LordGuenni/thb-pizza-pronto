package de.thb.dim.pizzaPronto;

import java.time.LocalDateTime;
import java.util.Arrays;

public class OrderVO {

	private final static int MAX_DISHES = 10;
	// private static int nextOrderNo = 0;
	private int index;
	private int orderNo;
	private String state;
	private LocalDateTime timestampStartedOrder;
	private LocalDateTime timestampDeliveredOrder;
	private DishVO[] shoppingBasket;
	private CustomerVO customer;
	

	public OrderVO(int orderNo, String state, LocalDateTime timestampStartedOrder, CustomerVO customer) {

		this.orderNo = orderNo;
		this.setTimestampStartedOrder(timestampStartedOrder);
		this.setCustomer(customer);
		this.setState(state);
		index = 0;

		
		
		this.shoppingBasket = new DishVO[MAX_DISHES];

	

	}

	public OrderVO(){
		this(0,null,null,null);

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
		return index;
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


	public void setCustomer(CustomerVO customer) {
		this.customer = customer;
	}
	
	public CustomerVO getCustomer() {

		return this.customer;
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
			shoppingBasket[index++] = dish;

		}

	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + index;
		result = prime * result + orderNo;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((timestampStartedOrder == null) ? 0 : timestampStartedOrder.hashCode());
		result = prime * result + ((timestampDeliveredOrder == null) ? 0 : timestampDeliveredOrder.hashCode());
		result = prime * result + Arrays.hashCode(shoppingBasket);
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderVO other = (OrderVO) obj;
		if (index != other.index)
			return false;
		if (orderNo != other.orderNo)
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (timestampStartedOrder == null) {
			if (other.timestampStartedOrder != null)
				return false;
		} else if (!timestampStartedOrder.equals(other.timestampStartedOrder))
			return false;
		if (timestampDeliveredOrder == null) {
			if (other.timestampDeliveredOrder != null)
				return false;
		} else if (!timestampDeliveredOrder.equals(other.timestampDeliveredOrder))
			return false;
		if (!Arrays.equals(shoppingBasket, other.shoppingBasket))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		return true;
	}

	@Override
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


