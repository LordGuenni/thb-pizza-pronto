package de.thb.dim.pizzaProntoGUI.controller;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.thb.dim.pizzaPronto.PizzaVO;
import de.thb.dim.pizzaProntoGUI.view.MainView;
import de.thb.dim.pizzaProntoGUI.view.MenuPanel;

public class MenuController {
	
	private MainView view;

	public MenuController(MainView view) {
		
	setView(view);
		
	MenuPanel menuPanel = view.getLayoutPanel().getMenuPanel();
		
		JButton addButton = menuPanel.getAddButton();
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int ingredientCount = menuPanel.getIngredientTableModel().getRowCount();
				String name = menuPanel.getNameTextField().getText();
				String[] ingredients = new String [ingredientCount];				
				String priceAsString = menuPanel.getPriceTextField().getText().replace(',', '.');
				float priceAsFloat = 0.0F;
				
				if (!priceAsString.equals("")) {
					try {
						priceAsFloat = Float.parseFloat(priceAsString);
					} catch (NumberFormatException exception) {
						System.err.println("Input error by price: " + exception.getMessage() );
					}
				}
					
				for(int i = 0; i < ingredientCount; i++) {
					ingredients[i] = (String) menuPanel.getIngredientTableModel().getValueAt(i, 0);
				}
				
				PizzaVO pizza = new PizzaVO(name, ingredients, priceAsFloat);
				
				int rowCnt = menuPanel.getTableModel().getRowCount();
				
				boolean isEqual = false;
				
				for(int i = 0; i < rowCnt; i++) {
					if (pizza.equals(menuPanel.getTableModel().getValueAt(i, 5)))
						isEqual = true;
				}
				
				if(isEqual == true) {
						EventQueue.invokeLater(new Runnable(){
							
							@Override
							public void run(){
								JFrame frame = new JFrame("Note");
													
								JPanel innerPanel = new JPanel(new GridBagLayout());
								innerPanel.setOpaque(true);
								innerPanel.setBackground(Color.WHITE);
								innerPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
								
								GridBagConstraints c0 = new GridBagConstraints();
								
								JLabel label = new JLabel("This dish is already on the menu.");
								label.setFont(new Font("Arial", Font.PLAIN, 18));
								label.setForeground(new Color(0x606060));

								c0.insets = new Insets(20,20,20,20);
								innerPanel.add(label, c0);
								
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
					
				} else {
					Object[] row = new Object[6];
					
					row[0] = menuPanel.getDishComboBox().getSelectedItem();
					row[1] = pizza.getName();
					row[2] = Arrays.toString(pizza.getIngredients());
					row[3] = pizza.getPrice();
					row[4] = pizza.hashCode();
					row[5] = pizza;
					
					menuPanel.getTableModel().addRow(row);
					
					menuPanel.getPriceTextField().setText(null);
					menuPanel.getNameTextField().setText(null);
					menuPanel.getIngredientTableModel().setRowCount(0);					
				}
				
			}
			
		});
		
		
		JButton removeButton = menuPanel.getRemoveButton();
		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int numRows = menuPanel.getTable().getSelectedRows().length;
				for(int i=0; i<numRows ; i++ ) {

					menuPanel.getTableModel().removeRow(menuPanel.getTable().getSelectedRow());
				}
				
			}
			
		});
		
		JButton addIngredientButton = menuPanel.getAddIngredientButton();
		addIngredientButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					
				Object[] row = new Object[1];
				
				row[0] = menuPanel.getIngredientTextField().getText();
				
				menuPanel.getIngredientTableModel().addRow(row);
				
				menuPanel.getIngredientTextField().setText(null);
				
			}
			
		});
		
		JButton removeIngredientButton = menuPanel.getRemoveIngredientButton();
		removeIngredientButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int numRows = menuPanel.getIngredientsTable().getSelectedRows().length;
				for(int i=0; i<numRows ; i++ ) {

					menuPanel.getIngredientTableModel().removeRow(menuPanel.getIngredientsTable().getSelectedRow());
				}
				
			}
			
		});
		
		JButton printButton = menuPanel.getPrintButton();
		printButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable(){
					
					@Override
					public void run(){
						JFrame frame = new JFrame("Print Details");
						
						int numRows = menuPanel.getTable().getSelectedRows().length;
						
						StringBuilder sb = new StringBuilder();
						
						int[] idx = menuPanel.getTable().getSelectedRows();
						
						for(int i=0; i<numRows ; i++ ) {

							String s = menuPanel.getTableModel().getValueAt(idx[i], 5).toString();
							sb.append(s);
							sb.append("\n");
						}
											
						JPanel innerPanel = new JPanel(new GridBagLayout());
						innerPanel.setOpaque(true);
						innerPanel.setBackground(Color.WHITE);
						innerPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
						
						GridBagConstraints c0 = new GridBagConstraints();
						
						JLabel label = new JLabel("Printed Dishes:");
						label.setFont(new Font("Arial", Font.PLAIN, 20));
						label.setForeground(new Color(0x606060));
						c0.gridx = 0;
						c0.gridy = 0;
						c0.insets = new Insets(20,20,10,20);
						innerPanel.add(label, c0);
						
						JTextArea details = new JTextArea();						
						details.setBackground(Color.WHITE);
						details.setEditable(false);						
						details.setText(sb.toString());
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
		
		JButton copyButton = menuPanel.getCopyButton();
		copyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int numRows = menuPanel.getTable().getSelectedRows().length;
				for(int i=0; i<numRows ; i++ ) {

					int[] idx = menuPanel.getTable().getSelectedRows();
					
					PizzaVO org = (PizzaVO) menuPanel.getTableModel().getValueAt(idx[i], 5);
					PizzaVO cpy = org.clone();
					
					Object[] row = new Object[6];
					
					row[0] = "Pizza";
					row[1] = cpy.getName();
					row[2] = Arrays.toString(cpy.getIngredients());
					row[3] = cpy.getPrice();
					row[4] = cpy.hashCode();
					row[5] = cpy;
					
					menuPanel.getTableModel().addRow(row);
				}
				
			}
			
		});
		
	}
	
	public void setView(MainView view) {
		this.view = view;
	}

}
