package bvsm.panel.tools;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.TableColumn;

public class Tools implements ActionListener, ItemListener{
	JButton jbutton;
	TextField textField;
	TextArea textArea;
	JTextArea jtextArea;
	JLabel label;
	JPanel panel;
	JComboBox<String> combo;
	JTable table;
	ButtonGroup bg;
	
	Font font;
	Color color = Color.BLACK;
		
	int buttonWidth = 110;
	int buttonHeight = 35;
	int style = Font.BOLD | Font.ITALIC;

	public Tools(){
	 font = new Font ("Jokerman", Font.PLAIN, 20);
	}
	
	public ArrayList<JButton> buttonArray = new ArrayList<JButton>();
	ArrayList<TextField> textFieldArray = new ArrayList<TextField>();
	ArrayList<TextArea> textAreaArray = new ArrayList<TextArea>();
	ArrayList<JTextArea> jtextAreaArray = new ArrayList<JTextArea>();
	ArrayList<JLabel> labelArray = new ArrayList<JLabel>();
	ArrayList<JPanel> panelArray = new ArrayList<JPanel>();
	ArrayList<JComboBox<String>> JComboListArray = new ArrayList<JComboBox<String>>();
	ArrayList<JRadioButton> JRadioButtonArray = new ArrayList<JRadioButton>();
	ArrayList<JTable> tableArray = new ArrayList<JTable>();
	ArrayList<JTable> panelTableArray = new ArrayList<JTable>();
	
	public void createButton(String text, int x, int y){
		jbutton = new JButton();
		jbutton.setText(text);
		jbutton.setName(text);
		jbutton.setBounds(x, y, buttonWidth, buttonHeight);
		jbutton.addActionListener(this);
		jbutton.setVisible(true);
		buttonArray.add(jbutton);
	}
	
	public void createButton(String text, int x, int y, int width, int height){
		jbutton = new JButton();
		jbutton.setText(text);
		jbutton.setName(text);
		jbutton.setBounds(x, y, width, height);
		jbutton.addActionListener(this);
		jbutton.setVisible(true);
		buttonArray.add(jbutton);
	}
	
	public void createButton(String text, String name, int x, int y){
		jbutton = new JButton();
		jbutton.setName(name);
		jbutton.setText(text);
		jbutton.setBounds(x, y, buttonWidth, buttonHeight);
		jbutton.addActionListener(this);
		jbutton.setVisible(true);
		buttonArray.add(jbutton);
	}
	
	public void createButton(String text, String name, int x, int y, int width, int height){
		jbutton = new JButton();
		jbutton.setName(name);
		jbutton.setText(text);
		jbutton.setBounds(x, y, width, height);
		jbutton.addActionListener(this);
		jbutton.setVisible(true);
		buttonArray.add(jbutton);
	}
	
	public void createTextField(String text, int x, int y){
		textField = new TextField();
		textField.setText(text);
		textField.setBounds(x, y, buttonWidth, buttonHeight);
		textField.setVisible(true);
		textFieldArray.add(textField);
	}
	
	public void createTextField(String text, String name, int x, int y){
		textField = new TextField();
		textField.setText(text);
		textField.setName(name);
		textField.setBounds(x, y, buttonWidth, buttonHeight);
		textField.setVisible(true);
		textFieldArray.add(textField);
	}
	
	public void createTextField(String name, String text, int x, int y, int width, int height){
		textField = new TextField();
		textField.setText(text);
		textField.setName(name);
		textField.setBounds(x, y, width, height);
		textField.setVisible(true);
		textFieldArray.add(textField);
	}
	
	public void createTextArea(String text, int x, int y){
		textArea = new TextArea();
		textArea.setText(text);
		textArea.setBounds(x, y, buttonWidth, buttonHeight);
		textArea.setVisible(true);
		textAreaArray.add(textArea);
	}
	
	public void createTextArea(String text, String name, int x, int y){
		textArea = new TextArea();
		textArea.setText(text);
		textArea.setName(name);
		textArea.setBounds(x, y, buttonWidth, buttonHeight);
		textArea.setVisible(true);
		textAreaArray.add(textArea);
	}
	
	public void createTextArea(String name, String text, int x, int y, int width, int height){
		textArea = new TextArea();
		textArea.setText(text);
		textArea.setName(name);
		textArea.setBounds(x, y, width, height);
		textArea.setVisible(true);
		textAreaArray.add(textArea);
	}
	
