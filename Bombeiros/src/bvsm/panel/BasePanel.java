package bvsm.panel;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import bvsm.images.GetImages;
import bvsm.panel.tools.Tools;

public abstract class BasePanel extends Tools{
	
	public JPanel panel;
	protected GetImages images;
	
	public BasePanel(JFrame frame, String name, int x, int y, int width, int height){


		images = new GetImages();
		panel = new JPanel();
		createComponents();
		panel.setName(name);
		panel.setBounds(x, y, width, height);
		panel.setBackground(Color.RED);
		createLabel(700, 0, 300,300, images.getImage("Bombeiros"));
		addComponents(panel);
		panel.setLayout(null);
		panel.setVisible(false);
		frame.add(panel);

	}
	
	protected void createComponents(){
		
	}
	public void setVisible(boolean visible){
		panel.setVisible(visible);
	}
}
