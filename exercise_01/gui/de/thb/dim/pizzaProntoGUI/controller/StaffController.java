package de.thb.dim.pizzaProntoGUI.controller;

// import de.thb.dim.pizzaPronto.valueObjects.ChefVO;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import de.thb.dim.pizzaPronto.ChefVO;
import de.thb.dim.pizzaProntoGUI.view.MainView;
import de.thb.dim.pizzaProntoGUI.view.StaffPanel;


public class StaffController {
	
	private MainView view;

	public StaffController(MainView view) {
		
		setView(view);
		
		StaffPanel staffPanel = view.getLayoutPanel().getStaffPanel2();
		
		JButton addButton = staffPanel.getAddButton();
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String colorString = (String) staffPanel.getColorComboBox().getSelectedItem();
				
				Color color = convertStringToColor(colorString);
				
				ChefVO chef = new ChefVO(staffPanel.getLastNameTextField().getText(),
										 staffPanel.getFirstNameTextField().getText(),
										 color);
				
				Object[] row = new Object[3];
				row[0] = chef.getFirstName();
				row[1] = chef.getLastName();				
				color = chef.getColorApron();
				row[2] = convertColorToString(color);
				
				staffPanel.getTableModel().addRow(row);
				
				staffPanel.getFirstNameTextField().setText(null);
				staffPanel.getLastNameTextField().setText(null);
			}
			
		});
		
		
		JButton removeButton = staffPanel.getRemoveButton();
		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int numRows = staffPanel.getTable().getSelectedRows().length;
				for(int i=0; i<numRows ; i++ ) {

					staffPanel.getTableModel().removeRow(staffPanel.getTable().getSelectedRow());
				}
				
			}
			
		});
		
	}
	
	static Color convertStringToColor(String name) {
		
		Color color = null;
		
        switch(name){ 
        case "White": 
            color = Color.WHITE; 
            break; 
        case "Black": 
        	color = Color.BLACK; 
            break; 
        case "Red": 
        	color = Color.RED; 
        	break; 
        case "Green": 
        	color = Color.GREEN; 
            break; 
        case "Blue":
        	color = Color.BLUE;
        	break;
        case "Yellow":
        	color = Color.YELLOW;
        	break;
        case "Pink":
        	color = Color.PINK; 
        	break;
        }
        return color;
	}
	
	static String convertColorToString(Color color) {
		
		String name = null;
		
		if (color == Color.WHITE)
			name = "White";
		else if (color == Color.BLACK)
			name = "Black";
		else if (color == Color.RED)
			name = "Red";
		else if (color == Color.GREEN)
			name = "Green";
		else if (color == Color.BLUE)
			name = "Blue";
		else if (color == Color.YELLOW)
			name = "Yellow";
		else if (color == Color.PINK)
			name = "Pink";
		
        return name;
	}
	
	public void setView(MainView view) {
		this.view = view;
	}

}
