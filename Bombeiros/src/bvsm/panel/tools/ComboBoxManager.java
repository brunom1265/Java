package bvsm.panel.tools;

import javax.swing.JComboBox;

public class ComboBoxManager{
	
	Tools tool;
	
	public String[][] topic;
	public String[][] subTopic;
	public String[][][] subsubTopic;
	
	int indexPress = 0;
	int indexLast = 0;
	
	public ComboBoxManager(Tools tool, String[][][] susubTopic, String[][] ... topics){
		this.topic = topics[0];
		this.subTopic = topics[1];
		this.subsubTopic = susubTopic;
		this.tool = tool;
	}
	
	public void updateCombo(JComboBox<String> ... combos){
		
		indexPress = combos[0].getSelectedIndex();
		indexLast = combos[2].getSelectedIndex();

		
		System.out.println("0 = " + indexPress);
		System.out.println("2 = " + indexLast);
		System.out.println("3 = " + combos[3].getSelectedIndex());

		if((combos[0].equals(combos[1]))){
			if(compare(combos[0].getSelectedItem(), combos[1].getSelectedItem())){
				
				tool.updateCombo(combos[2], subTopic[indexPress]);
				tool.updateCombo(combos[3], subsubTopic[indexPress][indexLast]);
			}
		}
		if((combos[0].equals(combos[2]))){

			if(compare(combos[0].getSelectedItem(), combos[2].getSelectedItem())){
				tool.updateCombo(combos[3], subsubTopic[indexPress][indexLast]);

			}
		}
		combos[0].setSelectedIndex(indexPress);
		combos[2].setSelectedIndex(indexLast);
	}
	
	public boolean compare(Object first, Object second){
		if(first != null) return first.equals(second);
		return false;
	}
}
