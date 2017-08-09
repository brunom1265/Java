package bvsm.panel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import bvsm.database.Database;
import bvsm.images.GetImages;
import bvsm.panel.tools.ComboBoxManager;
import bvsm.panel.tools.Tools;

public abstract class BasePanel extends Tools{
	
	public JPanel jpanel;
	protected GetImages images;
	public Database db = new Database();
	public BasePanel previous;
	protected ComboBoxManager cbm;

	

	public BasePanel(BasePanel previous, JFrame frame, String name, int x, int y, int width, int height){
      
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		images = new GetImages();
		jpanel = new JPanel();
		jpanel.setName(name);
		jpanel.setBounds(x, y, width, height);
		//jpanel.setBackground(Color.RED);
		this.previous = previous;
		jpanel.setLayout(null);
		jpanel.setVisible(false);
		createComponents();

		addComponents(jpanel);
		frame.add(jpanel);
		
	}
	
	protected void createComponents(){
		
	}
	
	public void setVisible(boolean visible){
		jpanel.setVisible(visible);
	}
	
	public void print(String temp){
		System.out.println(temp);
	}
	
	public void print(int temp){
		System.out.println(temp);
	}
	
}
