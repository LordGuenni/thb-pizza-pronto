package de.thb.dim.pizzaProntoGUI.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

@SuppressWarnings("serial")
public class CustomerPanel extends JPanel {

	private JPanel headerPanel;
	private JPanel addPanel;
	private JPanel tablePanel;
	private JPanel hintPanel;
	private JPanel datePanel;
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField yearTextField;
	private JLabel dateOfBirthLabel;
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel genderLabel;
	private JLabel addTopicLabel;
	private JLabel tableTopicLabel;
	private JLabel hintLabel;
	private JLabel day;
	private JLabel month;
	private JLabel year;
	private JComboBox<String> genderComboBox;
	private JComboBox<Integer> dayComboBox;
	private JComboBox<Integer> monthComboBox;
	private DefaultButton addButton;
	private DefaultButton removeButton;
	private DefaultButton printButton;
	private JTable table;
	private JScrollPane tableScrollPane;
	private DefaultTableModel tableModel;

	public CustomerPanel() {
		setOpaque(true);
		setBackground(new Color(0xeaeaea));
		setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, new ImageIcon("gui/de/thb/dim/pizzaProntoGUI/images/border.png")));
		setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		
		headerPanel = new JPanel();
		headerPanel = new JPanel(new GridBagLayout());
		headerPanel.setBackground(new Color(0xeaeaea));
		addComponentsToHeaderPanel(headerPanel);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.insets = new Insets(20, 30, 15, 0);
		c.fill = GridBagConstraints.BOTH;
		add(headerPanel, c);

		addPanel = new JPanel();
		addPanel.setBackground(Color.WHITE);
		addPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		addPanel.setPreferredSize(new Dimension(500,0));
		addComponentsToAddPanel(addPanel);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0;
		c.insets = new Insets(10, 30, 10, 10);
		add(addPanel, c);

		tablePanel = new JPanel();
		tablePanel.setBackground(Color.WHITE);
		tablePanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		addComponentsToTablePanel(tablePanel);
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10, 0, 10, 10);
		add(tablePanel, c);
		
		hintPanel = new JPanel();
		hintPanel.setBackground(Color.WHITE);
		hintPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		addComponentsToHintPanel(hintPanel);
		c.gridx = 0;
		c.gridy = 4;
		c.gridheight = 0;
		c.gridwidth = 2;
		c.weightx = 1;
		c.weighty = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 30, 10, 10);
		add(hintPanel, c);
	}

	private void addComponentsToHintPanel(JPanel hintPanel) {
		hintPanel.setLayout(new GridBagLayout());
		hintLabel = new JLabel("<html><p><strong><span style=\"font-size: 10px;\">Hinweis</span></strong></p>\n" + 
				"<p><span style=\"font-size: 10px;\">Es muessen in der Klasse CustomerVO zwingend die Methoden hashCode() und equals() implementiert sein, sonst ist es nicht möglich einen neuen Kunden hinzuzufuegen.\n" +
				"Beim Eintragen eines neuen Kunden wird mit der equals() Methode geprüft, ob sich der gleiche Kunde bereits in der Liste befindet. Bei korrekter Implementierung wird eine Nachricht angezeigt.\n" +
				"Mit dem Print Button können die in der Liste ausgewählten Kunden nochmal ausgegeben werden. Hierfür muss in Klasse CustomerVO die toString() Methode implementiert sein.</span></p></html>");
		
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.insets = new Insets(10, 10, 10, 10);
		hintPanel.add(hintLabel, c);
	}

	public void addComponentsToAddPanel(JPanel addPanel) {
		addPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		
		addTopicLabel = new JLabel("Add a Customer");
		addTopicLabel.setFont(new Font("Arial", Font.PLAIN, 24));
		addTopicLabel.setForeground(Color.DARK_GRAY);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.insets = new Insets(10, 12, 0, 10);
		addPanel.add(addTopicLabel, c);
				
		firstNameLabel = new JLabel("First Name:");
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.insets = new Insets(10, 12, 0, 10);
		addPanel.add(firstNameLabel, c);
		
		firstNameTextField = new JTextField();
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.insets = new Insets(0, 10, 10, 10);
		addPanel.add(firstNameTextField, c);
		
		lastNameLabel = new JLabel("Last Name:");
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		c.insets = new Insets(0, 12, 0, 10);
		addPanel.add(lastNameLabel, c);
		
		lastNameTextField = new JTextField();
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		c.insets = new Insets(0, 10, 10, 10);
		addPanel.add(lastNameTextField, c);
		
		dateOfBirthLabel = new JLabel("Date of Birth:");
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 2;
		c.insets = new Insets(0, 10, 10, 10);
		addPanel.add(dateOfBirthLabel, c);

		
		datePanel = new JPanel();
//		datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.PAGE_AXIS));
		datePanel.setBackground(Color.WHITE);
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 2;
		c.insets = new Insets(0, 6, 10, 10);
		addPanel.add(datePanel, c);
		
		day = new JLabel("Day:");
//		day.setAlignmentX(Component.LEFT_ALIGNMENT);
		datePanel.add(day);
		
		Integer[] days = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
		dayComboBox = new JComboBox<Integer>(days);
//		dayComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		datePanel.add(dayComboBox);
		
		month = new JLabel("Month:");
//		month.setAlignmentX(Component.LEFT_ALIGNMENT);
		datePanel.add(month);
		
		Integer[] months = {1,2,3,4,5,6,7,8,9,10,11,12};
		monthComboBox = new JComboBox<Integer>(months);
//		monthComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		datePanel.add(monthComboBox);
		
		year = new JLabel("Year:");
		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 1;
		c.weightx = 0;
		c.insets = new Insets(0, 12, 10, 0);
		addPanel.add(year, c);
		
		yearTextField = new JTextField();
	//	yearTextField.setPreferredSize(new Dimension(60,20));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 9;
		c.weightx = 1;
		c.gridwidth = 1;
		c.insets = new Insets(0, 10, 10, 10);
		addPanel.add(yearTextField, c);
		
		
		genderLabel = new JLabel("Gender:");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 10;
		c.gridwidth = 2;
		c.insets = new Insets(0, 10, 0, 10);
		addPanel.add(genderLabel, c);
		
		String[] genders = {"female", "male", "diverse"};
		
		genderComboBox = new JComboBox<String>(genders);
		genderComboBox.setEditable(true);
		c.gridx = 0;
		c.gridy = 11;
		c.gridwidth = 2;
		c.insets = new Insets(0, 10, 20, 10);
		addPanel.add(genderComboBox, c);
		
		addButton = new DefaultButton("Add Customer");
		c.gridx = 0;
		c.gridy = 12;
		c.gridwidth = 2;
		c.insets = new Insets(0, 10, 20, 12);
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.LAST_LINE_END;
		addPanel.add(addButton, c);

	}

	public void addComponentsToTablePanel(JPanel tablePanel) {
		tablePanel.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		String[] columns = { "Customer ID", "First Name", "Last Name", "Gender", "Age", "hashCode", "Object"};
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columns);
		table = new JTable(tableModel);
		TableColumnModel tcm = table.getColumnModel();
		tcm.removeColumn(tcm.getColumn(6));
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setRowHeight(30);
		table.setShowGrid(false);
		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setBackground(new Color(240, 240, 240));

