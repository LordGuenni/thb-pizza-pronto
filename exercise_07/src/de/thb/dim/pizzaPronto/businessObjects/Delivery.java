package de.thb.dim.pizzaPronto.businessObjects;
import java.time.LocalDateTime;
import java.util.Random;

import de.thb.dim.pizzaPronto.valueObjects.CustomerVO;
import de.thb.dim.pizzaPronto.valueObjects.DeliveryManVO;
import de.thb.dim.pizzaPronto.valueObjects.EmployeeVO;
import de.thb.dim.pizzaPronto.valueObjects.OrderVO;
import de.thb.dim.pizzaPronto.valueObjects.StateOfOrderVO;

public class Delivery implements IService {

	private EmployeeVO[] employees;

	public Delivery() {
		employees = new EmployeeVO[2];
		// create deliveryman
		employees[0] = new DeliveryManVO("Lieferant01", "Rasender", "Rudi");
		// create deliveryman
		employees[1] = new DeliveryManVO("Lieferant02", "Lacy", "Lutz");
	}

	@Override
	public String startService(OrderVO order) {
		String s = "";
		CustomerVO currentCustomer;
		EmployeeVO employee = selectEmployee();

		if (order == null) {
			s = String.format("\nService of DeliveryManVO %s: No order available.",
					employee.getPersonnelNo());
		} else {

			currentCustomer = order.getCustomer();

			if (currentCustomer == null) {
				s = String.format("\nService of DeliveryManVO %s: No customer available.",
						employee.getPersonnelNo());
			} else {
				if (order.getState().equals(StateOfOrderVO.READY)) {

					order.setState(StateOfOrderVO.DELIVERED);

					s += String.format("\nDrive to customer  %s %s, in %s %s\n", currentCustomer.getLastName(),
							currentCustomer.getFirstName(), currentCustomer.getStreet(),
							currentCustomer.getHouseNumber());
					s += String.format(
							"\nService of DeliveryManVO %s: ",
							employee.getPersonnelNo());
					s += String.format(
							"Order is delivered on %1$tm/%1$td/%1$tY at %1$tH:%1$tM o'clock",
							 LocalDateTime.now());

				}
				else {
					s = String.format("\nService of DeliveryManVO %s: No order is ready for processing.",
							employee.getPersonnelNo());
				}
			}
		}
		return s;
	}

	private EmployeeVO selectEmployee() {
		EmployeeVO employee;
		Random zufall = new Random();
		employee = employees[zufall.nextInt(employees.length - 1)];
		return employee;
	}

	/**
	 * @return the employees
	 */
	public EmployeeVO[] getEmployees() {
		return employees;
	}

	/**
	 * @param employees the employees to set
	 */
	public void setEmployees(EmployeeVO[] employees) {
		this.employees = employees;
	}
	
	

}