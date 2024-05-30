package de.thb.dim.pizzaPronto.valueObjects;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class OrderVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int orderNo;
    private StateOfOrderVO state;
    private LocalDateTime timestampStartedOrder;
    private LocalDateTime timestampDeliveredOrder;
    private List<DishVO> shoppingBasket;
    private CustomerVO customer;
	

	public OrderVO(int orderNo, StateOfOrderVO state, LocalDateTime timestampStartedOrder, CustomerVO customer) {
		
		this.orderNo = orderNo;
		this.setTimestampStartedOrder(timestampStartedOrder);
		this.setCustomer(customer);
		this.setState(state);		
		shoppingBasket = new LinkedList<DishVO>();
	}


	// Getter und Setter

	

    public StateOfOrderVO getState() {
        return state;
    }


	public void setState(StateOfOrderVO state) {
		this.state = state;
	}

	
	public int getOrderNo() {
		return orderNo;
	}

	public int getNumberOfDishes() {
        return shoppingBasket.size();
    }

	public DishVO getDish(int index) {
    if (index < 0 || index >= shoppingBasket.size()) {
        throw new IndexOutOfBoundsException("Invalid index.");
    }
    return shoppingBasket.get(index);
}



	public void setCustomer(CustomerVO customer) {
        this.customer = customer;
    }
	
	public CustomerVO getCustomer() {

		return this.customer;
	}

	public void setShoppingBasket(List<DishVO> dishes) {
        shoppingBasket = dishes;
    }

	public List<DishVO> getShoppingBasket() {
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

	

	public void deleteDish(DishVO dish) {
		
			shoppingBasket.remove(dish);

	}


   public void deleteDish(int index) {
		
			shoppingBasket.remove(index); 

	}




	public void addDish(DishVO dish) {
        shoppingBasket.add(dish);
    }

	

	@Override
	public int hashCode() {
		int hc = 0;
		final int hashMultiplier = 59;
		hc = hashMultiplier * orderNo;

		return hc;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass()) {
			return false;
		}

		OrderVO other = (OrderVO) obj;
		if (orderNo != other.getOrderNo()) {
			return false;
		}
		return true;
	}

	public String toString() {

		StringBuilder text = new StringBuilder(String.format(
				"OrderVO " + getOrderNo()
						+ " from %1$tm/%1$td/%1$tY %1$tH:%1$tM with delivery at  %2$tm/%2$td/%2$tY %2$tH:%2$tM\n",
				timestampStartedOrder, timestampDeliveredOrder));

		text.append("of customer: " + customer.getLastName() + " " + customer.getFirstName() + ", ID of customer: "
				+ customer.getId() + "\n");

		for (int i = 0; i < shoppingBasket.size(); i++) {
			if (shoppingBasket.get(i) != null) {
				text.append(shoppingBasket.get(i).toString());
				text.append("\n");
			}
		}

		return text.toString();

	}

    public float calculatePriceDishes() {
        float total = 0.0f;
        for (DishVO dish : shoppingBasket) {
            if (dish != null) {
                total += dish.getPrice();
            }
        }
        return total;
    }
}