//		table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 14));
		table.setSelectionBackground(new Color(0x50c443));
		
		tableTopicLabel = new JLabel("Your current Customers");
		tableTopicLabel.setFont(new Font("Arial", Font.PLAIN, 24));
		tableTopicLabel.setForeground(Color.DARK_GRAY);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.insets = new Insets(10, 12, 10, 10);
		tablePanel.add(tableTopicLabel, c);

		tableScrollPane = new JScrollPane(table);
		tableScrollPane.setBorder(BorderFactory.createEmptyBorder());
		tableScrollPane.getViewport().setBackground(Color.WHITE);
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10, 10, 10, 10);
		tablePanel.add(tableScrollPane, c);
		
		printButton = new DefaultButton("Print Details");
		c.gridx = 0;
		c.gridy = 2;
		c.weighty = 0;
		c.weightx = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.insets = new Insets(0, 10, 10, 10);
		tablePanel.add(printButton, c);
		
		removeButton = new DefaultButton("Remove Customer");
		c.gridx = 1;
		c.gridy = 2;
		c.weighty = 0;
		c.weightx = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.insets = new Insets(0, 10, 10, 10);
		tablePanel.add(removeButton, c);
		

	}
	
	public void addComponentsToHeaderPanel(JPanel headerPanel) {
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel headerLabelSmall = new JLabel("Pizza Pronto");
		headerLabelSmall.setFont(new Font("Helvetica", Font.PLAIN, 20));
		headerLabelSmall.setForeground(new Color(0x505050));
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.weightx = 1;
		headerPanel.add(headerLabelSmall, c);
		
		JLabel headerLabelLarge = new JLabel("Customers");
		headerLabelLarge.setFont(new Font("Helvetica", Font.PLAIN, 30));
		headerLabelLarge.setForeground(new Color(0x606060));
		c.gridx = 0;
		c.gridy = 1;
		headerPanel.add(headerLabelLarge, c);
	}

	public JPanel getHeaderPanel() {
		return headerPanel;
	}

	public void setHeaderPanel(JPanel headerPanel) {
		this.headerPanel = headerPanel;
	}

	public JPanel getAddPanel() {
		return addPanel;
	}

	public void setAddPanel(JPanel addPanel) {
		this.addPanel = addPanel;
	}

	public JPanel getTablePanel() {
		return tablePanel;
	}

	public void setTablePanel(JPanel tablePanel) {
		this.tablePanel = tablePanel;
	}

	public JPanel getHintPanel() {
		return hintPanel;
	}

	public void setHintPanel(JPanel hintPanel) {
		this.hintPanel = hintPanel;
	}

	public JPanel getDatePanel() {
		return datePanel;
	}

	public void setDatePanel(JPanel datePanel) {
		this.datePanel = datePanel;
	}

	public JTextField getFirstNameTextField() {
		return firstNameTextField;
	}

	public void setFirstNameTextField(JTextField firstNameTextField) {
		this.firstNameTextField = firstNameTextField;
	}

	public JTextField getLastNameTextField() {
		return lastNameTextField;
	}

	public void setLastNameTextField(JTextField lastNameTextField) {
		this.lastNameTextField = lastNameTextField;
	}

	public JTextField getYearTextField() {
		return yearTextField;
	}

	public void setYearTextField(JTextField yearTextField) {
		this.yearTextField = yearTextField;
	}

	public JLabel getDateOfBirthLabel() {
		return dateOfBirthLabel;
	}

	public void setDateOfBirthLabel(JLabel dateOfBirthLabel) {
		this.dateOfBirthLabel = dateOfBirthLabel;
	}

	public JLabel getFirstNameLabel() {
		return firstNameLabel;
	}

	public void setFirstNameLabel(JLabel firstNameLabel) {
		this.firstNameLabel = firstNameLabel;
	}

	public JLabel getLastNameLabel() {
		return lastNameLabel;
	}

	public void setLastNameLabel(JLabel lastNameLabel) {
		this.lastNameLabel = lastNameLabel;
	}

	public JLabel getGenderLabel() {
		return genderLabel;
	}

	public void setGenderLabel(JLabel genderLabel) {
		this.genderLabel = genderLabel;
	}

	public JLabel getAddTopicLabel() {
		return addTopicLabel;
	}

	public void setAddTopicLabel(JLabel addTopicLabel) {
		this.addTopicLabel = addTopicLabel;
	}

	public JLabel getTableTopicLabel() {
		return tableTopicLabel;
	}

	public void setTableTopicLabel(JLabel tableTopicLabel) {
		this.tableTopicLabel = tableTopicLabel;
	}

	public JLabel getHintLabel() {
		return hintLabel;
	}

	public void setHintLabel(JLabel hintLabel) {
		this.hintLabel = hintLabel;
	}

	public JLabel getDay() {
		return day;
	}

	public void setDay(JLabel day) {
		this.day = day;
	}

	public JLabel getMonth() {
		return month;
	}

	public void setMonth(JLabel month) {
		this.month = month;
	}

	public JLabel getYear() {
		return year;
	}

	public void setYear(JLabel year) {
		this.year = year;
	}

	public JComboBox<String> getGenderComboBox() {
		return genderComboBox;
	}

	public void setGenderComboBox(JComboBox<String> genderComboBox) {
		this.genderComboBox = genderComboBox;
	}

	public JComboBox<Integer> getDayComboBox() {
		return dayComboBox;
	}

	public void setDayComboBox(JComboBox<Integer> dayComboBox) {
		this.dayComboBox = dayComboBox;
	}

	public JComboBox<Integer> getMonthComboBox() {
		return monthComboBox;
	}

	public void setMonthComboBox(JComboBox<Integer> monthComboBox) {
		this.monthComboBox = monthComboBox;
	}

	public DefaultButton getAddButton() {
		return addButton;
	}

	public void setAddButton(DefaultButton addButton) {
		this.addButton = addButton;
	}

	public DefaultButton getRemoveButton() {
		return removeButton;
	}

	public void setRemoveButton(DefaultButton removeButton) {
		this.removeButton = removeButton;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JScrollPane getTableScrollPane() {
		return tableScrollPane;
	}

	public void setTableScrollPane(JScrollPane tableScrollPane) {
		this.tableScrollPane = tableScrollPane;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public DefaultButton getPrintButton() {
		return printButton;
	}

	public void setPrintButton(DefaultButton printButton) {
		this.printButton = printButton;
	}


}
