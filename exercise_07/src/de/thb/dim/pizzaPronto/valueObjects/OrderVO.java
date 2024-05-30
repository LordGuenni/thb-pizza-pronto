package de.thb.dim.pizzaPronto.valueObjects;


import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class OrderVO {

	private int orderNo;
    private StateOfOrderVO state;
    private LocalDateTime timestampStartedOrder;
    private LocalDateTime timestampDeliveredOrder;
    private List<DishVO> shoppingBasket;
    private CustomerVO customer;
	

	public OrderVO(int orderNo, StateOfOrderVO state, LocalDateTime timestampStartedOrder, CustomerVO customer) {
        this.orderNo = orderNo;
        this.timestampStartedOrder = timestampStartedOrder;
        this.customer = customer;
        this.state = state;
        shoppingBasket = new LinkedList<>();
    }

	public OrderVO() {
        this(0, null, null, null);
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
    if (state == StateOfOrderVO.CONFIRMED) {
        System.out.println("Your order is complete, you cannot delete any dishes.");
    } else {
        shoppingBasket.remove(dish);
    }
}


    public void deleteDish(int index) {
    if (index < 0 || index >= shoppingBasket.size()) {
        throw new IndexOutOfBoundsException("Invalid index.");
    }

    DishVO dish = shoppingBasket.get(index);
    if (dish == null) {
        throw new IllegalArgumentException("No dish found at index " + index + ".");
    }

    shoppingBasket.remove(index);
}




	public void addDish(DishVO dish) {
        shoppingBasket.add(dish);
    }

	

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + orderNo;
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((timestampDeliveredOrder == null) ? 0 : timestampDeliveredOrder.hashCode());
        result = prime * result + ((timestampStartedOrder == null) ? 0 : timestampStartedOrder.hashCode());
        result = prime * result + shoppingBasket.hashCode();
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
        if (customer == null) {
            if (other.customer != null)
                return false;
        } else if (!customer.equals(other.customer))
            return false;
        if (orderNo != other.orderNo)
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (timestampDeliveredOrder == null) {
            if (other.timestampDeliveredOrder != null)
                return false;
        } else if (!timestampDeliveredOrder.equals(other.timestampDeliveredOrder))
            return false;
        if (timestampStartedOrder == null) {
            if (other.timestampStartedOrder != null)
                return false;
        } else if (!timestampStartedOrder.equals(other.timestampStartedOrder))
            return false;
        if (!shoppingBasket.equals(other.shoppingBasket))
            return false;
        return true;
    }

	@Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (DishVO dish : shoppingBasket) {
            if (dish != null) {
                s.append(dish);
            }
        }
        s.append("\n");
        s.append("State: " + this.getState() + "\n");
        s.append("Started: " + this.getTimestampStartedOrder() + "\n");
        s.append("Delivered: " + this.getTimestampDeliveredOrder() + "\n");
        s.append("OrderNo: " + this.getOrderNo() + "\n");
        s.append("Customer: " + this.getCustomer() + "\n");
        s.append("\n");
        return s.toString();
    }

    public float calculatePriceDishes() {
        float totalPrice = 0.0f;
        for (DishVO dish : shoppingBasket) {
            if (dish != null) {
                totalPrice += dish.getPrice();
            }
        }
        return totalPrice;
    }
}


