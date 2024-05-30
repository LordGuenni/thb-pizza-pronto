package de.thb.dim.pizzaPronto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.lang.StringBuilder;


public class OrderVO {

	private int index;
	private int orderNo;
	private final static int MAX_DISHES = 10;
	private static int nextOrderNo = 0;
	private LocalDateTime timestampStartedOrder;
	private LocalDateTime timestampDeliveredOrder;
	private PizzaVO[] shoppingBasket;
	private CustomerVO customer;
	
	
// Getter und Setter
	
	public int getIndex() {
		return index;
	}
	

	public int getOrderNo() {
		return orderNo;
	}


	
	public int getNumberOfDishes() {
		int zähler = 0;
		for(int i = 0;i<shoppingBasket.length;i++){
			
			if(shoppingBasket[i] != null){
				zähler++;
				
			}
			
			
		}
		
		return zähler;
	}
	public static int getMAX_DISHES() {
		
		return MAX_DISHES;
	}
	
	public PizzaVO getDish(int x) {
		if(x > MAX_DISHES) {
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
	
	public void setShoppingBasket(PizzaVO[] pizzen) {
		
		shoppingBasket = pizzen;
		}

	
	public PizzaVO[] getShoppingBasket() {
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
	
	public OrderVO(LocalDateTime now, CustomerVO customer) {
		this.timestampStartedOrder=now;
		this.customer=customer;
		this.shoppingBasket=new PizzaVO[MAX_DISHES];
		this.index=0;
		int yearNum=(nextOrderNo-(nextOrderNo%100000))/100000;
		int thisYear=LocalDateTime.now().getYear();
		if (nextOrderNo==0||yearNum!=thisYear) {
			this.orderNo=thisYear*100000;
			nextOrderNo=orderNo+1;
		}
		else {
			orderNo=nextOrderNo++;
		}
		
	}
	
	public void deleteDish() {
		if(getNumberOfDishes()>=1) {
			shoppingBasket[index]=null;
			index--;
		}
		
	}
	
	public void addDish(PizzaVO pizza) {
		
		if(getNumberOfDishes() < MAX_DISHES) {
		shoppingBasket[index]=pizza;
		index++;
		
		
		}
		
	}
	
public boolean equals(Object orderlocal) {
		
		if(this == orderlocal) {
			return true;
		}
		if(orderlocal == null){
			return false;
		}
		if(this.getClass() != orderlocal.getClass()) {
			return false;
		}
		OrderVO order = (OrderVO) orderlocal;
		
		return Objects.equals(Objects.hash(order.shoppingBasket,order.timestampStartedOrder,order.timestampDeliveredOrder),hashCode());
		
	}
   
	public int hashCode() {
		return Objects.hash(shoppingBasket,timestampStartedOrder,timestampDeliveredOrder);
	
	}
	
	public String toString() {
		
		StringBuilder s = new StringBuilder();
		
		
		for(int i = 0;i < shoppingBasket.length; i++){
			if(this.getShoppingBasket()[i] != null) {
				s.append(getShoppingBasket()[i]);
			}
		}
		
		
		
		s.append(this.timestampStartedOrder);
		s.append(this.timestampDeliveredOrder);
		s.append(this.orderNo);
		s.append(this.customer);
		return s.toString();
		
	}
	
	
}
