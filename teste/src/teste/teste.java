package teste;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class teste extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public teste() {

		String[] header = { "name", "age" };
		String[][] data = {{}};

		DefaultTableModel model = new DefaultTableModel(data, header);
		JTable table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(200, 300));
		table.setFillsViewportHeight(true);
		table.setRowHeight(20);
		JScrollPane js = new JScrollPane(table);
		js.setBounds(0, 86, 303, 68);
		js.setVisible(true);
		add(js);

	}

	public static void main(String[] args) {
		JFrame jf = new JFrame();
		teste tab = new teste();
		jf.setTitle("Table");
		jf.setSize(500, 500);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(tab);

	}
}
