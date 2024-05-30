package de.thb.dim.pizzaProntoGUI.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;

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
				String[] ingredients = new String[ingredientCount];
				String priceAsString = menuPanel.getPriceTextField().getText().replace(',', '.');
				float priceAsFloat = 0.0F;

				if (!priceAsString.equals("")) {
					try {
						priceAsFloat = Float.parseFloat(priceAsString);
					} catch (NumberFormatException exception) {
						System.err.println("Input error by price: " + exception.getMessage() );
					}
				}

				for (int i = 0; i < ingredientCount; i++) {
					ingredients[i] = (String) menuPanel.getIngredientTableModel().getValueAt(i, 0);
				}

				PizzaVO pizza = new PizzaVO(name, ingredients, priceAsFloat);

				Object[] row = new Object[4];

				row[0] = menuPanel.getDishComboBox().getSelectedItem();
				row[1] = pizza.getName();
				row[2] = Arrays.toString(pizza.getIngredients());
				row[3] = pizza.getPrice();

				menuPanel.getTableModel().addRow(row);

				menuPanel.getPriceTextField().setText(null);
				menuPanel.getNameTextField().setText(null);
				menuPanel.getIngredientTableModel().setRowCount(0);

			}

		});

		JButton removeButton = menuPanel.getRemoveButton();
		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int numRows = menuPanel.getTable().getSelectedRows().length;
				for (int i = 0; i < numRows; i++) {

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
				for (int i = 0; i < numRows; i++) {

					menuPanel.getIngredientTableModel().removeRow(menuPanel.getIngredientsTable().getSelectedRow());
				}

			}

		});

	}

	public void setView(MainView view) {
		this.view = view;
	}

}
