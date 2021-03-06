package videoShow.panel.tools;

import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Tools implements ActionListener{
	JButton jbutton;
	TextField textField;
	TextArea textArea;
	JTextArea jtextArea;
	JLabel label;
	
	int buttonWidth = 110;
	int buttonHeight = 35;
	
	ArrayList<JButton> buttonArray = new ArrayList<JButton>();
	ArrayList<TextField> textFieldArray = new ArrayList<TextField>();
	ArrayList<TextArea> textAreaArray = new ArrayList<TextArea>();
	ArrayList<JTextArea> jtextAreaArray = new ArrayList<JTextArea>();
	ArrayList<JLabel> labelArray = new ArrayList<JLabel>();
		
	public void createButton(String text, int x, int y){
		jbutton = new JButton();
		jbutton.setText(text);
		jbutton.setName(text);
		jbutton.setBounds(x, y, buttonWidth, buttonHeight);
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
	
	public TextField createTextField(String text, int x, int y){
		textField = new TextField();
		textField.setText(text);
		textField.setBounds(x, y, buttonWidth, buttonHeight);
		textField.setVisible(true);
		textFieldArray.add(textField);
		return textFieldArray.get(textFieldArray.size() - 1);

	}
	
	public TextField createTextField(String text, String name, int x, int y){
		textField = new TextField();
		textField.setText(text);
		textField.setName(name);
		textField.setBounds(x, y, buttonWidth, buttonHeight);
		textField.setVisible(true);
		textFieldArray.add(textField);
		return textFieldArray.get(textFieldArray.size() - 1);
	}
	
	public TextField createTextField(String name, String text, int x, int y, int width, int height){
		textField = new TextField();
		textField.setText(text);
		textField.setName(name);
		textField.setBounds(x, y, width, height);
		textField.setVisible(true);
		textFieldArray.add(textField);
		return textFieldArray.get(textFieldArray.size() - 1);
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
	
	public void createJTextArea(String text, int x, int y){
		jtextArea = new JTextArea();
		jtextArea.setText(text);
		jtextArea.setBounds(x, y, buttonWidth, buttonHeight);
		jtextArea.setVisible(true);
		jtextAreaArray.add(jtextArea);
	}
	
	public void createJTextArea(String text, String name, int x, int y){
		jtextArea = new JTextArea();
		jtextArea.setText(text);
		jtextArea.setName(name);
		jtextArea.setBounds(x, y, buttonWidth, buttonHeight);
		jtextArea.setVisible(true);
		jtextAreaArray.add(jtextArea);
	}
	
	public void createJTextArea(String name, String text, int x, int y, int width, int height){
		jtextArea = new JTextArea();
		jtextArea.setText(text);
		jtextArea.setName(name);
		jtextArea.setBounds(x, y, width, height);
		jtextArea.setVisible(true);
		jtextAreaArray.add(jtextArea);
	}
	
	public JLabel createLabel(String text, int x, int y){
		label = new JLabel();
		label.setText(text);
		label.setBounds(x, y, buttonWidth, buttonHeight);
		label.setVisible(true);
		labelArray.add(label);
		return labelArray.get(labelArray.size() - 1);
	}
	
	public JLabel createLabel(String text, String name, int x, int y){
		label = new JLabel();
		label.setText(text);
		label.setName(name);
		label.setBounds(x, y, buttonWidth, buttonHeight);
		label.setVisible(true);
		labelArray.add(label);
		return labelArray.get(labelArray.size() - 1);

	}
	
	public JLabel createLabel(String name, String text, int x, int y, int width, int height){
		label = new JLabel();
		label.setText(text);
		label.setName(name);
		label.setBounds(x, y, width, height);
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
	
	public TextField getTextField(String labelName){
		for(int i = 0; i < textFieldArray.size(); i++){
			if(textFieldArray.get(i).getName() == labelName){
				return textFieldArray.get(i);
			}
		}
		return null;
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

	}
	public void actionPerformed(ActionEvent e) {
	}
}
