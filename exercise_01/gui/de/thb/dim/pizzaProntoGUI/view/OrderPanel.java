package de.thb.dim.pizzaProntoGUI.view;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class OrderPanel extends JPanel{

	public OrderPanel() {

		setOpaque(true);
		setBackground(new Color(0xeaeaea));
		setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, new ImageIcon("gui/de/thb/dim/pizzaProntoGUI/images/border.png")));

		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
	}
}
