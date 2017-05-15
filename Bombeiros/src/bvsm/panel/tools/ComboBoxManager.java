package bvsm.panel.tools;

import javax.swing.JComboBox;

public class ComboBoxManager {

	Tools tool;

	public String[][] topic;
	public String[][] subTopic;
	public String[][][] subsubTopic;

	int indexPress = 0;
	int indexLast = 0;

	public String b1;
	public String b2;
	public int b3;

	protected int pM = 0;

	public ComboBoxManager(Tools tool, String[][][] susubTopic, String[][]... topics) {
		this.topic = topics[0];
		this.subTopic = topics[1];
		this.subsubTopic = susubTopic;
		this.tool = tool;
	}

	@SuppressWarnings("unchecked")
	public void updateCombo(JComboBox<String>... combos) {

		indexPress = combos[0].getSelectedIndex();
		indexLast = combos[2].getSelectedIndex();

		if (combos.length > 2) {
			if ((combos[0].equals(combos[1]))) {
				if (compare(combos[0].getSelectedItem(), combos[1].getSelectedItem())) {

					tool.updateCombo(combos[2], subTopic[indexPress]);
					tool.updateCombo(combos[3], subsubTopic[indexPress][indexLast]);

					pM = combos[0].getSelectedIndex();
				}
			} else {
				tool.updateCombo(combos[1], topic[0]);
				tool.updateCombo(combos[2], subTopic[0]);
				tool.updateCombo(combos[3], subsubTopic[0][0]);
			}
			if ((combos[0].equals(combos[2]))) {

				if (compare(combos[0].getSelectedItem(), combos[2].getSelectedItem())) {
					tool.updateCombo(combos[3], subsubTopic[pM][indexLast]);
				}
			}
		}
		b1 = (String) combos[1].getSelectedItem();
		b2 = (String) combos[2].getSelectedItem();
		b3 = combos[3].getSelectedIndex();
	}

	public boolean compare(Object first, Object second) {
		if (first != null) return first.equals(second);
		return false;
	}
}
