package bvsm.panel.tools;

import javax.swing.JComboBox;

public class ComboBoxManager {
	
	Tools tool;
	
	public String[][] topic;
	public String[][] subTopic;
	public String[][] subsubTopic;
	
	
	public ComboBoxManager(Tools tool, String[][] ... topics){
		this.topic = topics[0];
		this.subTopic = topics[1];
		this.subsubTopic = topics[2];
		this.tool = tool;
	}
	
	public void comboChooser(JComboBox<String> topic, JComboBox<String> subTopic, JComboBox<String> subsubTopic){

		int topicIndex = topic.getSelectedIndex();
		tool.updateCombo(subTopic, this.subTopic[topicIndex]);
		
		int subTopicIndex = subTopic.getSelectedIndex();
		System.out.println(subTopicIndex);
		tool.updateCombo(subsubTopic, this.subsubTopic[subTopicIndex]);
		
			//int index = subTopic.getSelectedIndex();
			//subsubTopic.setSelectedIndex(index);
	}
	
}
