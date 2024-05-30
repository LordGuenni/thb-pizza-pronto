package de.thb.dim.pizzaProntoGUI.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;

import de.thb.dim.pizzaPronto.CustomerVO;
import de.thb.dim.pizzaProntoGUI.view.CustomerPanel;
import de.thb.dim.pizzaProntoGUI.view.MainView;


public class CustomerController {
	
	private MainView view;

	public CustomerController(MainView view) {
		
		setView(view);
		
		CustomerPanel customerPanel = view.getLayoutPanel().getCustomerPanel();
		
		JButton addButton = customerPanel.getAddButton();
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String lastName = customerPanel.getLastNameTextField().getText();
				String firstName = customerPanel.getFirstNameTextField().getText();
				String gender = (String) customerPanel.getGenderComboBox().getSelectedItem();
				
				int day = (int) customerPanel.getDayComboBox().getSelectedItem();
				int month = (int) customerPanel.getMonthComboBox().getSelectedItem();
				String yearAsString = customerPanel.getYearTextField().getText();
				int yearAsInt = 0;
				
				if(!yearAsString.equals(""))
					yearAsInt = Integer.parseInt(yearAsString);
				
				LocalDate dob = LocalDate.of(yearAsInt, month, day);
				
				CustomerVO customer = new CustomerVO(lastName, firstName, gender, dob);
				
				Object[] row = new Object[4];
				row[0] = customer.getFirstName();
				row[1] = customer.getLastName();
				row[2] = customer.getGender();
				row[3] = customer.dobToString();
				
				customerPanel.getTableModel().addRow(row);
				
				customerPanel.getFirstNameTextField().setText(null);
				customerPanel.getLastNameTextField().setText(null);
				customerPanel.getYearTextField().setText(null);
				customerPanel.getDayComboBox().setSelectedIndex(0);
				customerPanel.getMonthComboBox().setSelectedIndex(0);
				customerPanel.getGenderComboBox().setSelectedIndex(0);
			}
			
		});
		
		
		JButton removeButton = customerPanel.getRemoveButton();
		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int numRows = customerPanel.getTable().getSelectedRows().length;
				for(int i=0; i<numRows ; i++ ) {

					customerPanel.getTableModel().removeRow(customerPanel.getTable().getSelectedRow());
				}
				
			}
			
		});
		
	}
	
	public void setView(MainView view) {
		this.view = view;
	}

}
