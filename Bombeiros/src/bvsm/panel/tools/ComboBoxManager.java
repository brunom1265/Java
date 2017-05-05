package bvsm.panel.tools;

import javax.swing.JComboBox;

public class ComboBoxManager {
	
	public String[] topic;
	public String[] subTopic;
	public String[] subsubTopic;
	
	public ComboBoxManager(String[] ... topics){
		this.topic = topics[0];
		this.subTopic = topics[1];
		this.subsubTopic = topics[2];
	}
	
	public void comboChooser(JComboBox<String> topic, JComboBox<String> subTopic, JComboBox<String> subsubTopic){
		
	}
	
}
