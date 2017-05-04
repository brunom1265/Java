package bvsm.panel;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import bvsm.database.Database;
import bvsm.images.GetImages;
import bvsm.panel.tools.Tools;

public abstract class BasePanel extends Tools{
	
	public JPanel jpanel;
	protected GetImages images;
	public Database db = new Database();
	public BasePanel previous;
	

	public BasePanel(BasePanel previous, JFrame frame, String name, int x, int y, int width, int height){

		images = new GetImages();
		jpanel = new JPanel();
		createComponents();
		jpanel.setName(name);
		jpanel.setBounds(x, y, width, height);
		jpanel.setBackground(Color.RED);
		createLabel(800, 0, 300,300, images.getImage("Bombeiros"));
		this.previous = previous;
		addComponents(jpanel);
		jpanel.setLayout(null);
		jpanel.setVisible(false);
		frame.add(jpanel);
		
	}
	
	protected void createComponents(){
		
	}
	
	public void setVisible(boolean visible){
		jpanel.setVisible(visible);
	}
}