	public JTextArea createJTextArea(String text, int x, int y){
		jtextArea = new JTextArea();
		jtextArea.setText(text);
		jtextArea.setBounds(x, y, buttonWidth, buttonHeight);
		jtextArea.setVisible(true);
		jtextAreaArray.add(jtextArea);
		return jtextAreaArray.get(jtextAreaArray.size() - 1);
	}
	
	public JTextArea createJTextArea(String text, String name, int x, int y){
		jtextArea = new JTextArea();
		jtextArea.setText(text);
		jtextArea.setName(name);
		jtextArea.setBounds(x, y, buttonWidth, buttonHeight);
		jtextArea.setVisible(true);
		jtextAreaArray.add(jtextArea);
		return jtextAreaArray.get(jtextAreaArray.size() - 1);
	}
	
	public JTextArea createJTextArea(String name, String text, int x, int y, int width, int height){
		jtextArea = new JTextArea();
		jtextArea.setText(text);
		jtextArea.setName(name);
		jtextArea.setBounds(x, y, width, height);
		jtextArea.setVisible(true);
		jtextAreaArray.add(jtextArea);
		return jtextAreaArray.get(jtextAreaArray.size() - 1);
	}
	
	public JLabel createLabel(String text, int x, int y){
		label = new JLabel();
		label.setText(text);
		label.setBounds(x, y, buttonWidth, buttonHeight);
		label.setFont(font);
		label.setForeground(color);
		label.setVisible(true);
		labelArray.add(label);
		return labelArray.get(labelArray.size() - 1);
	}
	
	public JLabel createLabel(String text, String name, int x, int y){
		label = new JLabel();
		label.setText(text);
		label.setName(name);
		label.setBounds(x, y, buttonWidth, buttonHeight);
		label.setFont(font);
		label.setForeground(color);
		label.setVisible(true);
		labelArray.add(label);
		return labelArray.get(labelArray.size() - 1);

	}
	
	public JLabel createLabel(String text, String name, int x, int y, int width, int height){
		label = new JLabel();
		label.setText(text);
		label.setName(name);
		label.setBounds(x, y, width, height);
		label.setFont(font);
		label.setForeground(color);
		label.setVisible(true);
		labelArray.add(label);
		return labelArray.get(labelArray.size() - 1);
	}
	
	public JLabel createLabel(int x, int y, int width, int height, ImageIcon icon){
		label = new JLabel();
		label.setBounds(x, y, width, height);
		label.setIcon(icon);;
		label.setVisible(true);
		labelArray.add(label);
		return labelArray.get(labelArray.size() - 1);
	}
	
	public JPanel createPanel(String name, int x, int y, int width, int height){
		panel = new JPanel();
		panel.setBounds(x, y, width, height);
		panel.setName(name);
		panel.setVisible(true);
		panel.setLayout(null);
		panelArray.add(panel);
		return panelArray.get(panelArray.size() - 1);
	}
	
	public JComboBox<String> createComboBox(String[][] list, String name, int x, int y, int width, int height, boolean visible){
		combo = new JComboBox<String>();
		combo.setName(name);
		combo.setBounds(x, y, width, height);
		combo.setVisible(visible);
		
			for(int yIndex = 0; yIndex < list[0].length; yIndex++){
				combo.addItem(list[0][yIndex]);
			}
		combo.addActionListener(this);
		JComboListArray.add(combo);
		return combo;
	}
	
	public JComboBox<String> createComboBox(String[][][] list, String name, int x, int y, int width, int height, boolean visible){
		combo = new JComboBox<String>();
		combo.setName(name);
		combo.setBounds(x, y, width, height);
		combo.setVisible(visible);
		
			for(int yIndex = 0; yIndex < list[0][0].length; yIndex++){
				combo.addItem(list[0][0][yIndex]);
			}
		combo.addActionListener(this);	
		JComboListArray.add(combo);
		return combo;
	}
	
	public ButtonGroup createButtonGroup(){
		bg = new ButtonGroup();
		return bg;
	}

	public JRadioButton createRadioButton(String name, int x, int y, int width, int height, boolean visible){
		JRadioButton rb = new JRadioButton();
		rb.setActionCommand(name);
		rb.setBounds(x, y, width, height);
		rb.setVisible(visible);
		JRadioButtonArray.add(rb);
		return rb;
		
	}
	
