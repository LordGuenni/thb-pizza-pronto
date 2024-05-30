package de.thb.dim.pizzaProntoGUI.controller;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import de.thb.dim.pizzaPronto.CustomerVO;
import de.thb.dim.pizzaPronto.OrderVO;
import de.thb.dim.pizzaPronto.PizzaVO;
import de.thb.dim.pizzaProntoGUI.view.CustomerPanel;
import de.thb.dim.pizzaProntoGUI.view.MainView;
import de.thb.dim.pizzaProntoGUI.view.MenuPanel;
import de.thb.dim.pizzaProntoGUI.view.OrderPanel;

public class OrderController {
	
	@SuppressWarnings("unused")
	private MainView view;
	
	private JTable orderMenuTable;
	private JTable shoppingBasketTable;
	private JTable orderTable;
	private DefaultTableModel orderMenuTableModel;
	private DefaultTableModel shoppingBasketTableModel;
	private DefaultTableModel orderTableModel;
	
	private JLabel itemCountLabel;

	public OrderController(MainView view) {
		
		setView(view);
		
		OrderPanel orderPanel = view.getLayoutPanel().getOrderPanel();
		CustomerPanel customerPanel = view.getLayoutPanel().getCustomerPanel();
		MenuPanel menuPanel = view.getLayoutPanel().getMenuPanel();
		
		JTable customerTable = customerPanel.getTable();
		JTable menuTable = menuPanel.getTable();
		orderMenuTable = orderPanel.getMenuTable();
		shoppingBasketTable = orderPanel.getShoppingBasketTable();
		orderTable = orderPanel.getCurrentOrderstable();
		
		ListSelectionModel tableSelection = orderTable.getSelectionModel();
		DefaultTableModel customerTableModel = (DefaultTableModel) customerTable.getModel();
		DefaultTableModel menuTableModel = (DefaultTableModel) menuTable.getModel();
		orderMenuTableModel = (DefaultTableModel) orderMenuTable.getModel();
		shoppingBasketTableModel = (DefaultTableModel) shoppingBasketTable.getModel();
		orderTableModel = (DefaultTableModel) orderTable.getModel();
		
		JButton startButton = orderPanel.getStartButton();
		JButton addButton = orderPanel.getAddButton();
		JButton removeButton = orderPanel.getRemoveButton();
		JButton printButton = orderPanel.getPrintButton();
		
		JLabel numberLabelRight = orderPanel.getNumberLabelRight();
		JLabel idLabelRight = orderPanel.getIdLabelRight();
		JLabel nameLabelRight = orderPanel.getNameLabelRight();
		JLabel genderLabelRight = orderPanel.getGenderLabelRight();
		JLabel dateOfBirthRight = orderPanel.getDateOfBirthRight();
		JLabel startedLabelRight = orderPanel.getStartedLabelRight();
		JLabel streetLabelRight = orderPanel.getStreetLabelRight();
		itemCountLabel = orderPanel.getItemCountLabel();

		
		@SuppressWarnings("unchecked")
		JComboBox<Integer> comboBox = view.getLayoutPanel().getOrderPanel().getCustomerComboBox();
		
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int selectedItem = (int)comboBox.getSelectedItem();
				
				boolean isFound = false;
				
				int i = 0;
				
				while(isFound == false){
					if(selectedItem == (int) customerTable.getValueAt(i, 0))
						isFound = true;
					i++;
				}
				
				if(isFound == true) {
					CustomerVO customer = (CustomerVO) customerTableModel.getValueAt(i-1, 0);
					OrderVO order = new OrderVO(LocalDateTime.now(), customer);
					
					Object[] row = new Object[8];
					
					row[0] = order;
					row[1] = order.getOrderNo();
					
					orderTableModel.addRow(row);
					
					numberLabelRight.setText(Integer.toString(order.getOrderNo()));
					startedLabelRight.setText(String.format("%1$tm/%1$td/%1$tY %1$tH:%1$tM", order.getTimestampStartedOrder()));
					idLabelRight.setText(Integer.toString(order.getCustomer().getId()));
					nameLabelRight.setText(order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName());
					genderLabelRight.setText(order.getCustomer().getGender());
					dateOfBirthRight.setText(order.getCustomer().getDateOfBirth().format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
					itemCountLabel.setText(Integer.toString(order.getIndex()));
					streetLabelRight.setText(order.getCustomer().getStreet() + " " + order.getCustomer().getHouseNumber());
					
					orderTable.setRowSelectionInterval(orderTable.getRowCount()-1, orderTable.getRowCount()-1);
				}
			}
			
		});
		
		tableSelection.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = orderTable.getSelectedRow();
				OrderVO order = (OrderVO) orderTableModel.getValueAt(index, 0);
				
				numberLabelRight.setText(Integer.toString(order.getOrderNo()));
				startedLabelRight.setText(String.format("%1$tm/%1$td/%1$tY %1$tH:%1$tM", order.getTimestampStartedOrder()));
				idLabelRight.setText(Integer.toString(order.getCustomer().getId()));
				nameLabelRight.setText(order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName());
				genderLabelRight.setText(order.getCustomer().getGender());
				dateOfBirthRight.setText(order.getCustomer().getDateOfBirth().format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
				itemCountLabel.setText(Integer.toString(order.getNumberOfDishes()));
				streetLabelRight.setText(order.getCustomer().getStreet() + " " + order.getCustomer().getHouseNumber());
				
				updateShoppingBasket();
			}
			
		});
		
		customerTableModel.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				int cnt = customerTable.getRowCount();				
				Vector<Integer> items = new Vector<Integer>();
				
				for(int i = 0; i < cnt; i++)
				{
					items.add((int) customerTable.getModel().getValueAt(i,1));
				}
				
				comboBox.setModel(new DefaultComboBoxModel<Integer>(items));				
			}
			
		});
		
		menuTableModel.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				int rowCount = menuTable.getRowCount();
				
				orderMenuTableModel.setRowCount(0);
				
				for(int i = 0;i < rowCount; i++) {
						Object[] row = new Object[6];
					
						row[0] = menuTableModel.getValueAt(i, 5);
						row[1] = menuTableModel.getValueAt(i, 0);
						row[2] = menuTableModel.getValueAt(i, 1);
						row[3] = menuTableModel.getValueAt(i, 3);
						
						orderMenuTableModel.addRow(row);											
				}
			}
			
		});
		
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				OrderVO order = (OrderVO) orderTableModel.getDataVector().get(orderTable.getSelectedRow()).get(0);
				
				int[] index = orderMenuTable.getSelectedRows();
				for(int i=0; i<index.length ; i++ ) {
					
					PizzaVO dish = (PizzaVO)orderMenuTableModel.getDataVector().get(index[i]).get(0);

					order.addDish(dish);					
				}
				updateShoppingBasket();			
				
			}
			
		});
		
		
		
		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				OrderVO order = (OrderVO) orderTableModel.getDataVector().get(orderTable.getSelectedRow()).get(0);
				order.deleteDish();
				updateShoppingBasket();
			}
			
		});
		
		printButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				OrderVO order = (OrderVO) orderTableModel.getDataVector().get(orderTable.getSelectedRow()).get(0);
				
				EventQueue.invokeLater(new Runnable(){
					
					@Override
					public void run(){
						JFrame frame = new JFrame("Print Order");
											
						JPanel innerPanel = new JPanel(new GridBagLayout());
						innerPanel.setOpaque(true);
						innerPanel.setBackground(Color.WHITE);
						innerPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
						
						GridBagConstraints c0 = new GridBagConstraints();
						
						JLabel label = new JLabel("Invoice:");
						label.setFont(new Font("Arial", Font.PLAIN, 20));
						label.setForeground(new Color(0x606060));
						c0.gridx = 0;
						c0.gridy = 0;
						c0.insets = new Insets(20,20,10,20);
						innerPanel.add(label, c0);
						
						JTextArea details = new JTextArea();						
						details.setBackground(Color.WHITE);
						details.setEditable(false);						
						details.setText(order.toString());
						c0.gridx = 0;
						c0.gridy = 1;
						c0.insets = new Insets(10,20,20,20);
						innerPanel.add(details, c0);
						
						JPanel outerPanel = new JPanel(new GridBagLayout());
						outerPanel.setOpaque(true);
						outerPanel.setBackground(new Color(0xeaeaea));
						
						GridBagConstraints c1 = new GridBagConstraints();
						c1.insets = new Insets(20,20,20,20);
						outerPanel.add(innerPanel,c1);
						
						frame.add(outerPanel);
						
						frame.setLocationRelativeTo(null);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.pack();
						frame.setVisible(true);
					}
					
				});
				
			}
			
		});
		
		
		
		
	}
	
	private void updateShoppingBasket() {
		OrderVO order = (OrderVO) orderTableModel.getDataVector().get(orderTable.getSelectedRow()).get(0);
		shoppingBasketTableModel.setRowCount(0);
		for(int i = 0; i < order.getNumberOfDishes(); i++) {
			Object[] row = new Object[3];
			row[1] = order.getShoppingBasket()[i].getName();
			row[2] = order.getShoppingBasket()[i].getPrice();
			
			shoppingBasketTableModel.addRow(row);
		}
		itemCountLabel.setText(Integer.toString(order.getNumberOfDishes()));

	}
	
	public void setView(MainView view) {
		this.view = view;
	}

}
