package bvsm.panel.tools;

import javax.swing.JComboBox;

public class ComboBoxManager {

	Tools tool;

	public String[][] topic;
	public String[][] subTopic;
	public String[][][] subsubTopic;

	public int indexPress = 0;
	public int indexLast = 0;
	int indexL = 0;

	public String b1 = "Inc�ndio";
	public String b2 = "Geral";
	public int b3 = 0;
	public String type;

	protected int pM = 0;

	public ComboBoxManager(Tools tool, String[][][] susubTopic, String[][]... topics) {
		this.topic = topics[0];
		this.subTopic = topics[1];
		this.subsubTopic = susubTopic;
		this.tool = tool;
	}

	@SuppressWarnings("unchecked")
	public void updateCombo(JComboBox<String>... combos) {

		indexPress = combos[1].getSelectedIndex();
		indexLast = combos[2].getSelectedIndex();
		indexL = combos[3].getSelectedIndex();
		
		if (combos.length > 2) {
			if ((combos[0].equals(combos[1]))) {
					tool.updateCombo(combos[2], subTopic[indexPress]);
					indexLast = combos[2].getSelectedIndex();

					tool.updateCombo(combos[3], subsubTopic[indexPress][indexLast]);
			}
			else if ((combos[0].equals(combos[2]))) {
				if (compare(combos[0].getSelectedItem(), combos[2].getSelectedItem())) {
					
					tool.updateCombo(combos[3], subsubTopic[indexPress][indexLast]);
				}
			}
		}

		b1 = (String) combos[1].getSelectedItem();
		b2 = (String) combos[2].getSelectedItem();
		b3 = combos[3].getSelectedIndex();
		type = (String) combos[3].getSelectedItem();

	}

	public boolean compare(Object first, Object second) {
		if (first != null) return first.equals(second);
		return false;
	}
}