	public JTable createTable(String name, int rows, int columns, int width, int height, boolean visible){
		
		JTable table = new JTable(rows, columns);
		table.setName(name);
		table.setVisible(visible);
		table.setBounds(0, 0, width, height);
		table.setFillsViewportHeight(true);
		table.setRowHeight(25);
		panelTableArray.add(table);
		return panelTableArray.get(panelTableArray.size() - 1);

	}
	
	public JTable createTable(String name, int rows, int columns, int x, int y, int width, int height){
		
		JTable table = new JTable(rows, columns);
		table.setName(name);
		table.setBounds(x, y, width, height);
		table.setFillsViewportHeight(true);
		table.setRowHeight(25);	
		tableArray.add(table);
		return tableArray.get(tableArray.size() - 1);

	}
	
	public JTable createTable(String name, int rows, int columns, int x, int y, int width, int height, boolean visible){
		
		JTable table = new JTable(rows, columns);
		table.setName(name);
		table.setBounds(x, y, width, height);
		table.setFillsViewportHeight(visible);
		table.setRowHeight(25);
		tableArray.add(table);
		return tableArray.get(tableArray.size() - 1);

	}
	
	public void addComponents(JPanel panel){
		for(JButton button : buttonArray){
			panel.add(button);
		}
		
		for(TextField field : textFieldArray){
			panel.add(field);
		}
		
		for(TextArea area : textAreaArray){
			panel.add(area);
		}
		
		for(JTextArea tArea : jtextAreaArray){
			panel.add(tArea);
		}
		
		for(JLabel label : labelArray){
			panel.add(label);
		}
		
		for(JPanel panel1 : panelArray){
			panel.add(panel1);
		}
		
		for(JTable table : tableArray){
			panel.add(table);
		}
		
		for(JComboBox<String> combo : JComboListArray){
			panel.add(combo);
		}
		
		for(int i = 0; i < JRadioButtonArray.size(); i++){
			bg.add(JRadioButtonArray.get(i));
			JRadioButtonArray.get(i).addActionListener(this);
			panel.add(JRadioButtonArray.get(i));
		}		
	}
	
	public void updateCombo(JComboBox<String> cb, String[] list){

		cb.removeAllItems();
		for(int x = 0; x < list.length; x++){
				cb.addItem(list[x]);
		}
		
	}
	
	public JButton getButton(String name){
		for(int i = 0; i < buttonArray.size();i++){
			if(buttonArray.get(i).getName() == name){
				return buttonArray.get(i);
			}
		}
		return null;
	}
	
	public JTextArea getTextArea(String name){
		for(int i = 0; i < jtextAreaArray.size(); i++){
			if(jtextAreaArray.get(i).getName() == name) return jtextAreaArray.get(i);
		}
		return null;
	}
	
	public JLabel getLabel(String name){
		for(int i = 0; i < labelArray.size(); i++){
			if(labelArray.get(i).getName() == name) return labelArray.get(i);
		}
		return null;
	}
	
	public JPanel getPanel(String name){
		for(int i = 0; i < panelArray.size(); i++){
			if(panelArray.get(i).getName() == name) return panelArray.get(i);
		}
		return null;
	}
	
	public JTable getTable(String name){
		for(int i = 0; i < tableArray.size(); i++){
			if(tableArray.get(i).getName() == name) return tableArray.get(i);
		}
		
		return null;
	}
	
	public JTable getPanelTable(String name){
		for(int i = 0; i < panelTableArray.size(); i++){
			if(panelTableArray.get(i).getName() == name) return panelTableArray.get(i);
		}
		return null;
	}
	
	public JComboBox<String> getComboBox(String name){
		for(int i = 0; i < JComboListArray.size(); i++){
			if(JComboListArray.get(i).getName() == name) return JComboListArray.get(i);
		}
		return null;
	}

	public void setRadioBoxVisible(boolean visible){
		for(int i = 0; i < JRadioButtonArray.size();i++){
			JRadioButtonArray.get(i).setVisible(visible);
		}
	}
	
	public void setComboBoxEditable(boolean editable){
		for(int i = 0; i < JComboListArray.size();i++){
			JComboListArray.get(i).setEnabled(editable);
		}
	}
	
	public void actionPerformed(ActionEvent arg0) {

	}

	public void itemStateChanged(ItemEvent e) {
		
	}
}
